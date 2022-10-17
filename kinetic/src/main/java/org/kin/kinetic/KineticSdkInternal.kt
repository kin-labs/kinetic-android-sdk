package org.kin.kinetic

import android.util.Base64
import org.kin.kinetic.helpers.generateCreateAccountTransaction
import org.kin.kinetic.helpers.generateMakeTransferTransaction
import com.solana.Solana
import com.solana.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.openapitools.client.models.*
import java.time.Instant

class KineticSdkInternal(
    endpoint: String,
    environment: String,
    index: Int,
    headers: Map<String, String>,
) {
    companion object {
        val MEMO_V1_PROGRAM_ID = PublicKey("Memo1UhkJRfHyvLMcVucJwxXeuD728EqVDDwQDxFMNo")
    }

    val solana: Solana? = null
    val environment: String
    val endpoint: String
    val index: Int
    var appConfig: AppConfig? = null

    val accountApi: AccountApi
    val airdropApi: AirdropApi
    val transactionApi: TransactionApi
    val appApi: AppApi
    val dispatcher = Dispatchers.IO
    val logger = MutableStateFlow(Pair(LogLevel.INFO, "Initializing logger"))

    init {
        this.endpoint = endpoint
        this.environment = environment
        this.index = index

        accountApi = AccountApi(endpoint)
        airdropApi = AirdropApi(endpoint)
        transactionApi = TransactionApi(endpoint)
        appApi = AppApi(endpoint)

        log(LogLevel.INFO, "Initializing $NAME@$VERSION\nendpoint: ${this.endpoint}, environment: ${this.environment}, index: ${this.index}")
    }

    suspend fun createAccount(
        commitment: Commitment,
        mint: String?,
        owner: Keypair,
        referenceId: String?,
        referenceType: String?,
    ): org.openapitools.client.models.Transaction {
        val appConfig = ensureAppConfig()
        val mint = getAppMint(appConfig, mint)

        val accounts = this@KineticSdkInternal.getTokenAccounts(owner.publicKey, mint.publicKey)
        if (!accounts.isEmpty()) {
            error("Token account already exists")
        }

        val latestBlockhashResponse = this.getBlockhash()

        val tx = generateCreateAccountTransaction(
            mint.addMemo,
            latestBlockhashResponse.blockhash,
            index,
            mint.feePayer,
            mint.publicKey,
            owner.solana
        )

        val serialized = tx.serialize(SerializeConfig(requireAllSignatures = false, verifySignatures = false))

        val createAccountRequest = CreateAccountRequest(
            commitment,
            environment,
            index,
            latestBlockhashResponse.lastValidBlockHeight,
            mint.publicKey,
            Base64.encodeToString(serialized, 0),
            referenceId,
            referenceType
        )

        return withContext(dispatcher) {
            accountApi.createAccount(createAccountRequest)
        }
    }

    suspend fun getAppConfig(environment: String, index: Int): AppConfig {
        val appConfig = appApi.getAppConfig(environment, index)
        this.appConfig = appConfig
        return appConfig
    }

    suspend fun getBalance(account: String): BalanceResponse {
        return withContext(dispatcher) {
            accountApi.getBalance(environment, index, account)
        }
    }

    suspend fun getHistory(account: String, mint: String?): List<HistoryResponse> {
        val appConfig = ensureAppConfig()
        val mint = getAppMint(appConfig, mint)
        return withContext(dispatcher) {
            accountApi.getHistory(environment, index, account, mint.publicKey)
        }
    }

    suspend fun getTokenAccounts(account: String, mint: String?): List<String> {
        val appConfig = ensureAppConfig()
        val mint = getAppMint(appConfig, mint)
        return withContext(dispatcher) {
            accountApi.getTokenAccounts(environment, index, account, mint.publicKey)
        }
    }

    suspend fun getTransaction(signature: String): GetTransactionResponse {
        return withContext(dispatcher) {
            transactionApi.getTransaction(environment, index, signature)
        }
    }

    suspend fun makeTransfer(
        amount: String,
        commitment: Commitment = Commitment.confirmed,
        destination: String,
        mint: String?,
        owner: Keypair,
        referenceId: String?,
        referenceType: String?,
        senderCreate: Boolean,
        type: KinBinaryMemo.TransactionType,
    ): org.openapitools.client.models.Transaction {
        val appConfig = ensureAppConfig()
        val mint = getAppMint(appConfig, mint)

        this.validateDestination(appConfig, destination)

        val accounts = this@KineticSdkInternal.getTokenAccounts(destination, mint.publicKey)
        if (accounts.isEmpty() && !senderCreate) {
            error("Destination account does not exist")
        }

        val latestBlockhashResponseJob = this@KineticSdkInternal.getBlockhash()
        val latestBlockhashResponse = latestBlockhashResponseJob

        val tx = generateMakeTransferTransaction(
            mint.addMemo,
            amount,
            latestBlockhashResponse.blockhash,
            destination,
            index,
            mint.decimals,
            mint.feePayer,
            mint.publicKey,
            owner.solana,
            senderCreate,
            type
        )

        val serialized = tx.serialize(SerializeConfig(requireAllSignatures = false, verifySignatures = false))

        val makeTransferRequest = MakeTransferRequest(
            commitment,
            environment,
            index,
            mint.publicKey,
            latestBlockhashResponse.lastValidBlockHeight,
            Base64.encodeToString(serialized, 0),
            referenceId,
            referenceType
        )

        return withContext(dispatcher) {
            transactionApi.makeTransfer(makeTransferRequest)
        }
    }

    suspend fun requestAirdrop(
        account: String,
        amount: String?,
        commitment: Commitment,
        mint: String?,
    ): RequestAirdropResponse {
        val appConfig = ensureAppConfig()
        val mint = getAppMint(appConfig, mint)

        return withContext(dispatcher) {
            airdropApi.requestAirdrop(
                RequestAirdropRequest(
                    account,
                    commitment,
                    environment,
                    index,
                    mint.publicKey,
                    amount,
                )
            )
        }
    }

    private fun apiBaseOptions(headers: Map<String, String>): Map<String, String> {
        return headers + mapOf(
            Pair("kinetic-environment", environment),
            Pair("kinetic-index", index.toString()),
            Pair("kinetic-user-agent", "$NAME@$VERSION")
        )
    }

    private fun ensureAppConfig(): AppConfig {
        appConfig?.let { return it } ?: error("App config not initialized")
    }

    private fun getAppMint(appConfig: AppConfig, mint: String?): AppConfigMint {
        val mint = mint ?: appConfig.mint.publicKey
        val found = appConfig.mints.find { item ->
            item.publicKey == mint
        }
        found?.let { return it } ?: error("Mint not found")
    }

    private suspend fun getBlockhash(): LatestBlockhashResponse {
        return transactionApi.getLatestBlockhash(environment, index)
    }

    private fun validateDestination(appConfig: AppConfig, destination: String) {
        if (appConfig.mints.find { mint -> mint.publicKey == destination } != null) {
            error("Cannot transfer to a mint address")
        }
    }

    private fun log(level: LogLevel, message: String) {
        logger.update { Pair(level, "$NAME::${Instant.now()}::${message}") }
    }
}
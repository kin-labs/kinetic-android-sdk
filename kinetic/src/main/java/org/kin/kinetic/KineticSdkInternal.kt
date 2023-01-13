package org.kin.kinetic

import android.util.Base64
import org.kin.kinetic.helpers.generateCreateAccountTransaction
import org.kin.kinetic.helpers.generateMakeTransferTransaction
import com.solana.Solana
import com.solana.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import org.kin.kinetic.generated.api.*
import org.kin.kinetic.generated.api.model.*
import org.kin.kinetic.generated.api.model.Transaction
import org.kin.kinetic.helpers.addDecimals
import java.time.Instant

class KineticSdkInternal(
    val sdkConfig: KineticSdkConfig
) {
    companion object {
        val MEMO_V1_PROGRAM_ID = PublicKey("Memo1UhkJRfHyvLMcVucJwxXeuD728EqVDDwQDxFMNo")
    }

    val solana: Solana? = null
    var appConfig: AppConfig? = null

    val accountApi: AccountApi
    val airdropApi: AirdropApi
    val transactionApi: TransactionApi
    val appApi: AppApi
    val dispatcher = Dispatchers.IO
    val logger = MutableStateFlow(Pair(LogLevel.INFO, "Initializing logger"))

    init {
        val apiHeaders: Map<String, String> = apiBaseOptions(sdkConfig.headers)

        accountApi = AccountApi(basePath = sdkConfig.endpoint, headers = apiHeaders)
        airdropApi = AirdropApi(basePath = sdkConfig.endpoint, headers = apiHeaders)
        transactionApi = TransactionApi(basePath = sdkConfig.endpoint, headers = apiHeaders)
        appApi = AppApi(basePath = sdkConfig.endpoint, headers = apiHeaders)

        log(LogLevel.INFO, "Initializing $NAME@$VERSION\nendpoint: ${sdkConfig.endpoint}, environment: ${sdkConfig.environment}, index: ${sdkConfig.index}")
    }

    suspend fun createAccount(
        owner: Keypair,
        commitment: Commitment?,
        mint: String?,
        referenceId: String?,
        referenceType: String?,
    ): Transaction {
        val appConfig = ensureAppConfig()
        val commitment = getCommitment(commitment)
        val mint = getAppMint(appConfig, mint)

        val accounts = this@KineticSdkInternal.getTokenAccounts(owner.publicKey, commitment, mint.publicKey)
        if (!accounts.isEmpty()) {
            error("Token account already exists")
        }

        val latestBlockhashResponse = this.getBlockhash()

        val tx = generateCreateAccountTransaction(
            mint.addMemo,
            latestBlockhashResponse.blockhash,
            sdkConfig.index,
            mint.feePayer,
            mint.publicKey,
            owner.solana
        )

        val serialized = tx.serialize(SerializeConfig(requireAllSignatures = false, verifySignatures = false))

        val createAccountRequest = CreateAccountRequest(
            commitment,
            sdkConfig.environment,
            sdkConfig.index,
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

    suspend fun getBalance(account: String, commitment: Commitment?): BalanceResponse {
        val commitment = getCommitment(commitment)

        return withContext(dispatcher) {
            accountApi.getBalance(sdkConfig.environment, sdkConfig.index, account, commitment)
        }
    }

    suspend fun getHistory(account: String, commitment: Commitment?, mint: String?): List<HistoryResponse> {
        val appConfig = ensureAppConfig()
        val commitment = getCommitment(commitment)
        val mint = getAppMint(appConfig, mint)

        return withContext(dispatcher) {
            accountApi.getHistory(sdkConfig.environment, sdkConfig.index, account, mint.publicKey, commitment)
        }
    }

    suspend fun getTokenAccounts(account: String, commitment: Commitment?, mint: String?): List<String> {
        val appConfig = ensureAppConfig()
        val commitment = getCommitment(commitment)
        val mint = getAppMint(appConfig, mint)

        return withContext(dispatcher) {
            accountApi.getTokenAccounts(sdkConfig.environment, sdkConfig.index, account, mint.publicKey, commitment)
        }
    }

    suspend fun getTransaction(signature: String, commitment: Commitment?): GetTransactionResponse {
        val commitment = getCommitment(commitment)

        return withContext(dispatcher) {
            transactionApi.getTransaction(sdkConfig.environment, sdkConfig.index, signature, commitment)
        }
    }

    suspend fun makeTransfer(
        amount: String,
        destination: String,
        owner: Keypair,
        commitment: Commitment?,
        mint: String?,
        referenceId: String?,
        referenceType: String?,
        senderCreate: Boolean,
        type: KinBinaryMemo.TransactionType,
    ): Transaction {
        val appConfig = ensureAppConfig()
        val commitment = getCommitment(commitment)
        val mint = getAppMint(appConfig, mint)
        val amount = addDecimals(amount, mint.decimals).toString()

        this.validateDestination(appConfig, destination)

        val accounts = this@KineticSdkInternal.getTokenAccounts(destination, commitment, mint.publicKey)
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
            sdkConfig.index,
            mint.decimals,
            mint.feePayer,
            mint.publicKey,
            owner.solana,
            accounts.isEmpty() && senderCreate,
            type
        )

        val serialized = tx.serialize(SerializeConfig(requireAllSignatures = false, verifySignatures = false))

        val makeTransferRequest = MakeTransferRequest(
            commitment,
            sdkConfig.environment,
            sdkConfig.index,
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
        commitment: Commitment?,
        mint: String?,
    ): RequestAirdropResponse {
        val appConfig = ensureAppConfig()
        val commitment = getCommitment(commitment)
        val mint = getAppMint(appConfig, mint)
        var amount = amount
        if (amount != null) {
            amount = addDecimals(amount, mint.decimals).toString()
        }

        return withContext(dispatcher) {
            airdropApi.requestAirdrop(
                RequestAirdropRequest(
                    account,
                    commitment,
                    sdkConfig.environment,
                    sdkConfig.index,
                    mint.publicKey,
                    amount,
                )
            )
        }
    }

    private fun apiBaseOptions(headers: Map<String, String>): Map<String, String> {
        return headers + mapOf(
            Pair("kinetic-environment", sdkConfig.environment),
            Pair("kinetic-index", sdkConfig.index.toString()),
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
        return transactionApi.getLatestBlockhash(sdkConfig.environment, sdkConfig.index)
    }

    private fun getCommitment(commitment: Commitment?): Commitment {
        return commitment ?: sdkConfig.commitment ?: Commitment.confirmed
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
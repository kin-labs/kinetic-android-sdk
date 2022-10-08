package com.kinlabs.kinetic

import com.solana.Solana
import com.solana.networking.OkHttpNetworkingRouter
import kotlinx.coroutines.async
import org.openapitools.client.models.*
import java.util.*
import java.util.logging.Logger

class KineticSdk {
    var solana: Solana? = null
    private var internal: KineticSdkInternal
    val endpoint: String
    val environment: String
    val headers: Map<String, String>?
    val index: Int
    val logger: Logger?
    val solanaRpcEndpoint: String?

    constructor(
        endpoint: String,
        environment: String,
        headers: Map<String, String> = emptyMap(),
        index: Int,
        logger: Logger?,
        solanaRpcEndpoint: String?
    ) {
        this.internal = KineticSdkInternal(
            endpoint,
            environment,
            headers,
            index,
            logger
        )
        this.endpoint = endpoint
        this.environment = environment
        this.headers = headers
        this.index = index
        this.logger = logger
        this.solanaRpcEndpoint = solanaRpcEndpoint
    }

    var config: AppConfig? = null
        get() {
            return this.internal.appConfig
        }

    suspend fun createAccount(
        commitment: Commitment = Commitment.confirmed,
        mint: String? = null,
        owner: Keypair,
        referenceId: String? = null,
        referenceType: String? = null
    ): Transaction {
        return internal.createAccount(
            commitment,
            mint,
            owner,
            referenceId,
            referenceType
        )
    }

    suspend fun getBalance(account: String): BalanceResponse {
        return internal.getBalance(account)
    }

    fun getExplorerUrl(path: String): String? {
        return internal.appConfig?.environment?.explorer?.replace("{path}", path)
    }

    suspend fun getHistory(account: String, mint: String? = null): List<HistoryResponse> {
        return internal.getHistory(account, mint)
    }

    suspend fun getTokenAccounts(account: String, mint: String? = null): List<String> {
        return internal.getTokenAccounts(account, mint)
    }

    suspend fun getTransaction(signature: String): GetTransactionResponse {
        return internal.getTransaction(signature)
    }

    suspend fun makeTransfer(
        amount: String,
        commitment: Commitment = Commitment.confirmed,
        destination: String,
        mint: String? = null,
        owner: Keypair,
        referenceId: String? = null,
        referenceType: String? = null,
        senderCreate: Boolean = false,
        type: KinBinaryMemo.TransactionType = KinBinaryMemo.TransactionType.None
    ): Transaction {
        return internal.makeTransfer(
            amount,
            commitment,
            destination,
            mint,
            owner,
            referenceId,
            referenceType,
            senderCreate,
            type
        )
    }

    suspend fun requestAirdrop(
        account: String,
        amount: String? = null,
        commitment: Commitment = Commitment.finalized,
        mint: String? = null
    ): RequestAirdropResponse {
        return internal.requestAirdrop(
            account,
            amount,
            commitment,
            mint
        )
    }

    suspend fun init(): AppConfig {
        val config = internal.getAppConfig(environment, index)
        val rpcEndpoint = if (solanaRpcEndpoint != null) getSolanaRPCEndpoint(solanaRpcEndpoint)
            else getSolanaRPCEndpoint(config.environment.cluster.endpoint)
        val networkingRouter = OkHttpNetworkingRouter(rpcEndpoint)
        solana = Solana(networkingRouter)
        return config
    }

    companion object {
        suspend fun setup(
            endpoint: String,
            environment: String,
            headers: Map<String, String> = emptyMap<String, String>(),
            index: Int,
            logger: Logger? = null,
            solanaRpcEndpoint: String? = null,
        ): KineticSdk {
            var sdk = KineticSdk(
                endpoint,
                environment,
                headers,
                index,
                logger,
                solanaRpcEndpoint
            )
            sdk.init()
            return sdk
        }
    }
}
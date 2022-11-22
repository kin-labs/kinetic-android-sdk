package org.kin.kinetic

import org.kin.kinetic.helpers.getSolanaRPCEndpoint
import com.solana.Solana
import com.solana.networking.OkHttpNetworkingRouter
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kin.kinetic.generated.api.model.*

class KineticSdk {
    var solana: Solana? = null
    private var internal: KineticSdkInternal
    val endpoint: String
    val environment: String
    val index: Int
    val headers: Map<String, String>?
    val logger: StateFlow<Pair<LogLevel, String>>
    val solanaRpcEndpoint: String?

    private constructor(
        endpoint: String,
        environment: String,
        index: Int,
        headers: Map<String, String>,
        solanaRpcEndpoint: String?
    ) {
        this.internal = KineticSdkInternal(
            endpoint,
            environment,
            index,
            headers
        )
        this.endpoint = endpoint
        this.environment = environment
        this.index = index
        this.headers = headers
        this.logger = this.internal.logger.asStateFlow()
        this.solanaRpcEndpoint = solanaRpcEndpoint
    }

    var config: AppConfig? = null
        get() {
            return this.internal.appConfig
        }

    suspend fun createAccount(
        owner: Keypair,
        commitment: Commitment = Commitment.confirmed,
        mint: String? = null,
        referenceId: String? = null,
        referenceType: String? = null
    ): Transaction {
        return internal.createAccount(
            owner,
            commitment,
            mint,
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
        destination: String,
        owner: Keypair,
        commitment: Commitment = Commitment.confirmed,
        mint: String? = null,
        referenceId: String? = null,
        referenceType: String? = null,
        senderCreate: Boolean = false,
        type: KinBinaryMemo.TransactionType = KinBinaryMemo.TransactionType.None
    ): Transaction {
        return internal.makeTransfer(
            amount,
            destination,
            owner,
            commitment,
            mint,
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
            index: Int,
            headers: Map<String, String> = emptyMap<String, String>(),
            solanaRpcEndpoint: String? = null,
        ): KineticSdk {
            var sdk = KineticSdk(
                endpoint,
                environment,
                index,
                headers,
                solanaRpcEndpoint
            )
            sdk.init()
            return sdk
        }
    }
}
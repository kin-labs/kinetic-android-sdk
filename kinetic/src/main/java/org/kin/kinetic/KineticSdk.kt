package org.kin.kinetic

import org.kin.kinetic.helpers.getSolanaRPCEndpoint
import com.solana.Solana
import com.solana.networking.OkHttpNetworkingRouter
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.kin.kinetic.generated.api.model.*

class KineticSdk {
    val sdkConfig: KineticSdkConfig
    var solana: Solana? = null
    private var internal: KineticSdkInternal
    val logger: StateFlow<Pair<LogLevel, String>>

    private constructor(
        sdkConfig: KineticSdkConfig,
    ) {
        this.sdkConfig = sdkConfig
        this.internal = KineticSdkInternal(sdkConfig)
        this.logger = this.internal.logger.asStateFlow()
    }

    var config: AppConfig? = null
        get() {
            return this.internal.appConfig
        }

    var endpoint: String = ""
        get() {
            return this.sdkConfig.endpoint
        }

    var solanaRpcEndpoint: String? = null
        get() {
            return this.sdkConfig.solanaRpcEndpoint
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
        val config = internal.getAppConfig(sdkConfig.environment, sdkConfig.index)
        val rpcEndpoint = if (sdkConfig.solanaRpcEndpoint != null) getSolanaRPCEndpoint(sdkConfig.solanaRpcEndpoint)
            else getSolanaRPCEndpoint(config.environment.cluster.endpoint)
        val networkingRouter = OkHttpNetworkingRouter(rpcEndpoint)
        solana = Solana(networkingRouter)
        return config
    }

    companion object {
        suspend fun setup(
            sdkConfig: KineticSdkConfig,
        ): KineticSdk {
            var sdk = KineticSdk(sdkConfig)
            sdk.init()
            return sdk
        }
    }
}
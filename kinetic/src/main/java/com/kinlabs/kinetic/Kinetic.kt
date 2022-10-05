package com.kinlabs.kinetic

import android.util.Base64
import android.util.Log
import com.solana.Solana
import com.solana.api.*
import com.solana.core.*
import com.solana.core.Transaction
import com.solana.models.REQUIRED_ACCOUNT_SPACE
import com.solana.networking.NetworkingRouter
import com.solana.networking.OkHttpNetworkingRouter
import com.solana.networking.RPCEndpoint
import com.solana.programs.SystemProgram
import com.solana.programs.TokenProgram
import com.squareup.moshi.JsonClass
import okhttp3.OkHttpClient
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.wallet.Wallet
import org.kinlabs.kinetic.*
import org.near.borshj.Borsh
import org.openapitools.client.models.*
import java.io.File
import java.math.BigDecimal


class Kinetic(filesDir: File, environment: String, appIndex: Int, endpoint: String, appConfig: AppConfig) {
    data class Builder(
        val filesDir: File,
        val environment: String,
        val appIndex: Int,
        val endpoint: String
    ) {
        fun build(callback: (Kinetic) -> Unit) {
            val appApi = AppApi(endpoint)
            Thread {
                val appConfig = appApi.getAppConfig(environment, appIndex)
                callback(Kinetic(filesDir, environment, appIndex, endpoint, appConfig))
            }.start()
        }
    }

    companion object {
        val SAMPLE_WALLET = PublicKey("3rad7aFPdJS3CkYPSphtDAWCNB8BYpV2yc7o5ZjFQbDb") // (Pause For's mainnet hot wallet)
        val MEMO_V1_PROGRAM_ID = PublicKey("Memo1UhkJRfHyvLMcVucJwxXeuD728EqVDDwQDxFMNo")
    }

    val environment: String
    val appIndex: Int
    val network: NetworkingRouter
    val storage: BasicAccountStorage
    val solana: Solana

    val accountApi: AccountApi
    val airdropApi: AirdropApi
    val transactionApi: TransactionApi
    val appApi: AppApi

    var appConfig: AppConfig

    init {
        this.environment = environment
        this.appIndex = appIndex
        this.appConfig = appConfig
        network = OkHttpNetworkingRouter(getSolanaRPCEndpoint(environment))
        storage = BasicAccountStorage(filesDir)
        solana = Solana(network)
        accountApi = AccountApi(endpoint)
        airdropApi = AirdropApi(endpoint)
        transactionApi = TransactionApi(endpoint)
        appApi = AppApi(endpoint)
    }

    fun getAppConfig(callback: (AppConfig) -> Unit) {
        Thread {
            try {
                val appConfig = appApi.getAppConfig(environment, appIndex)
                this.appConfig = appConfig
                callback(appConfig)
            } catch (e: Exception) {
                Log.d("TAG", e.localizedMessage)
            }
        }.start()
    }

    fun getBalance(accountId: String, callback: (String) -> Unit) {
        Thread {
            callback(accountApi.getBalance(environment, appIndex, accountId).balance)
        }.start()
    }

    fun getTokenAccounts(accountId: String, mint: AppConfigMint? = null, callback: (List<String>) -> Unit) {
        Thread {
            callback(accountApi.getTokenAccounts(environment, appIndex, accountId, mint?.publicKey ?: appConfig.mint.publicKey))
        }.start()
    }

    fun getAccountHistory(accountId: String, mint: AppConfigMint? = null, callback: (List<HistoryResponse>) -> Unit) {
        Thread {
            val res = accountApi.getHistory(environment, appIndex, accountId, mint?.publicKey ?: appConfig.mint.publicKey)
            Log.d("TAG", res.toString())
            callback(res)
        }.start()
    }

    fun requestAirdrop(account: String, amount: Int, mint: AppConfigMint? = null, commitment: RequestAirdropRequest.Commitment = RequestAirdropRequest.Commitment.confirmed, callback: (String) -> Unit) {
        Thread {
            try {
                val airdropRequest = RequestAirdropRequest(account, commitment, environment, appIndex, mint?.publicKey ?: appConfig.mint.publicKey, amount.toString())
                callback(airdropApi.requestAirdrop(airdropRequest).signature)
            } catch (e: Exception) {
                callback(e.localizedMessage)
            }
        }.start()
    }

    fun createAccount(
        account: KineticAccount,
        mint: AppConfigMint? = null,
        commitment: CreateAccountRequest.Commitment = CreateAccountRequest.Commitment.finalized,
        referenceId: String? = null,
        referenceType: String? = null,
        callback: (CreateAccountResponse) -> Unit
    ) {
        Thread {
            try {
                val mint = mint ?: appConfig.mint
                val recentBlockhash = transactionApi.getLatestBlockhash(environment, appIndex)

                val tokenAccount = com.solana.core.PublicKey.findProgramAddress(
                    listOf(
                        account.solanaAccount.publicKey.pubkey,
                        PublicKey("TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA").solanaPublicKey.pubkey,
                        PublicKey(appConfig.mint.publicKey).solanaPublicKey.pubkey
                    ), PublicKey("ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL").solanaPublicKey
                )

                try {
                    val transaction = Transaction()
                    transaction.signatures = mutableListOf<SignaturePubkeyPair>(SignaturePubkeyPair(null, account.publicKey.solanaPublicKey))
                    val createAccountInstruction = TransactionInstruction(
                        PublicKey("ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL").solanaPublicKey,
                        listOf(
                            AccountMeta(PublicKey(mint.feePayer).solanaPublicKey, true, true),
                            AccountMeta(tokenAccount.address, false, true),
                            AccountMeta(account.publicKey.solanaPublicKey, true, false),
                            AccountMeta(PublicKey(mint.publicKey).solanaPublicKey, false, false),
                            AccountMeta(SystemProgram.PROGRAM_ID, false, false),
                            AccountMeta(TokenProgram.PROGRAM_ID, false, false),
                            AccountMeta(TokenProgram.SYSVAR_RENT_PUBKEY, false, false)
                        ),
                        ByteArray(0)
                    )
                    transaction.add(createAccountInstruction)
                    transaction.setRecentBlockHash(recentBlockhash.blockhash)
                    transaction.feePayer = PublicKey(mint.feePayer).solanaPublicKey
                    transaction.partialSign(account.solanaAccount)

                    val txBytes = transaction.serialize(SerializeConfig(requireAllSignatures = false, verifySignatures = false))

                    val res = accountApi.createAccount(
                        CreateAccountRequest(
                            commitment,
                            environment,
                            appIndex,
                            recentBlockhash.lastValidBlockHeight,
                            mint.publicKey,
                            Base64.encodeToString(txBytes, 0),
                            referenceId,
                            referenceType
                        )
                    )
                    Log.d("TAG", res.toString())
                } catch (e: Exception) {
                    Log.d("TAG", e.localizedMessage)
                }
            } catch (e: Exception) {
                Log.d("TAG", e.localizedMessage)
            }
        }.start()
    }

    fun submitPayment(
        fromAccount: KineticAccount,
        toPublicKey: String,
        mint: AppConfigMint? = null,
        commitment: MakeTransferRequest.Commitment = MakeTransferRequest.Commitment.confirmed,
        referenceId: String? = null,
        referenceType: String? = null,
        callback: (org.openapitools.client.models.Transaction) -> Unit
    ) {
        Thread {
            val toPublicKey = PublicKey(toPublicKey)
            val mint = mint ?: appConfig.mint
            val recentBlockhash = transactionApi.getLatestBlockhash(environment, appIndex)
            val transaction = Transaction()
            transaction.signatures = mutableListOf<SignaturePubkeyPair>(SignaturePubkeyPair(null, fromAccount.publicKey.solanaPublicKey))

            val kinMemo = KinBinaryMemo.Builder(124)
                .setTransferType(KinBinaryMemo.TransferType.None)
                .build()
            val memoInstruction = TransactionInstruction(
                MEMO_V1_PROGRAM_ID.solanaPublicKey,
                emptyList(),
                kinMemo.encode()
            )
//            transaction.addInstruction(memoInstruction)
            val tokenAccount = com.solana.core.PublicKey.findProgramAddress(
                listOf(
                    fromAccount.solanaAccount.publicKey.pubkey,
                    PublicKey("TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA").solanaPublicKey.pubkey,
                    PublicKey(appConfig.mint.publicKey).solanaPublicKey.pubkey
                ), PublicKey("ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL").solanaPublicKey
            )
            val destTokenAccount = com.solana.core.PublicKey.findProgramAddress(
                listOf(
                    toPublicKey.solanaPublicKey.pubkey,
                    PublicKey("TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA").solanaPublicKey.pubkey,
                PublicKey(mint.publicKey).solanaPublicKey.pubkey
            ), PublicKey("ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL").solanaPublicKey
            )
            val transferInstruction = TokenProgram.transferChecked(
                tokenAccount.address,
                destTokenAccount.address,
                1,
                (mint.decimals).toByte(),
                fromAccount.solanaAccount.publicKey,
                PublicKey(mint.publicKey).solanaPublicKey
            )
            transaction.addInstruction(transferInstruction)
            transaction.setRecentBlockHash(recentBlockhash.blockhash)
            transaction.feePayer = PublicKey(mint.feePayer).solanaPublicKey
            transaction.partialSign(fromAccount.solanaAccount)

            try {
                val txBytes = transaction.serialize(SerializeConfig(requireAllSignatures = false, verifySignatures = false))
                val makeTransferRequest = MakeTransferRequest(
                    commitment,
                    environment,
                    appIndex,
                    mint.publicKey,
                    recentBlockhash.lastValidBlockHeight,
                    Base64.encodeToString(txBytes, 0),
                    referenceId,
                    referenceType
                )
                callback(transactionApi.makeTransfer(makeTransferRequest))
            } catch (e: Exception) {
                Log.d("TAG", e.localizedMessage)
            }
        }.start()
    }

    ////
    // START: Direct to Solana functions, don't touch Kinetic backend
    ////

    fun getLocalAccount(callback: (KineticAccount?) -> Unit) {
        storage.account()
            .onSuccess {
                callback(it)
            }
            .onFailure {
                Log.d("TAG", it.localizedMessage)
                callback(null)
            }
    }

    fun createAccountDirect(callback: (KineticAccount) -> Unit) {
        if (storage.account().isFailure) { // No account, create
            val account = KineticAccount()
            storage.save(account)
            callback(account)
        } else {
            callback(storage.account().getOrNull()!!)
        }
    }

    fun getSolBalance(callback: (Long) -> Unit) {
        solana.api.getBalance(SAMPLE_WALLET.solanaPublicKey) { res ->
            res.getOrNull()?.let { balance ->
                callback(balance)
            }
        }
    }

    fun getSPLBalance(token: String, callback: (String) -> Unit) {
        solana.api.getTokenAccountsByOwner(SAMPLE_WALLET.solanaPublicKey, PublicKey(token).solanaPublicKey) { res ->
            res.getOrNull()?.let { tokenKey ->
                solana.api.getTokenAccountBalance(tokenKey) { res ->
                    res.getOrNull()?.let {
                        callback(it.uiAmountString)
                    }
                }
            }
        }
    }

    ////
    // END: Direct to Solana functions, don't touch Kinetic backend
    ////
}
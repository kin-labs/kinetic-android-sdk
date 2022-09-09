package com.kinlabs.kinetic

import android.util.Log
import com.solana.Solana
import com.solana.api.*
import com.solana.core.*
import com.solana.models.REQUIRED_ACCOUNT_SPACE
import com.solana.networking.NetworkingRouter
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


class Kinetic(filesDir: File) {
    companion object {
        val TOKEN_KIN = "kinXdEcpDQeHPEuQnqmUgtYykqKGVFq6CeVX5iAHJq6"
        val SAMPLE_WALLET = PublicKey("3rad7aFPdJS3CkYPSphtDAWCNB8BYpV2yc7o5ZjFQbDb") // (Pause For's mainnet hot wallet)
        val TEST_PKEY = byteArrayOf(237.toByte(),61,27,169.toByte(),183.toByte(),200.toByte(),219.toByte(),250.toByte(),32,89,115,224.toByte(),68,92,237.toByte(),44,34,53,36,147.toByte(),82,190.toByte(),225.toByte(),116,163.toByte(),215.toByte(),212.toByte(),255.toByte(),5,70,1,32,136.toByte(),85,123,196.toByte(),201.toByte(),2,233.toByte(),220.toByte(),145.toByte(),142.toByte(),168.toByte(),175.toByte(),71,28,86,110,99,89,47,93,20,8,35,15,158.toByte(),119,107,224.toByte(),63,207.toByte(),158.toByte(),97)
        val TEST_ACCOUNT = Account(TEST_PKEY)
        val MEMO_V1_PROGRAM_ID = PublicKey("Memo1UhkJRfHyvLMcVucJwxXeuD728EqVDDwQDxFMNo")
    }

    val network = NetworkingRouter(RPCEndpoint.devnetSolana, OkHttpClient())
    val storage = BasicAccountStorage(filesDir)
    val solana = Solana(network)

    val accountApi = AccountApi("https://staging.kinetic.host")
    val airdropApi = AirdropApi("https://staging.kinetic.host")
    val transactionApi = TransactionApi("https://staging.kinetic.host")
    val appApi = AppApi("https://staging.kinetic.host")

    var appConfig = AppConfig(AppConfigApp(124, "Pause For"), AppConfigEnvironment("devnet", "test", AppConfigCluster("test", "test", "test", AppConfigCluster.Type.custom)), AppConfigMint(false,false,1,"test","test","test","test","test","test"),
        emptyList
   ())

    val environment = "devnet"
    val appIndex = 124

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

    fun getTokenAccounts(accountId: String, callback: (List<String>) -> Unit) {
        Thread {
            callback(accountApi.getTokenAccounts(environment, appIndex, accountId, appConfig.mint.symbol))
        }.start()
    }

    fun getAccountHistory(accountId: String, callback: (List<HistoryResponse>) -> Unit) {
        Thread {
            val res = accountApi.getHistory(environment, appIndex, accountId, appConfig.mint.symbol)
            Log.d("TAG", res.toString())
            callback(res)
        }.start()
    }

    fun requestAirdrop(account: String, amount: Int, callback: (String) -> Unit) {
        Thread {
            try {
                val airdropRequest = RequestAirdropRequest(account, RequestAirdropRequest.Commitment.confirmed, environment, appIndex, appConfig.mint.symbol, amount.toString())
                callback(airdropApi.requestAirdrop(airdropRequest).signature)
            } catch (e: Exception) {
                callback(e.localizedMessage)
            }
        }.start()
    }

    fun createAccount(callback: (CreateAccountResponse) -> Unit) {
        val networkParameters = NetworkParameters.fromID("org.bitcoin.production")
        Wallet(networkParameters).keyChainSeed.mnemonicCode?.let { mnemonic ->
            Thread {
                try {
                    val account =
                        Account.fromMnemonic(mnemonic, "", DerivationPath.BIP44_M_44H_501H_0H)
                    val recentBlockhash = transactionApi.getLatestBlockhash(environment, appIndex)

                    val tokenAccount = PublicKey.findProgramAddress(
                        listOf(
                            account.publicKey.pubkey,
                            PublicKey("TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA").pubkey,
                            PublicKey(appConfig.mint.publicKey).pubkey
                        ), PublicKey("ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL")
                    )

                    solana.api.getMinimumBalanceForRentExemption(REQUIRED_ACCOUNT_SPACE) {
                        it.onSuccess { balance ->
                            try {
                                val transaction = Transaction()
                                val newAccount = Account()
                                val createAccountInstruction = SystemProgram.createAccount(
                                    fromPublicKey = account.publicKey,
                                    newAccountPublickey = tokenAccount.address,
                                    lamports = balance,
                                    REQUIRED_ACCOUNT_SPACE,
                                    TokenProgram.PROGRAM_ID
                                )
                                transaction.addInstruction(createAccountInstruction)

                                val initializeAccountInstruction = TokenProgram.initializeAccount(
                                    account = tokenAccount.address,
                                    mint = PublicKey(appConfig.mint.publicKey),
                                    owner = account.publicKey
                                )
                                transaction.addInstruction(initializeAccountInstruction)

                                transaction.setRecentBlockHash(recentBlockhash.blockhash)

                                transaction.sign(account)

                                val txBytes = transaction.serialize()

                                val res = accountApi.createAccount(
                                    CreateAccountRequest(environment, appIndex, appConfig.mint.symbol, txBytes)
                                )
                                Log.d("TAG", res.signature!!)
                            } catch (e: Exception) {
                                Log.d("TAG", e.localizedMessage)
                            }
                        }.onFailure {
                            Log.d("TAG", it.localizedMessage)
                        }
                    }
                } catch (e: Exception) {
                    Log.d("TAG", e.localizedMessage)
                }
            }.start()
        }
    }

    fun submitPayment(accountId: String, callback: (AppTransaction) -> Unit) {
        Thread {
            val recentBlockhash = transactionApi.getLatestBlockhash(environment, appIndex)
            val transaction = Transaction()

            val kinMemo = KinBinaryMemo.Builder(124)
                .setTransferType(KinBinaryMemo.TransferType.None)
                .build()
            val memoInstruction = TransactionInstruction(
                MEMO_V1_PROGRAM_ID,
                emptyList(),
                kinMemo.encode()
            )
            transaction.addInstruction(memoInstruction)

            val transferInstruction = TokenProgram.transfer(
                TEST_ACCOUNT.publicKey,
                PublicKey("ChZg9BGutz7LvPJk1MPAiDrzoVPcZvt4Ef25eBBoURn1"),
                100,
                TEST_ACCOUNT.publicKey
            )
            transaction.addInstruction(transferInstruction)
            transaction.setRecentBlockHash(recentBlockhash.blockhash)
            transaction.setFeePayer(SAMPLE_WALLET)
            transaction.sign(TEST_ACCOUNT)

            try {
                val txBytes = transaction.serialize()
                val makeTransferRequest = MakeTransferRequest(MakeTransferRequest.Commitment.confirmed, environment, appIndex, appConfig.mint.symbol, recentBlockhash.lastValidBlockHeight, null, null, txBytes)
                callback(transactionApi.makeTransfer(makeTransferRequest))
            } catch (e: Exception) {
                Log.d("TAG", e.localizedMessage)
            }
        }.start()
    }

    ////
    // START: Direct to Solana functions, don't touch Kinetic backend
    ////

    fun createAccountDirect(callback: (String) -> Unit) {
        if (storage.account().isFailure) { // No account, create
            val networkParameters = NetworkParameters.fromID("org.bitcoin.production")
            Wallet(networkParameters).keyChainSeed.mnemonicCode?.let { mnemonic ->
                val account = Account.fromMnemonic(mnemonic, "", DerivationPath.BIP44_M_44H_501H_0H)
                storage.save(account)
                callback(account.publicKey.toBase58())
            }
        } else {
            callback(storage.account().getOrNull()!!.publicKey.toBase58())
        }
    }

    fun getSolBalance(callback: (Long) -> Unit) {
        solana.api.getBalance(SAMPLE_WALLET) { res ->
            res.getOrNull()?.let { balance ->
                callback(balance)
            }
        }
    }

    fun getSPLBalance(token: String, callback: (String) -> Unit) {
        solana.api.getTokenAccountsByOwner(SAMPLE_WALLET, PublicKey(token)) { res ->
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

    @JsonClass(generateAdapter = true)
    class WireTX(
        val type: String = "Buffer",
        val data: ByteArray = byteArrayOf(),
    ) : Borsh
}
package com.kinlabs.mogami

import com.solana.Solana
import com.solana.api.getBalance
import com.solana.api.getTokenAccountBalance
import com.solana.api.getTokenAccountsByOwner
import com.solana.core.Account
import com.solana.core.DerivationPath
import com.solana.core.PublicKey
import com.solana.models.TokenResultObjects
import com.solana.networking.NetworkingRouter
import com.solana.networking.RPCEndpoint
import okhttp3.OkHttpClient
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.wallet.Wallet
import java.io.File

class Mogami(filesDir: File) {
    companion object {
        val TOKEN_KIN = "kinXdEcpDQeHPEuQnqmUgtYykqKGVFq6CeVX5iAHJq6"
        val SAMPLE_WALLET = PublicKey("3rad7aFPdJS3CkYPSphtDAWCNB8BYpV2yc7o5ZjFQbDb") // (Pause For's mainnet hot wallet)
    }

    val network = NetworkingRouter(RPCEndpoint.mainnetBetaSolana, OkHttpClient())
    val storage = BasicAccountStorage(filesDir)
    val solana = Solana(network, storage)

    fun createAccount(callback: (String) -> Unit) {
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
}
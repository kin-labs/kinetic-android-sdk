package com.kinlabs.mogamiandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.solana.Solana
import com.solana.SolanaAccountStorage
import com.solana.api.getBalance
import com.solana.api.getTokenAccountBalance
import com.solana.api.getTokenAccountsByOwner
import com.solana.core.Account
import com.solana.core.DerivationPath
import com.solana.core.PublicKey
import com.solana.networking.NetworkingRouter
import com.solana.networking.RPCEndpoint
import okhttp3.OkHttpClient
import org.bitcoinj.core.NetworkParameters
import java.lang.Exception
import org.bitcoinj.wallet.Wallet

class MainActivity : AppCompatActivity() {
    private lateinit var addressText: TextView
    private lateinit var solBalanceText: TextView
    private lateinit var kinBalanceText: TextView
    private lateinit var createAccountButton: Button
    private lateinit var airdropButton: Button
    private lateinit var getBalanceButton: Button

    private val TAG = MainActivity::class.java.simpleName

    val KIN_MINT = PublicKey("kinXdEcpDQeHPEuQnqmUgtYykqKGVFq6CeVX5iAHJq6")
    val SAMPLE_WALLET = PublicKey("3rad7aFPdJS3CkYPSphtDAWCNB8BYpV2yc7o5ZjFQbDb") // (Pause For's mainnet hot wallet)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addressText = findViewById(R.id.address_text)
        solBalanceText = findViewById(R.id.sol_balance_text)
        kinBalanceText = findViewById(R.id.kin_balance_text)
        createAccountButton = findViewById(R.id.create_account_button)
        airdropButton = findViewById(R.id.airdrop_button)
        getBalanceButton = findViewById(R.id.get_balance_button)

        val network = NetworkingRouter(RPCEndpoint.mainnetBetaSolana, OkHttpClient())
        val storage = BasicAccountStorage()
        val solana = Solana(network, storage)

        createAccountButton.setOnClickListener {
            val networkParameters = NetworkParameters.fromID("org.bitcoin.production")
            Wallet(networkParameters).keyChainSeed.mnemonicCode?.let { mnemonic ->
                val account = Account.fromMnemonic(mnemonic, "", DerivationPath.BIP44_M_44H_501H_0H)
                Log.d(TAG, mnemonic.joinToString(" "))
                Log.d(TAG, account.publicKey.toBase58())
                storage.save(account)
                addressText.text = account.publicKey.toBase58()
            }
        }

        getBalanceButton.setOnClickListener {
            storage.account().getOrNull()?.let { account: Account ->
                // Get SOL balance
                solana.api.getBalance(SAMPLE_WALLET) { res ->
                    res.getOrNull()?.let { balance ->
                        solBalanceText.text = balance.toString()
                    }
                }
                // Get KIN balance
                // TODO in fork: Make this return an empty array instead of crashing when there are no token accounts
                // TODO in fork: Make this return all token accounts when there's more than one :/
                solana.api.getTokenAccountsByOwner(SAMPLE_WALLET, KIN_MINT) { res ->
                    res.getOrNull()?.let { tokenKey ->
                        solana.api.getTokenAccountBalance(tokenKey) { res ->
                            res.getOrNull()?.let {
                                kinBalanceText.text = it.uiAmountString
                            }
                        }
                    }
                }
            }
        }
    }
}

class BasicAccountStorage: SolanaAccountStorage {
    private var _account: Account? = null
    override fun account(): Result<Account> {
        return if (_account != null) {
            Log.d("TAG", _account!!.publicKey.toBase58())
            Result.success(_account!!)
        } else {
            Log.d("TAG","No account stored")
            Result.failure(Exception("No account stored"))
        }
    }

    override fun clear(): Result<Unit> {
        _account = null
        return Result.success(Unit)
    }

    override fun save(account: Account): Result<Unit> {
        _account = account
        return Result.success(Unit)
    }

}
package com.kinlabs.mogamiandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.solana.Solana
import com.solana.SolanaAccountStorage
import com.solana.core.Account
import com.solana.networking.NetworkingRouter
import com.solana.networking.RPCEndpoint
import okhttp3.OkHttpClient
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var addressText: TextView
    private lateinit var balanceText: TextView
    private lateinit var createAccountButton: Button
    private lateinit var airdropButton: Button
    private lateinit var getBalanceButton: Button

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addressText = findViewById(R.id.address_text)
        balanceText = findViewById(R.id.balance_text)
        createAccountButton = findViewById(R.id.create_account_button)
        airdropButton = findViewById(R.id.airdrop_button)
        getBalanceButton = findViewById(R.id.get_balance_button)

        val network = NetworkingRouter(RPCEndpoint.devnetSolana, OkHttpClient())
        val storage = BasicAccountStorage()
        val solana = Solana(network, storage)

        createAccountButton.setOnClickListener {
            val account = Account()
            // No mnemonic :/
            Log.d(TAG, account.publicKey.toBase58())
            Log.d(TAG, Arrays.toString(account.secretKey))
            storage.save(account)
        }


    }
}

class BasicAccountStorage: SolanaAccountStorage {
    private var _account: Account? = null
    override fun account(): Result<Account> {
        return if (_account != null) {
            Log.d("TAG", _account!!.toString())
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
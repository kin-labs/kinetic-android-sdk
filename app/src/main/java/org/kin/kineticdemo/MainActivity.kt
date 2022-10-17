package org.kin.kineticdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.kin.kinetic.Keypair
import org.kin.kinetic.KineticSdk
import org.kin.kinetic.LogLevel
import kotlinx.coroutines.*
import org.openapitools.client.models.Commitment

class MainActivity : AppCompatActivity() {
    private lateinit var getConfigButton: Button
    private lateinit var serverConfigText: TextView
    private lateinit var getBalanceButton: Button
    private lateinit var kinBalanceText: TextView
    private lateinit var getTokenAccountsButton: Button
    private lateinit var tokenAccountsText: TextView
    private lateinit var getAccountHistoryButton: Button
    private lateinit var accountHistoryText: TextView
    private lateinit var airdropButton: Button
    private lateinit var airdropText: TextView
    private lateinit var createAccountButton: Button
    private lateinit var createAccountText: TextView
    private lateinit var makeTransferButton: Button
    private lateinit var makeTransferText: TextView
    private lateinit var getTransactionButton: Button
    private lateinit var transactionText: TextView

    private var kinetic: KineticSdk? = null
    private var account: Keypair? = null
    private var storage: BasicAccountStorage? = null

    private val kineticNetworkScope = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kineticNetworkScope.launch {
            kinetic = KineticSdk.setup(
                "http://staging.kinetic.host",
                "devnet",
                1
            )
            storage = BasicAccountStorage(filesDir)
            account = storage!!.account()

            kinetic!!.logger.collect {
                // Handle logs how you prefer here.
                // Example: print each log from the Kin SDK to the console
                if (it.first == LogLevel.ERROR) {
                    Log.e("KinError", it.second)
                } else {
                    Log.d("KinLogs", it.second)
                }
            }
        }

        getConfigButton = findViewById(R.id.get_config_button)
        serverConfigText = findViewById(R.id.server_config_text)
        getBalanceButton = findViewById(R.id.get_balance_button)
        kinBalanceText = findViewById(R.id.kin_balance_text)
        getTokenAccountsButton = findViewById(R.id.get_token_accounts_button)
        tokenAccountsText = findViewById(R.id.token_accounts_text)
        getAccountHistoryButton = findViewById(R.id.get_account_history_button)
        accountHistoryText = findViewById(R.id.account_history_text)
        airdropButton = findViewById(R.id.airdrop_button)
        airdropText = findViewById(R.id.airdrop_text)
        createAccountButton = findViewById(R.id.create_account_button)
        createAccountText = findViewById(R.id.create_account_text)
        makeTransferButton = findViewById(R.id.make_transfer_button)
        makeTransferText = findViewById(R.id.make_transfer_text)
        getTransactionButton = findViewById(R.id.get_transaction_button)
        transactionText = findViewById(R.id.transaction_text)

        getConfigButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.init()
                runOnUiThread { serverConfigText.text = res.toString() }
            }
        }

        getBalanceButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.getBalance(account!!.publicKey)
                runOnUiThread { kinBalanceText.text = res.toString() }
            }
        }

        getTokenAccountsButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.getTokenAccounts(account!!.publicKey)
                runOnUiThread { tokenAccountsText.text = res.toString() }
            }
        }

        getAccountHistoryButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.getHistory(account!!.publicKey)
                runOnUiThread { accountHistoryText.text = res.toString() }
            }
        }

        airdropButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.requestAirdrop(account!!.publicKey)
                runOnUiThread { airdropText.text = res.toString() }
            }
        }

        createAccountButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.createAccount(owner = account!!)
                runOnUiThread { createAccountText.text = res.toString() }
            }
        }

        makeTransferButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.makeTransfer(
                    "1",
                    Commitment.confirmed,
                    "BobQoPqWy5cpFioy1dMTYqNH9WpC39mkAEDJWXECoJ9y",
                    null,
                    account!!
                )
                runOnUiThread { makeTransferText.text = res.toString() }
            }
        }

        getTransactionButton.setOnClickListener {
            kineticNetworkScope.launch {
                val res = kinetic?.getTransaction("testTXsignature")
                runOnUiThread { transactionText.text = res.toString() }
            }
        }
    }
}
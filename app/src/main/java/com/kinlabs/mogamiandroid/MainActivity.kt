package com.kinlabs.mogamiandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.kinlabs.mogami.KinBinaryMemo
import com.kinlabs.mogami.Mogami

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
    private lateinit var submitPaymentButton: Button
    private lateinit var paymentText: TextView
    private lateinit var memoButton: Button
    private lateinit var memoText: TextView

    var accountId: String = "3rad7aFPdJS3CkYPSphtDAWCNB8BYpV2yc7o5ZjFQbDb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mogami = Mogami(filesDir)

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
        submitPaymentButton = findViewById(R.id.submit_payment_button)
        paymentText = findViewById(R.id.payment_text)
        memoButton = findViewById(R.id.memo_button)
        memoText = findViewById(R.id.memo_text)

        getConfigButton.setOnClickListener {
            mogami.getServerConfig { configSummary ->
                runOnUiThread {
                    serverConfigText.text = configSummary.toString()
                }
            }
        }

        getBalanceButton.setOnClickListener {
            mogami.getBalance(accountId) { balance ->
                runOnUiThread {
                    kinBalanceText.text = balance
                }
            }
//            // Get SOL balance
//            mogami.getSolBalance() { balance ->
//                solBalanceText.text = balance.toString()
//            }
//            // Get KIN balance
//            // TODO in fork: Make this return an empty array instead of crashing when there are no token accounts
//            // TODO in fork: Make this return all token accounts when there's more than one :/
//            mogami.getSPLBalance(Mogami.TOKEN_KIN) { balance ->
//                kinBalanceText.text = balance
//            }
        }

        getTokenAccountsButton.setOnClickListener {
            mogami.getTokenAccounts(accountId) {
                runOnUiThread {
                    tokenAccountsText.text = it.toString()
                }
            }
        }

        getAccountHistoryButton.setOnClickListener {
            mogami.getAccountHistory(accountId) {
                runOnUiThread {
                    accountHistoryText.text = it.toString()
                }
            }
        }

        airdropButton.setOnClickListener {
            mogami.requestAirdrop(accountId, 100) {
                runOnUiThread {
                    airdropText.text = it
                }
            }
        }

        createAccountButton.setOnClickListener {
            mogami.createAccount { response ->
                runOnUiThread {
                    createAccountText.text = response.toString()
                }
            }
        }

        submitPaymentButton.setOnClickListener {
            mogami.submitPayment(accountId) {

            }
        }

        memoButton.setOnClickListener {
            val memo = KinBinaryMemo.Builder(124)
                .setTransferType(KinBinaryMemo.TransferType.P2P)
                .build()

            memoText.text =
                memo.toString() + "\n\n" + Base64.encodeToString(memo.encode(), Base64.DEFAULT)
        }
    }
}
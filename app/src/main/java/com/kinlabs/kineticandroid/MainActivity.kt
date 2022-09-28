package com.kinlabs.kineticandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.TextView
import com.kinlabs.kinetic.KinBinaryMemo
import com.kinlabs.kinetic.Kinetic
import com.kinlabs.kinetic.KineticAccount
import com.kinlabs.kinetic.PublicKey

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

    private lateinit var kinetic: Kinetic
    private lateinit var account: KineticAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Kinetic.Builder(
            filesDir,
            "devnet",
            1,
                "http://10.0.2.2:3000"
//            "https://staging.kinetic.host"
        ).build { kinetic: Kinetic ->
            this.kinetic = kinetic
            kinetic.createAccountDirect {
                account = it
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
        submitPaymentButton = findViewById(R.id.submit_payment_button)
        paymentText = findViewById(R.id.payment_text)
        memoButton = findViewById(R.id.memo_button)
        memoText = findViewById(R.id.memo_text)

        getConfigButton.setOnClickListener {
            kinetic.getAppConfig { configSummary ->
                runOnUiThread {
                    serverConfigText.text = "Got it"// configSummary.toString()
                }
            }
        }

        getBalanceButton.setOnClickListener {
            kinetic.getBalance(account.publicKey.toBase58()) { balance ->
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
            kinetic.getTokenAccounts(account.publicKey.toBase58()) {
                runOnUiThread {
                    tokenAccountsText.text = it.toString()
                }
            }
        }

        getAccountHistoryButton.setOnClickListener {
            kinetic.getAccountHistory(account.publicKey.toBase58()) {
                runOnUiThread {
                    accountHistoryText.text = it.toString()
                }
            }
        }

        airdropButton.setOnClickListener {
            kinetic.requestAirdrop(account.publicKey.toBase58(), 100) {
                runOnUiThread {
                    airdropText.text = it
                }
            }
        }

        createAccountButton.setOnClickListener {
            kinetic.createAccount(account) { response ->
                runOnUiThread {
                    createAccountText.text = response.toString()
                }
            }
        }

        submitPaymentButton.setOnClickListener {
            kinetic.submitPayment(account, "3rad7aFPdJS3CkYPSphtDAWCNB8BYpV2yc7o5ZjFQbDb") {

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
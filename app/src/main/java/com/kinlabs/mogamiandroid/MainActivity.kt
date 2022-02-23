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
    private lateinit var addressText: TextView
    private lateinit var solBalanceText: TextView
    private lateinit var kinBalanceText: TextView
    private lateinit var createAccountButton: Button
    private lateinit var memoButton: Button
    private lateinit var getBalanceButton: Button
    private lateinit var memoText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mogami = Mogami(filesDir)

        addressText = findViewById(R.id.address_text)
        solBalanceText = findViewById(R.id.sol_balance_text)
        kinBalanceText = findViewById(R.id.kin_balance_text)
        createAccountButton = findViewById(R.id.create_account_button)
        memoButton = findViewById(R.id.memo_button)
        getBalanceButton = findViewById(R.id.get_balance_button)
        memoText = findViewById(R.id.memo_text)

        createAccountButton.setOnClickListener {
            mogami.createAccount { account ->
                addressText.text = account
            }
        }

        memoButton.setOnClickListener {
            val memo = KinBinaryMemo.Builder(124)
                .setTransferType(KinBinaryMemo.TransferType.P2P)
                .build()

            memoText.text = memo.toString() + "\n\n" + Base64.encodeToString(memo.encode(), Base64.DEFAULT)
        }

        getBalanceButton.setOnClickListener {
            // Get SOL balance
            mogami.getSolBalance() { balance ->
                solBalanceText.text = balance.toString()
            }
            // Get KIN balance
            // TODO in fork: Make this return an empty array instead of crashing when there are no token accounts
            // TODO in fork: Make this return all token accounts when there's more than one :/
            mogami.getSPLBalance(Mogami.TOKEN_KIN) { balance ->
                kinBalanceText.text = balance
            }
        }
    }
}
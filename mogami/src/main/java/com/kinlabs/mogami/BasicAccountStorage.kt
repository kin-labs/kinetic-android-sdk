package com.kinlabs.mogami

import android.util.Log
import com.solana.SolanaAccountStorage
import com.solana.core.Account

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
package com.kinlabs.mogami

import android.util.Log
import com.solana.SolanaAccountStorage
import com.solana.core.Account
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class BasicAccountStorage(filesDir: File): SolanaAccountStorage {
    private var _account: Account? = null
    private var _filesDir: String = filesDir.absolutePath + "/mogami/"

    override fun account(): Result<Account> {
        return if (_account != null) {
            Log.d("TAG", "Account in mem: " + _account!!.publicKey.toBase58())
            Result.success(_account!!)
        } else {
            val accounts = getAllAccounts()
            if (!accounts.isEmpty()) {
                val pkey = readFile(_filesDir, accounts[0] + ".key")
                _account = Account(pkey)
                Log.d("TAG", "Account in storage: " + _account!!.publicKey.toBase58())
                Result.success(_account!!)
            } else {
                Log.d("TAG", "No account stored")
                Result.failure(Exception("No account stored"))
            }
        }
    }

    override fun clear(): Result<Unit> {
        _account = null
        return Result.success(Unit)
    }

    override fun save(account: Account): Result<Unit> {
        writeToFile(_filesDir, account.publicKey.toBase58() + ".key", account.secretKey)
        _account = account
        return Result.success(Unit)
    }

    private fun getAllAccounts(): List<String> {
        val accountDir = File(_filesDir)
        if (!accountDir.exists() || !accountDir.isDirectory) {
            return emptyList()
        }
        val accountDirectories = accountDir.list().asList()
            .map { name -> _filesDir + name }

        val accounts = accountDirectories
            .map { directory -> directory.split("/").last().split(".")[0] }

        return accounts
    }

    private fun writeToFile(directory: String, fileName: String, body: ByteArray): Boolean {
        var outputStream: FileOutputStream? = null
        return try {
            val file = File(directory, fileName)
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }

            if (!file.exists()) {
                file.createNewFile()
            }

            outputStream = FileOutputStream(file)
            outputStream.write(body)

            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } finally {
            outputStream?.close()
        }
    }

    private fun readFile(directory: String, fileName: String): ByteArray {
        var inputStream: FileInputStream? = null

        val file = File(directory, fileName)
        if (!file.exists()) {
            return ByteArray(0)
        }

        return try {
            inputStream = FileInputStream(file)
            inputStream.readBytes()

        } catch (e: IOException) {
            e.printStackTrace()
            return ByteArray(0)

        } finally {
            inputStream?.close()
        }
    }
}
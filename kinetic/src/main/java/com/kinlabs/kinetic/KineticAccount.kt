package com.kinlabs.kinetic

import com.google.gson.Gson
import com.solana.core.Account
import com.solana.core.DerivationPath
import com.solana.core.HotAccount
import com.solana.vendor.TweetNaclFast
import com.solana.vendor.bip32.wallet.DerivableType
import com.solana.vendor.bip32.wallet.SolanaBip44
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.HDUtils
import org.bitcoinj.crypto.MnemonicCode
import org.bitcoinj.wallet.Wallet
import java.nio.ByteBuffer
import java.util.*


class KineticAccount {
    private var keyPair: TweetNaclFast.Signature.KeyPair
    var mnemonic: List<String>? = null
    internal var solanaAccount: HotAccount

    constructor(mnemonic: List<String>? = null, passphrase: String = "") {
        val solanaBip44 = SolanaBip44()
        if (mnemonic.isNullOrEmpty()) {
            val networkParameters = NetworkParameters.fromID("org.bitcoin.production")
            val newMnemonic = Wallet(networkParameters).keyChainSeed.mnemonicCode
            val seed = MnemonicCode.toSeed(newMnemonic, passphrase)
            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44)
            keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)
            this.mnemonic = newMnemonic
            this.solanaAccount = HotAccount(keyPair.secretKey)
        } else {
            val seed = MnemonicCode.toSeed(mnemonic, passphrase)
            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44)
            keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)
            this.mnemonic = mnemonic
            this.solanaAccount = HotAccount(keyPair.secretKey)
        }
    }

    constructor(secretKey: ByteArray) {
        keyPair = TweetNaclFast.Signature.keyPair_fromSecretKey(secretKey)
        this.solanaAccount = HotAccount(keyPair.secretKey)
    }

    constructor(json: String) {
        val account = Gson().fromJson(json, KineticAccount::class.java)
        keyPair = account.keyPair
        mnemonic = account.mnemonic
        solanaAccount = HotAccount(keyPair.secretKey)
    }

    private constructor(keyPair: TweetNaclFast.Signature.KeyPair) {
        this.keyPair = keyPair
        this.solanaAccount = HotAccount(keyPair.secretKey)
    }

    val publicKey: com.kinlabs.kinetic.PublicKey
        get() = com.kinlabs.kinetic.PublicKey(keyPair.publicKey)

    val secretKey: ByteArray
        get() = keyPair.secretKey

    fun toJson(): String {
        return Gson().toJson(this)
    }
}
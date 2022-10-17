package org.kin.kinetic

import com.google.gson.Gson
import com.solana.core.HotAccount
import com.solana.vendor.TweetNaclFast
import com.solana.vendor.bip32.wallet.DerivableType
import com.solana.vendor.bip32.wallet.SolanaBip44
import org.bitcoinj.core.Base58
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.crypto.MnemonicCode
import org.bitcoinj.wallet.Wallet


class Keypair {
    private var keyPair: TweetNaclFast.Signature.KeyPair
    var mnemonic: List<String>? = null
    internal var solanaKeypair: HotAccount

    val publicKey: String
        get() = PublicKey(keyPair.publicKey).toBase58()

    val secretKey: String?
        get() = Base58.encode(keyPair.secretKey)

    val solana: HotAccount
        get() = solanaKeypair

    val solanaPublicKey: com.solana.core.PublicKey
        get() = solanaKeypair.publicKey

    val solanaSecretKey: ByteArray
        get() = keyPair.secretKey

    constructor(secretKey: String) {
        val bytes = Base58.decode(secretKey)
        this.keyPair = TweetNaclFast.Signature.keyPair_fromSecretKey(bytes)
        this.solanaKeypair = HotAccount(this.keyPair.secretKey)
    }

    private constructor(keyPair: TweetNaclFast.Signature.KeyPair) {
        this.keyPair = keyPair
        this.solanaKeypair = HotAccount(keyPair.secretKey)
    }

    companion object {
        fun fromByteArray(byteArray: ByteArray): Keypair {
            return fromSecretKey(Base58.encode(byteArray))
        }

//        fun fromMnemonicSeed(mnemonic: List<String>): Keypair {
//            TODO()
//        }

        fun fromMnemonic(mnemonic: List<String>): Keypair {
            val seed = MnemonicCode.toSeed(mnemonic, "")
            val solanaBip44 = SolanaBip44()
            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44)
            val keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)
            val kp = Keypair(keyPair)
            kp.mnemonic = mnemonic
            return kp
//            return this.fromMnemonicSet(mnemonic)[0]
        }

        // TODO: Not implemented, need update to SolanaKT to allow passing in account number
//        fun fromMnemonicSet(mnemonic: List<String>, from: Int = 0, to: Int = 10): List<Keypair> {
//            val from = if (from < 0) 0 else from
//            val to = if (to <= from) from + 1 else to
//
//            val solanaBip44 = SolanaBip44()
//            val seed = MnemonicCode.toSeed(mnemonic, "")
//            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44CHANGE)
//            this.keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)
//            this.mnemonic = mnemonic
//            this.solanaKeypair = HotAccount(keyPair.secretKey)
//
//            var keys = emptyList<Keypair>()
//
//            for (i in from..to) {
//                Bip44
//            }
//        }

//        fun derive(seed: ByteArray, walletIndex: Int): Keypair {
//            val solanaBip44 = SolanaBip44()
//            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44)
//            val kp = Keypair(Base58.encode(privateKey))
//            return kp
//        }

//        fun fromSeed(seed: ByteArray): Keypair {
//            return Keypair.derive(seed, 0)
//        }

        fun fromSecretKey(secretKey: String): Keypair {
            return Keypair(secretKey)
        }

        fun random(): Keypair {
            val mnemonic = generateMnemonic()
            return fromMnemonic(mnemonic)
        }

        fun generateMnemonic(strength: Int = 128): List<String> {
            val networkParameters = NetworkParameters.fromID("org.bitcoin.production")
            return Wallet(networkParameters).keyChainSeed.mnemonicCode!!
        }

        fun fromJson(json: String): Keypair {
            val account = Gson().fromJson(json, Keypair::class.java)
            return Keypair(account.secretKey!!)
        }
    }


    fun toJson(): String {
        return Gson().toJson(this)
    }
}
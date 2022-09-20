package com.kinlabs.kinetic

import com.solana.core.Account
import com.solana.core.DerivationPath
import com.solana.core.HotAccount
import com.solana.core.PublicKey
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

class KineticAccount: Account {
    private var keyPair: TweetNaclFast.Signature.KeyPair
    var mnemonic: List<String>? = null

    constructor(mnemonic: List<String>? = null, passphrase: String = "") {
        val solanaBip44 = SolanaBip44()
        if (mnemonic.isNullOrEmpty()) {
            val networkParameters = NetworkParameters.fromID("org.bitcoin.production")
            val newMnemonic = Wallet(networkParameters).keyChainSeed.mnemonicCode
            val seed = MnemonicCode.toSeed(newMnemonic, passphrase)
            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44)
            keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)
            this.mnemonic = newMnemonic
        } else {
            val seed = MnemonicCode.toSeed(mnemonic, passphrase)
            val privateKey = solanaBip44.getPrivateKeyFromSeed(seed, DerivableType.BIP44)
            keyPair = TweetNaclFast.Signature.keyPair_fromSeed(privateKey)
            this.mnemonic = mnemonic
        }
    }

    constructor(secretKey: ByteArray) {
        keyPair = TweetNaclFast.Signature.keyPair_fromSecretKey(secretKey)
    }

    private constructor(keyPair: TweetNaclFast.Signature.KeyPair) {
        this.keyPair = keyPair
    }

    override val publicKey: PublicKey
        get() = PublicKey(keyPair.publicKey)

    override fun sign(serializedMessage: ByteArray): ByteArray {
        val signatureProvider = TweetNaclFast.Signature(ByteArray(0), secretKey)
        return signatureProvider.detached(serializedMessage)
    }

    val secretKey: ByteArray
        get() = keyPair.secretKey

    companion object {
        /**
         * Creates an [HotAccount] object from a Sollet-exported JSON string (array)
         * @param json Sollet-exported JSON string (array)
         * @return [HotAccount] built from Sollet-exported private key
         */
        fun fromJson(json: String): KineticAccount {
            return KineticAccount(convertJsonStringToByteArray(json))
        }

        /**
         * Convert's a Sollet-exported JSON string into a byte array usable for [HotAccount] instantiation
         * @param characters Sollet-exported JSON string
         * @return byte array usable in [HotAccount] instantiation
         */
        private fun convertJsonStringToByteArray(characters: String): ByteArray {
            // Create resulting byte array
            val buffer = ByteBuffer.allocate(64)

            // Convert json array into String array
            val sanitizedJson = characters.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
            val chars = sanitizedJson.split(",").toTypedArray()

            // Convert each String character into byte and put it in the buffer
            Arrays.stream(chars).forEach { character: String ->
                val byteValue = character.toInt().toByte()
                buffer.put(byteValue)
            }
            return buffer.array()
        }
    }
}
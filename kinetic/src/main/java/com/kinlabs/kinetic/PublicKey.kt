package com.kinlabs.kinetic

import com.solana.core.PublicKey
import org.bitcoinj.core.Base58
import java.util.*

class PublicKey internal constructor(internal val solanaPublicKey: PublicKey) {
    constructor(byteArray: ByteArray) : this(PublicKey(pubkey = byteArray))
    constructor(base58: String) : this(PublicKey(pubkeyString = base58))

    fun toByteArray(): ByteArray {
        return solanaPublicKey.pubkey
    }

    fun toBase58(): String {
        return Base58.encode(solanaPublicKey.pubkey)
    }

    fun equals(pubkey: PublicKey): Boolean {
        return Arrays.equals(this.solanaPublicKey.pubkey, pubkey.toByteArray())
    }

    override fun toString(): String {
        return toBase58()
    }
}
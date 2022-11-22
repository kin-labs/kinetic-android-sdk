package org.kin.kinetic.keypair

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.kin.kinetic.Keypair
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_12
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_12_KEYPAIR
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_24
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_24_KEYPAIR
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_PUBLIC_KEY
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_SECRET_BYTEARRAY

class KeypairTest {

    /**
     * should generate 1 keypair
     */
    @Test
    fun generateKeypair() {
        val kp = Keypair.random()

        assertNotNull(kp)
        assertNotNull(kp.publicKey)
        assertNotNull(kp.secretKey)
        assertNotNull(kp.solana)
        assertNotNull(kp.mnemonic)
    }

    /**
     * should generate 1 keypair from mnemonic
     */
    @Test
    fun generateKeypairFromMnemonic() {
        val kp = Keypair.random()
        val restored = Keypair.fromMnemonic(kp.mnemonic!!)

        assertEquals(restored.mnemonic, kp.mnemonic)
        assertEquals(restored.secretKey, kp.secretKey)
        assertEquals(restored.publicKey, kp.publicKey)
    }

    /**
     * should import a mnemonic (12 chars) and get 1 keypair
     */
    @Test
    fun generateKeypairFromMnemonic12() {
        val kp = Keypair.fromMnemonic(TEST_MNEMONIC_12)

        assertEquals(kp.publicKey, TEST_MNEMONIC_12_KEYPAIR.publicKey)
        assertEquals(kp.secretKey, TEST_MNEMONIC_12_KEYPAIR.secretKey)
    }

    /**
     * TODO: should import a mnemonic (24 chars) and get 1 keypair
     * 24 words mnemonic is currently not supported by the library
     */



    /**
     * should generate 1 keypair from mnemonic
     */
    @Test
    fun createAndImportKeypair() {
        val kp = Keypair.random()
        val restored = Keypair.fromSecretKey(kp.secretKey!!)

        assertEquals(restored.mnemonic, null)
        assertEquals(restored.secretKey, kp.secretKey)
        assertEquals(restored.publicKey, kp.publicKey)
    }

    /**
     * should generate 1 keypair from byte array
     */
    @Test
    fun createAndImportByteArray() {
        // TODO: Fix test
//        val restored = Keypair.fromByteArray(TEST_SECRET_BYTEARRAY.map { it.toByte() }.toByteArray())
//        assertEquals(restored.publicKey, TEST_PUBLIC_KEY)
    }
}
package org.kin.kinetic.keypair

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.kin.kinetic.Keypair
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_12
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_12_KEYPAIR
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_24
import org.kin.kinetic.keypair.KeypairFixtures.Companion.TEST_MNEMONIC_24_KEYPAIR

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
        val restored = Keypair.fromMnemonicSet(kp.mnemonic!!)

        assertEquals(restored.mnemonic, kp.mnemonic)
        assertEquals(restored.secretKey, kp.secretKey)
        assertEquals(restored.publicKey, kp.publicKey)
    }

    /**
     * should import a mnemonic (12 chars) and get 1 keypair
     */
    @Test
    fun generateKeypairFromMnemonic12() {
        val kp = Keypair.fromMnemonicSet(TEST_MNEMONIC_12)
        val kpSecret = Keypair.fromMnemonicSeed(TEST_MNEMONIC_12)

        assertEquals(kp.publicKey, TEST_MNEMONIC_12_KEYPAIR.publicKey)
        assertEquals(kpSecret.publicKey, TEST_MNEMONIC_12_KEYPAIR.publicKey)
        assertEquals(kp.secretKey, TEST_MNEMONIC_12_KEYPAIR.secretKey)
        assertEquals(kpSecret.secretKey, TEST_MNEMONIC_12_KEYPAIR.secretKey)
    }

    /**
     * should import a mnemonic (24 chars) and get 1 keypair
     */
    @Test
    fun generateKeypairFromMnemonic24() {
        val kp = Keypair.fromMnemonicSet(TEST_MNEMONIC_24)
        val kpSecret = Keypair.fromMnemonicSeed(TEST_MNEMONIC_24)

        assertEquals(kp.mnemonic, TEST_MNEMONIC_24_KEYPAIR.mnemonic)
        assertEquals(kpSecret.mnemonic, TEST_MNEMONIC_24_KEYPAIR.mnemonic)
        assertEquals(kp.publicKey, TEST_MNEMONIC_24_KEYPAIR.publicKey)
        assertEquals(kpSecret.publicKey, TEST_MNEMONIC_24_KEYPAIR.publicKey)
        assertEquals(kp.secretKey, TEST_MNEMONIC_24_KEYPAIR.secretKey)
        assertEquals(kpSecret.secretKey, TEST_MNEMONIC_24_KEYPAIR.secretKey)
    }
}
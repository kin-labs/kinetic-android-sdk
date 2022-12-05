package org.kin.kinetic

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class KineticSdkTest {
    val TEST_APP_INDEX = 1
    val TEST_APP_ENVIRONMENT = "devnet"
    val TEST_APP_ENDPOINT = "http://localhost:3000"
    val TEST_SOLANA_RPC_NAME = "mainnet-beta"
    val TEST_SOLANA_RPC_ENDPOINT = "https://api.mainnet-beta.solana.com/"

    @ExperimentalCoroutinesApi
    @Test
    fun initialize_isCorrect(): Unit = runBlocking {
        val sdk = KineticSdk.setup(
            TEST_APP_ENDPOINT,
            TEST_APP_ENVIRONMENT,
            TEST_APP_INDEX
        )
        assertEquals(sdk.environment, TEST_APP_ENVIRONMENT)
        assertEquals(sdk.endpoint, TEST_APP_ENDPOINT)
        assertEquals(sdk.index, TEST_APP_INDEX)
    }
}
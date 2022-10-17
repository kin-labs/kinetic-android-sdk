package org.kin.kinetic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

class KineticSdkTest {
    val TEST_APP_INDEX = 1
    val TEST_APP_ENVIRONMENT = "devnet"
    val TEST_APP_ENDPOINT = "http://localhost:3000"
    val TEST_SOLANA_RPC_NAME = "mainnet-beta"
    val TEST_SOLANA_RPC_ENDPOINT = "https://api.mainnet-beta.solana.com/"
    val testNetworkScope = CoroutineScope(Dispatchers.IO)

    @Test
    fun initialize_isCorrect() {
        testNetworkScope.launch {
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
}
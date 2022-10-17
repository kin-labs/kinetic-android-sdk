package org.kin.kinetic.helpers

import com.solana.networking.Network
import com.solana.networking.RPCEndpoint
import java.net.URL

fun getSolanaRPCEndpoint(endpoint: String): RPCEndpoint {
    return when (endpoint) {
        "devnet" -> RPCEndpoint.devnetSolana
        "testnet" -> RPCEndpoint.testnetSolana
        "mainnet", "mainnet-beta" -> RPCEndpoint.mainnetBetaSolana
        else -> {
            val webSocketString = endpoint.replace("https", "wss").replace("http", "wss")
            return RPCEndpoint.custom(URL(endpoint), URL(webSocketString), Network.mainnetBeta)
        }
    }
}
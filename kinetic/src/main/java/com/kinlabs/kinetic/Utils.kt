package com.kinlabs.kinetic

import com.solana.networking.RPCEndpoint

fun getSolanaRPCEndpoint(environment: String): RPCEndpoint {
    return when (environment) {
        "devnet" -> RPCEndpoint.devnetSolana
        "testnet" -> RPCEndpoint.testnetSolana
        "mainnet", "mainnet-beta" -> RPCEndpoint.mainnetBetaSolana
        else -> RPCEndpoint.devnetSolana
    }
}
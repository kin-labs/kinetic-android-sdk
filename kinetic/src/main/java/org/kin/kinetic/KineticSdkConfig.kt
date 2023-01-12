package org.kin.kinetic

data class KineticSdkConfig(
    val endpoint: String,
    val environment: String,
    val index: Int,
    val headers: Map<String, String> = emptyMap(),
    val solanaRpcEndpoint: String? = null,
)
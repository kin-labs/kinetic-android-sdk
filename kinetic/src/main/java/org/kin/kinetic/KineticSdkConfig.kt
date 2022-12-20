package org.kin.kinetic

import org.kin.kinetic.generated.api.model.Commitment

data class KineticSdkConfig(
    val endpoint: String,
    val environment: String,
    val index: Int,
    val headers: Map<String, String> = emptyMap(),
    val commitment: Commitment? = null,
    val solanaRpcEndpoint: String? = null,
)
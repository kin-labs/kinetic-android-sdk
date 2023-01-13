package org.kin.kinetic.helpers

import org.kin.kinetic.KineticSdkConfig
import org.kin.kinetic.generated.api.model.Commitment

fun validateKineticSdkConfig(sdkConfig: KineticSdkConfig): KineticSdkConfig {
    if (!sdkConfig.endpoint.startsWith("http")) {
        throw RuntimeException("validateKineticSdkConfig: the endpoint should start with http or https.")
    }
    val endpoint = sdkConfig.endpoint.removeSuffix("/")

    return KineticSdkConfig(
        commitment = sdkConfig.commitment,
        endpoint = endpoint,
        environment = sdkConfig.environment,
        index = sdkConfig.index,
        headers = sdkConfig.headers,
        solanaRpcEndpoint = sdkConfig.solanaRpcEndpoint
    )
}
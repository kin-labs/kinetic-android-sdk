/**
 * Mogami
 *
 * The Mogami API description
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models


import com.squareup.moshi.Json

/**
 * 
 *
 * @param port 
 * @param environment 
 * @param solanaRpcEndpoint 
 */

data class ApiConfigSummary (

    @Json(name = "port")
    val port: java.math.BigDecimal,

    @Json(name = "environment")
    val environment: kotlin.String,

    @Json(name = "solanaRpcEndpoint")
    val solanaRpcEndpoint: kotlin.String

)

/**
 * @kin-kinetic/api
 *
 * The OpenAPI definition of the Kinetic API
 *
 * The version of the OpenAPI document: 1.0.0-rc.3
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

import org.openapitools.client.models.ConfirmedSignatureInfo

import com.squareup.moshi.Json

/**
 * 
 *
 * @param account 
 * @param history 
 */

data class HistoryResponse (

    @Json(name = "account")
    val account: kotlin.String,

    @Json(name = "history")
    val history: kotlin.collections.List<ConfirmedSignatureInfo>

)


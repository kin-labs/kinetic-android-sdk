/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.kin.kinetic.generated.api.model


import com.squareup.moshi.Json

/**
 * 
 *
 * @param amount 
 * @param decimals 
 * @param uiAmount 
 * @param uiAmountString 
 */

data class TokenAmount (

    @Json(name = "amount")
    val amount: kotlin.String,

    @Json(name = "decimals")
    val decimals: kotlin.Int,

    @Json(name = "uiAmount")
    val uiAmount: kotlin.Int? = null,

    @Json(name = "uiAmountString")
    val uiAmountString: kotlin.String? = null

)


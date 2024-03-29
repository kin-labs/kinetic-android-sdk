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

import org.kin.kinetic.generated.api.model.TokenInfo

import com.squareup.moshi.Json

/**
 * 
 *
 * @param account 
 * @param isMint 
 * @param isOwner 
 * @param isTokenAccount 
 * @param owner 
 * @param program 
 * @param tokens 
 */

data class AccountInfo (

    @Json(name = "account")
    val account: kotlin.String,

    @Json(name = "isMint")
    val isMint: kotlin.Boolean,

    @Json(name = "isOwner")
    val isOwner: kotlin.Boolean,

    @Json(name = "isTokenAccount")
    val isTokenAccount: kotlin.Boolean,

    @Json(name = "owner")
    val owner: kotlin.String? = null,

    @Json(name = "program")
    val program: kotlin.String? = null,

    @Json(name = "tokens")
    val tokens: kotlin.collections.List<TokenInfo>? = null

)


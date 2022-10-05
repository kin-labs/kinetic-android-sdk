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

import org.openapitools.client.models.AppConfigApi
import org.openapitools.client.models.AppConfigApp
import org.openapitools.client.models.AppConfigEnvironment
import org.openapitools.client.models.AppConfigMint

import com.squareup.moshi.Json

/**
 * 
 *
 * @param app 
 * @param api 
 * @param environment 
 * @param mint 
 * @param mints 
 */

data class AppConfig (

    @Json(name = "app")
    val app: AppConfigApp,

    @Json(name = "api")
    val api: AppConfigApi,

    @Json(name = "environment")
    val environment: AppConfigEnvironment,

    @Json(name = "mint")
    val mint: AppConfigMint,

    @Json(name = "mints")
    val mints: kotlin.collections.List<AppConfigMint>

)


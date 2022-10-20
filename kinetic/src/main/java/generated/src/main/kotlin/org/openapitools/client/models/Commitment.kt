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


import com.squareup.moshi.Json

/**
 * 
 *
 * Values: confirmed,finalized,processed
 */

enum class Commitment(val value: kotlin.String) {

    @Json(name = "Confirmed")
    confirmed("Confirmed"),

    @Json(name = "Finalized")
    finalized("Finalized"),

    @Json(name = "Processed")
    processed("Processed");

    /**
     * Override toString() to avoid using the enum variable name as the value, and instead use
     * the actual value defined in the API spec file.
     *
     * This solves a problem when the variable name and its value are different, and ensures that
     * the client sends the correct enum values to the server always.
     */
    override fun toString(): String = value

    companion object {
        /**
         * Converts the provided [data] to a [String] on success, null otherwise.
         */
        fun encode(data: Any?): kotlin.String? = if (data is Commitment) "$data" else null

        /**
         * Returns a valid [Commitment] for [data], null otherwise.
         */
        fun decode(data: Any?): Commitment? = data?.let {
          val normalizedData = "$it".lowercase()
          values().firstOrNull { value ->
            it == value || normalizedData == "$value".lowercase()
          }
        }
    }
}

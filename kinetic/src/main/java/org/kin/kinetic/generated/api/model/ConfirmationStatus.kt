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
 * Values: processed,confirmed,finalized
 */

enum class ConfirmationStatus(val value: kotlin.String) {

    @Json(name = "processed")
    processed("processed"),

    @Json(name = "confirmed")
    confirmed("confirmed"),

    @Json(name = "finalized")
    finalized("finalized");

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
        fun encode(data: kotlin.Any?): kotlin.String? = if (data is ConfirmationStatus) "$data" else null

        /**
         * Returns a valid [ConfirmationStatus] for [data], null otherwise.
         */
        fun decode(data: kotlin.Any?): ConfirmationStatus? = data?.let {
          val normalizedData = "$it".lowercase()
          values().firstOrNull { value ->
            it == value || normalizedData == "$value".lowercase()
          }
        }
    }
}


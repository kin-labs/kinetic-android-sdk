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

package org.kin.kinetic.generated.api

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.HttpUrl

import org.kin.kinetic.generated.api.model.AppConfig
import org.kin.kinetic.generated.api.model.AppHealth

import com.squareup.moshi.Json

import org.kin.kinetic.generated.infrastructure.ApiClient
import org.kin.kinetic.generated.infrastructure.ApiResponse
import org.kin.kinetic.generated.infrastructure.ClientException
import org.kin.kinetic.generated.infrastructure.ClientError
import org.kin.kinetic.generated.infrastructure.ServerException
import org.kin.kinetic.generated.infrastructure.ServerError
import org.kin.kinetic.generated.infrastructure.MultiValueMap
import org.kin.kinetic.generated.infrastructure.PartConfig
import org.kin.kinetic.generated.infrastructure.RequestConfig
import org.kin.kinetic.generated.infrastructure.RequestMethod
import org.kin.kinetic.generated.infrastructure.ResponseType
import org.kin.kinetic.generated.infrastructure.Success
import org.kin.kinetic.generated.infrastructure.toMultiValue

class AppApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient, headers: Map<String, String> = mapOf()) : ApiClient(basePath, client, headers) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost:3000")
        }
    }

    /**
     * 
     * 
     * @param environment 
     * @param index 
     * @return AppConfig
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getAppConfig(environment: kotlin.String, index: kotlin.Int) : AppConfig {
        val localVarResponse = getAppConfigWithHttpInfo(environment = environment, index = index)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as AppConfig
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * 
     * @param environment 
     * @param index 
     * @return ApiResponse<AppConfig?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getAppConfigWithHttpInfo(environment: kotlin.String, index: kotlin.Int) : ApiResponse<AppConfig?> {
        val localVariableConfig = getAppConfigRequestConfig(environment = environment, index = index)

        return request<Unit, AppConfig>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getAppConfig
     *
     * @param environment 
     * @param index 
     * @return RequestConfig
     */
    fun getAppConfigRequestConfig(environment: kotlin.String, index: kotlin.Int) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/app/{environment}/{index}/config".replace("{"+"environment"+"}", encodeURIComponent(environment.toString())).replace("{"+"index"+"}", encodeURIComponent(index.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
     * 
     * 
     * @param environment 
     * @param index 
     * @return AppHealth
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getAppHealth(environment: kotlin.String, index: kotlin.Int) : AppHealth {
        val localVarResponse = getAppHealthWithHttpInfo(environment = environment, index = index)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as AppHealth
            ResponseType.Informational -> throw UnsupportedOperationException("Client does not support Informational responses.")
            ResponseType.Redirection -> throw UnsupportedOperationException("Client does not support Redirection responses.")
            ResponseType.ClientError -> {
                val localVarError = localVarResponse as ClientError<*>
                throw ClientException("Client error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
            ResponseType.ServerError -> {
                val localVarError = localVarResponse as ServerError<*>
                throw ServerException("Server error : ${localVarError.statusCode} ${localVarError.message.orEmpty()}", localVarError.statusCode, localVarResponse)
            }
        }
    }

    /**
     * 
     * 
     * @param environment 
     * @param index 
     * @return ApiResponse<AppHealth?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getAppHealthWithHttpInfo(environment: kotlin.String, index: kotlin.Int) : ApiResponse<AppHealth?> {
        val localVariableConfig = getAppHealthRequestConfig(environment = environment, index = index)

        return request<Unit, AppHealth>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getAppHealth
     *
     * @param environment 
     * @param index 
     * @return RequestConfig
     */
    fun getAppHealthRequestConfig(environment: kotlin.String, index: kotlin.Int) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/app/{environment}/{index}/health".replace("{"+"environment"+"}", encodeURIComponent(environment.toString())).replace("{"+"index"+"}", encodeURIComponent(index.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}
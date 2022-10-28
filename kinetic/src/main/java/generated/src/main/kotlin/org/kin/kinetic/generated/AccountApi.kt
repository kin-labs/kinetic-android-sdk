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

package org.kin.kinetic.generated

import java.io.IOException
import okhttp3.OkHttpClient
import okhttp3.HttpUrl

import org.openapitools.client.models.BalanceResponse
import org.openapitools.client.models.CreateAccountRequest
import org.openapitools.client.models.HistoryResponse
import org.openapitools.client.models.Transaction

import com.squareup.moshi.Json

import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ApiResponse
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ClientError
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.infrastructure.ServerError
import org.openapitools.client.infrastructure.MultiValueMap
import org.openapitools.client.infrastructure.PartConfig
import org.openapitools.client.infrastructure.RequestConfig
import org.openapitools.client.infrastructure.RequestMethod
import org.openapitools.client.infrastructure.ResponseType
import org.openapitools.client.infrastructure.Success
import org.openapitools.client.infrastructure.toMultiValue

class AccountApi(basePath: kotlin.String = defaultBasePath, client: OkHttpClient = ApiClient.defaultClient, headers: Map<String, String> = mapOf()) : ApiClient(basePath, client, headers) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty(ApiClient.baseUrlKey, "http://localhost:3000")
        }
    }

    /**
     * 
     * 
     * @param createAccountRequest 
     * @return Transaction
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun createAccount(createAccountRequest: CreateAccountRequest) : Transaction {
        val localVarResponse = createAccountWithHttpInfo(createAccountRequest = createAccountRequest)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as Transaction
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
     * @param createAccountRequest 
     * @return ApiResponse<Transaction?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun createAccountWithHttpInfo(createAccountRequest: CreateAccountRequest) : ApiResponse<Transaction?> {
        val localVariableConfig = createAccountRequestConfig(createAccountRequest = createAccountRequest)

        return request<CreateAccountRequest, Transaction>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation createAccount
     *
     * @param createAccountRequest 
     * @return RequestConfig
     */
    fun createAccountRequestConfig(createAccountRequest: CreateAccountRequest) : RequestConfig<CreateAccountRequest> {
        val localVariableBody = createAccountRequest
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Content-Type"] = "application/json"
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/account/create",
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
     * @param accountId 
     * @return void
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getAccountInfo(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String) : Unit {
        val localVarResponse = getAccountInfoWithHttpInfo(environment = environment, index = index, accountId = accountId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> Unit
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
     * @param accountId 
     * @return ApiResponse<Unit?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Throws(IllegalStateException::class, IOException::class)
    fun getAccountInfoWithHttpInfo(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String) : ApiResponse<Unit?> {
        val localVariableConfig = getAccountInfoRequestConfig(environment = environment, index = index, accountId = accountId)

        return request<Unit, Unit>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getAccountInfo
     *
     * @param environment 
     * @param index 
     * @param accountId 
     * @return RequestConfig
     */
    fun getAccountInfoRequestConfig(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        
        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/account/info/{environment}/{index}/{accountId}".replace("{"+"environment"+"}", encodeURIComponent(environment.toString())).replace("{"+"index"+"}", encodeURIComponent(index.toString())).replace("{"+"accountId"+"}", encodeURIComponent(accountId.toString())),
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
     * @param accountId 
     * @return BalanceResponse
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getBalance(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String) : BalanceResponse {
        val localVarResponse = getBalanceWithHttpInfo(environment = environment, index = index, accountId = accountId)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as BalanceResponse
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
     * @param accountId 
     * @return ApiResponse<BalanceResponse?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getBalanceWithHttpInfo(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String) : ApiResponse<BalanceResponse?> {
        val localVariableConfig = getBalanceRequestConfig(environment = environment, index = index, accountId = accountId)

        return request<Unit, BalanceResponse>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getBalance
     *
     * @param environment 
     * @param index 
     * @param accountId 
     * @return RequestConfig
     */
    fun getBalanceRequestConfig(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/account/balance/{environment}/{index}/{accountId}".replace("{"+"environment"+"}", encodeURIComponent(environment.toString())).replace("{"+"index"+"}", encodeURIComponent(index.toString())).replace("{"+"accountId"+"}", encodeURIComponent(accountId.toString())),
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
     * @param accountId 
     * @param mint 
     * @return kotlin.collections.List<HistoryResponse>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getHistory(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String, mint: kotlin.String) : kotlin.collections.List<HistoryResponse> {
        val localVarResponse = getHistoryWithHttpInfo(environment = environment, index = index, accountId = accountId, mint = mint)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.collections.List<HistoryResponse>
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
     * @param accountId 
     * @param mint 
     * @return ApiResponse<kotlin.collections.List<HistoryResponse>?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getHistoryWithHttpInfo(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String, mint: kotlin.String) : ApiResponse<kotlin.collections.List<HistoryResponse>?> {
        val localVariableConfig = getHistoryRequestConfig(environment = environment, index = index, accountId = accountId, mint = mint)

        return request<Unit, kotlin.collections.List<HistoryResponse>>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getHistory
     *
     * @param environment 
     * @param index 
     * @param accountId 
     * @param mint 
     * @return RequestConfig
     */
    fun getHistoryRequestConfig(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String, mint: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/account/history/{environment}/{index}/{accountId}/{mint}".replace("{"+"environment"+"}", encodeURIComponent(environment.toString())).replace("{"+"index"+"}", encodeURIComponent(index.toString())).replace("{"+"accountId"+"}", encodeURIComponent(accountId.toString())).replace("{"+"mint"+"}", encodeURIComponent(mint.toString())),
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
     * @param accountId 
     * @param mint 
     * @return kotlin.collections.List<kotlin.String>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     * @throws UnsupportedOperationException If the API returns an informational or redirection response
     * @throws ClientException If the API returns a client error response
     * @throws ServerException If the API returns a server error response
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class, UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getTokenAccounts(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String, mint: kotlin.String) : kotlin.collections.List<kotlin.String> {
        val localVarResponse = getTokenAccountsWithHttpInfo(environment = environment, index = index, accountId = accountId, mint = mint)

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as kotlin.collections.List<kotlin.String>
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
     * @param accountId 
     * @param mint 
     * @return ApiResponse<kotlin.collections.List<kotlin.String>?>
     * @throws IllegalStateException If the request is not correctly configured
     * @throws IOException Rethrows the OkHttp execute method exception
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalStateException::class, IOException::class)
    fun getTokenAccountsWithHttpInfo(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String, mint: kotlin.String) : ApiResponse<kotlin.collections.List<kotlin.String>?> {
        val localVariableConfig = getTokenAccountsRequestConfig(environment = environment, index = index, accountId = accountId, mint = mint)

        return request<Unit, kotlin.collections.List<kotlin.String>>(
            localVariableConfig
        )
    }

    /**
     * To obtain the request config of the operation getTokenAccounts
     *
     * @param environment 
     * @param index 
     * @param accountId 
     * @param mint 
     * @return RequestConfig
     */
    fun getTokenAccountsRequestConfig(environment: kotlin.String, index: kotlin.Int, accountId: kotlin.String, mint: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()
        localVariableHeaders["Accept"] = "application/json"

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/account/token-accounts/{environment}/{index}/{accountId}/{mint}".replace("{"+"environment"+"}", encodeURIComponent(environment.toString())).replace("{"+"index"+"}", encodeURIComponent(index.toString())).replace("{"+"accountId"+"}", encodeURIComponent(accountId.toString())).replace("{"+"mint"+"}", encodeURIComponent(mint.toString())),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }


    private fun encodeURIComponent(uriComponent: kotlin.String): kotlin.String =
        HttpUrl.Builder().scheme("http").host("localhost").addPathSegment(uriComponent).build().encodedPathSegments[0]
}
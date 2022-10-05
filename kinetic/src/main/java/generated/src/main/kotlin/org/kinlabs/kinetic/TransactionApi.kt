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

package org.kinlabs.kinetic

import org.openapitools.client.models.GetTransactionResponse
import org.openapitools.client.models.LatestBlockhashResponse
import org.openapitools.client.models.MakeTransferRequest
import org.openapitools.client.models.MinimumRentExemptionBalanceResponse
import org.openapitools.client.models.Transaction

import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ClientError
import org.openapitools.client.infrastructure.ServerException
import org.openapitools.client.infrastructure.ServerError
import org.openapitools.client.infrastructure.MultiValueMap
import org.openapitools.client.infrastructure.RequestConfig
import org.openapitools.client.infrastructure.RequestMethod
import org.openapitools.client.infrastructure.ResponseType
import org.openapitools.client.infrastructure.Success
import org.openapitools.client.infrastructure.toMultiValue

class TransactionApi(basePath: kotlin.String = defaultBasePath) : ApiClient(basePath) {
    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            System.getProperties().getProperty("org.openapitools.client.baseUrl", "http://localhost:3000")
        }
    }

    /**
    * 
    * 
    * @param environment  
    * @param index  
    * @return LatestBlockhashResponse
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getLatestBlockhash(environment: kotlin.String, index: kotlin.Int) : LatestBlockhashResponse {
        val localVariableConfig = getLatestBlockhashRequestConfig(environment = environment, index = index)

        val localVarResponse = request<Unit, LatestBlockhashResponse>(
            localVariableConfig
        )

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as LatestBlockhashResponse
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
    * To obtain the request config of the operation getLatestBlockhash
    *
    * @param environment  
    * @param index  
    * @return RequestConfig
    */
    fun getLatestBlockhashRequestConfig(environment: kotlin.String, index: kotlin.Int) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/transaction/latest-blockhash/{environment}/{index}".replace("{"+"environment"+"}", "$environment").replace("{"+"index"+"}", "$index"),
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
    * @param dataLength  
    * @return MinimumRentExemptionBalanceResponse
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getMinimumRentExemptionBalance(environment: kotlin.String, index: kotlin.Int, dataLength: kotlin.Int) : MinimumRentExemptionBalanceResponse {
        val localVariableConfig = getMinimumRentExemptionBalanceRequestConfig(environment = environment, index = index, dataLength = dataLength)

        val localVarResponse = request<Unit, MinimumRentExemptionBalanceResponse>(
            localVariableConfig
        )

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as MinimumRentExemptionBalanceResponse
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
    * To obtain the request config of the operation getMinimumRentExemptionBalance
    *
    * @param environment  
    * @param index  
    * @param dataLength  
    * @return RequestConfig
    */
    fun getMinimumRentExemptionBalanceRequestConfig(environment: kotlin.String, index: kotlin.Int, dataLength: kotlin.Int) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf<kotlin.String, List<kotlin.String>>()
            .apply {
                put("dataLength", listOf(dataLength.toString()))
            }
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/transaction/minimum-rent-exemption-balance/{environment}/{index}".replace("{"+"environment"+"}", "$environment").replace("{"+"index"+"}", "$index"),
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
    * @param signature  
    * @return GetTransactionResponse
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun getTransaction(environment: kotlin.String, index: kotlin.Int, signature: kotlin.String) : GetTransactionResponse {
        val localVariableConfig = getTransactionRequestConfig(environment = environment, index = index, signature = signature)

        val localVarResponse = request<Unit, GetTransactionResponse>(
            localVariableConfig
        )

        return when (localVarResponse.responseType) {
            ResponseType.Success -> (localVarResponse as Success<*>).data as GetTransactionResponse
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
    * To obtain the request config of the operation getTransaction
    *
    * @param environment  
    * @param index  
    * @param signature  
    * @return RequestConfig
    */
    fun getTransactionRequestConfig(environment: kotlin.String, index: kotlin.Int, signature: kotlin.String) : RequestConfig<Unit> {
        val localVariableBody = null
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()

        return RequestConfig(
            method = RequestMethod.GET,
            path = "/api/transaction/transaction/{environment}/{index}/{signature}".replace("{"+"environment"+"}", "$environment").replace("{"+"index"+"}", "$index").replace("{"+"signature"+"}", "$signature"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

    /**
    * 
    * 
    * @param makeTransferRequest  
    * @return Transaction
    * @throws UnsupportedOperationException If the API returns an informational or redirection response
    * @throws ClientException If the API returns a client error response
    * @throws ServerException If the API returns a server error response
    */
    @Suppress("UNCHECKED_CAST")
    @Throws(UnsupportedOperationException::class, ClientException::class, ServerException::class)
    fun makeTransfer(makeTransferRequest: MakeTransferRequest) : Transaction {
        val localVariableConfig = makeTransferRequestConfig(makeTransferRequest = makeTransferRequest)

        val localVarResponse = request<MakeTransferRequest, Transaction>(
            localVariableConfig
        )

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
    * To obtain the request config of the operation makeTransfer
    *
    * @param makeTransferRequest  
    * @return RequestConfig
    */
    fun makeTransferRequestConfig(makeTransferRequest: MakeTransferRequest) : RequestConfig<MakeTransferRequest> {
        val localVariableBody = makeTransferRequest
        val localVariableQuery: MultiValueMap = mutableMapOf()
        val localVariableHeaders: MutableMap<String, String> = mutableMapOf()

        return RequestConfig(
            method = RequestMethod.POST,
            path = "/api/transaction/make-transfer",
            query = localVariableQuery,
            headers = localVariableHeaders,
            body = localVariableBody
        )
    }

}

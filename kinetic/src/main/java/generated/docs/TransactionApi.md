# TransactionApi

All URIs are relative to *https://devnet.kinetic.kin.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getLatestBlockhash**](TransactionApi.md#getLatestBlockhash) | **GET** /api/transaction/latest-blockhash/{environment}/{index} | 
[**getMinimumRentExemptionBalance**](TransactionApi.md#getMinimumRentExemptionBalance) | **GET** /api/transaction/minimum-rent-exemption-balance/{environment}/{index} | 
[**makeTransfer**](TransactionApi.md#makeTransfer) | **POST** /api/transaction/make-transfer | 


<a name="getLatestBlockhash"></a>
# **getLatestBlockhash**
> LatestBlockhashResponse getLatestBlockhash(environment, index)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TransactionApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : LatestBlockhashResponse = apiInstance.getLatestBlockhash(environment, index)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TransactionApi#getLatestBlockhash")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TransactionApi#getLatestBlockhash")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |

### Return type

[**LatestBlockhashResponse**](LatestBlockhashResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getMinimumRentExemptionBalance"></a>
# **getMinimumRentExemptionBalance**
> MinimumRentExemptionBalanceResponse getMinimumRentExemptionBalance(environment, index, dataLength)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TransactionApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
val dataLength : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : MinimumRentExemptionBalanceResponse = apiInstance.getMinimumRentExemptionBalance(environment, index, dataLength)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TransactionApi#getMinimumRentExemptionBalance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TransactionApi#getMinimumRentExemptionBalance")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |
 **dataLength** | **kotlin.Int**|  |

### Return type

[**MinimumRentExemptionBalanceResponse**](MinimumRentExemptionBalanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="makeTransfer"></a>
# **makeTransfer**
> AppTransaction makeTransfer(makeTransferRequest)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TransactionApi()
val makeTransferRequest : MakeTransferRequest =  // MakeTransferRequest | 
try {
    val result : AppTransaction = apiInstance.makeTransfer(makeTransferRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TransactionApi#makeTransfer")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TransactionApi#makeTransfer")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **makeTransferRequest** | [**MakeTransferRequest**](MakeTransferRequest.md)|  |

### Return type

[**AppTransaction**](AppTransaction.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


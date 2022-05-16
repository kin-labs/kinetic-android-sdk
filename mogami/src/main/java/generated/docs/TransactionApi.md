# TransactionApi

All URIs are relative to *https://devnet.mogami.io*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getLatestBlockhash**](TransactionApi.md#getLatestBlockhash) | **GET** /api/transaction/latest-blockhash | 
[**getMinimumRentExemptionBalance**](TransactionApi.md#getMinimumRentExemptionBalance) | **GET** /api/transaction/minimum-rent-exemption-balance | 
[**makeTransfer**](TransactionApi.md#makeTransfer) | **POST** /api/transaction/make-transfer | 


<a name="getLatestBlockhash"></a>
# **getLatestBlockhash**
> LatestBlockhashResponse getLatestBlockhash()



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TransactionApi()
try {
    val result : LatestBlockhashResponse = apiInstance.getLatestBlockhash()
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
This endpoint does not need any parameter.

### Return type

[**LatestBlockhashResponse**](LatestBlockhashResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getMinimumRentExemptionBalance"></a>
# **getMinimumRentExemptionBalance**
> MinimumRentExemptionBalanceResponse getMinimumRentExemptionBalance(dataLength)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TransactionApi()
val dataLength : java.math.BigDecimal = 8.14 // java.math.BigDecimal | 
try {
    val result : MinimumRentExemptionBalanceResponse = apiInstance.getMinimumRentExemptionBalance(dataLength)
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
 **dataLength** | **java.math.BigDecimal**|  |

### Return type

[**MinimumRentExemptionBalanceResponse**](MinimumRentExemptionBalanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="makeTransfer"></a>
# **makeTransfer**
> MakeTransferResponse makeTransfer(makeTransferRequest)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TransactionApi()
val makeTransferRequest : MakeTransferRequest =  // MakeTransferRequest | 
try {
    val result : MakeTransferResponse = apiInstance.makeTransfer(makeTransferRequest)
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

[**MakeTransferResponse**](MakeTransferResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


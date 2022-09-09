# AccountApi

All URIs are relative to *https://devnet.kinetic.kin.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createAccount**](AccountApi.md#createAccount) | **POST** /api/account/create | 
[**getAccountInfo**](AccountApi.md#getAccountInfo) | **GET** /api/account/info/{environment}/{index}/{accountId} | 
[**getBalance**](AccountApi.md#getBalance) | **GET** /api/account/balance/{environment}/{index}/{accountId} | 
[**getHistory**](AccountApi.md#getHistory) | **GET** /api/account/history/{environment}/{index}/{accountId}/{mint} | 
[**getTokenAccounts**](AccountApi.md#getTokenAccounts) | **GET** /api/account/token-accounts/{environment}/{index}/{accountId}/{mint} | 


<a name="createAccount"></a>
# **createAccount**
> AppTransaction createAccount(createAccountRequest)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val createAccountRequest : CreateAccountRequest =  // CreateAccountRequest | 
try {
    val result : AppTransaction = apiInstance.createAccount(createAccountRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#createAccount")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#createAccount")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **createAccountRequest** | [**CreateAccountRequest**](CreateAccountRequest.md)|  |

### Return type

[**AppTransaction**](AppTransaction.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAccountInfo"></a>
# **getAccountInfo**
> getAccountInfo(environment, index, accountId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
val accountId : kotlin.String = accountId_example // kotlin.String | 
try {
    apiInstance.getAccountInfo(environment, index, accountId)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#getAccountInfo")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#getAccountInfo")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |
 **accountId** | **kotlin.String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getBalance"></a>
# **getBalance**
> BalanceResponse getBalance(environment, index, accountId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
val accountId : kotlin.String = accountId_example // kotlin.String | 
try {
    val result : BalanceResponse = apiInstance.getBalance(environment, index, accountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#getBalance")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#getBalance")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |
 **accountId** | **kotlin.String**|  |

### Return type

[**BalanceResponse**](BalanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getHistory"></a>
# **getHistory**
> kotlin.collections.List&lt;HistoryResponse&gt; getHistory(environment, index, accountId, mint)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
val accountId : kotlin.String = accountId_example // kotlin.String | 
val mint : kotlin.String = mint_example // kotlin.String | 
try {
    val result : kotlin.collections.List<HistoryResponse> = apiInstance.getHistory(environment, index, accountId, mint)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#getHistory")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#getHistory")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |
 **accountId** | **kotlin.String**|  |
 **mint** | **kotlin.String**|  |

### Return type

[**kotlin.collections.List&lt;HistoryResponse&gt;**](HistoryResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTokenAccounts"></a>
# **getTokenAccounts**
> kotlin.collections.List&lt;kotlin.String&gt; getTokenAccounts(environment, index, accountId, mint)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
val accountId : kotlin.String = accountId_example // kotlin.String | 
val mint : kotlin.String = mint_example // kotlin.String | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.getTokenAccounts(environment, index, accountId, mint)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#getTokenAccounts")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#getTokenAccounts")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |
 **accountId** | **kotlin.String**|  |
 **mint** | **kotlin.String**|  |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


# AccountApi

All URIs are relative to *https://devnet.mogami.io*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiAccountFeatureControllerGetAccountInfo**](AccountApi.md#apiAccountFeatureControllerGetAccountInfo) | **GET** /api/account/info/{accountId} | 
[**createAccount**](AccountApi.md#createAccount) | **POST** /api/account/create | 
[**getBalance**](AccountApi.md#getBalance) | **GET** /api/account/balance/{accountId} | 
[**getHistory**](AccountApi.md#getHistory) | **GET** /api/account/history/{accountId} | 
[**tokenAccounts**](AccountApi.md#tokenAccounts) | **GET** /api/account/token-accounts/{accountId} | 


<a name="apiAccountFeatureControllerGetAccountInfo"></a>
# **apiAccountFeatureControllerGetAccountInfo**
> apiAccountFeatureControllerGetAccountInfo(accountId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val accountId : kotlin.String = accountId_example // kotlin.String | 
try {
    apiInstance.apiAccountFeatureControllerGetAccountInfo(accountId)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#apiAccountFeatureControllerGetAccountInfo")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#apiAccountFeatureControllerGetAccountInfo")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | **kotlin.String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

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

<a name="getBalance"></a>
# **getBalance**
> BalanceResponse getBalance(accountId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val accountId : kotlin.String = accountId_example // kotlin.String | 
try {
    val result : BalanceResponse = apiInstance.getBalance(accountId)
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
> kotlin.collections.List&lt;HistoryResponse&gt; getHistory(accountId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val accountId : kotlin.String = accountId_example // kotlin.String | 
try {
    val result : kotlin.collections.List<HistoryResponse> = apiInstance.getHistory(accountId)
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
 **accountId** | **kotlin.String**|  |

### Return type

[**kotlin.collections.List&lt;HistoryResponse&gt;**](HistoryResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="tokenAccounts"></a>
# **tokenAccounts**
> kotlin.collections.List&lt;kotlin.String&gt; tokenAccounts(accountId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AccountApi()
val accountId : kotlin.String = accountId_example // kotlin.String | 
try {
    val result : kotlin.collections.List<kotlin.String> = apiInstance.tokenAccounts(accountId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AccountApi#tokenAccounts")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AccountApi#tokenAccounts")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **accountId** | **kotlin.String**|  |

### Return type

**kotlin.collections.List&lt;kotlin.String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


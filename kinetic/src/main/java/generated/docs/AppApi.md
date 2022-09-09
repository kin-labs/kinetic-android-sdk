# AppApi

All URIs are relative to *https://devnet.kinetic.kin.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAppConfig**](AppApi.md#getAppConfig) | **GET** /api/app/{environment}/{index}/config | 
[**getAppHealth**](AppApi.md#getAppHealth) | **GET** /api/app/{environment}/{index}/health | 


<a name="getAppConfig"></a>
# **getAppConfig**
> AppConfig getAppConfig(environment, index)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AppApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : AppConfig = apiInstance.getAppConfig(environment, index)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AppApi#getAppConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AppApi#getAppConfig")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |

### Return type

[**AppConfig**](AppConfig.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getAppHealth"></a>
# **getAppHealth**
> AppHealth getAppHealth(environment, index)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AppApi()
val environment : kotlin.String = environment_example // kotlin.String | 
val index : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : AppHealth = apiInstance.getAppHealth(environment, index)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AppApi#getAppHealth")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AppApi#getAppHealth")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **environment** | **kotlin.String**|  |
 **index** | **kotlin.Int**|  |

### Return type

[**AppHealth**](AppHealth.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


# AppApi

All URIs are relative to *https://devnet.mogami.io*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiAppFeatureControllerAppWebhook**](AppApi.md#apiAppFeatureControllerAppWebhook) | **POST** /api/app/{index}/webhook/{type} | 
[**getAppConfig**](AppApi.md#getAppConfig) | **GET** /api/app/config/{index} | 


<a name="apiAppFeatureControllerAppWebhook"></a>
# **apiAppFeatureControllerAppWebhook**
> apiAppFeatureControllerAppWebhook(index, type)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AppApi()
val index : java.math.BigDecimal = 8.14 // java.math.BigDecimal | 
val type : kotlin.String = type_example // kotlin.String | 
try {
    apiInstance.apiAppFeatureControllerAppWebhook(index, type)
} catch (e: ClientException) {
    println("4xx response calling AppApi#apiAppFeatureControllerAppWebhook")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AppApi#apiAppFeatureControllerAppWebhook")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **index** | **java.math.BigDecimal**|  |
 **type** | **kotlin.String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getAppConfig"></a>
# **getAppConfig**
> AppConfig getAppConfig(index)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AppApi()
val index : kotlin.String = index_example // kotlin.String | 
try {
    val result : AppConfig = apiInstance.getAppConfig(index)
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
 **index** | **kotlin.String**|  |

### Return type

[**AppConfig**](AppConfig.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


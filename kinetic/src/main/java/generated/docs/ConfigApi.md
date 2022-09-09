# ConfigApi

All URIs are relative to *https://devnet.mogami.io*

Method | HTTP request | Description
------------- | ------------- | -------------
[**config**](ConfigApi.md#config) | **GET** /api/config | 


<a name="config"></a>
# **config**
> ApiConfigSummary config()



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = ConfigApi()
try {
    val result : ApiConfigSummary = apiInstance.config()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling ConfigApi#config")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling ConfigApi#config")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ApiConfigSummary**](ApiConfigSummary.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


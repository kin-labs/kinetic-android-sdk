# AirdropApi

All URIs are relative to *http://localhost:3000*

Method | HTTP request | Description
------------- | ------------- | -------------
[**requestAirdrop**](AirdropApi.md#requestAirdrop) | **POST** /api/airdrop | 


<a name="requestAirdrop"></a>
# **requestAirdrop**
> RequestAirdropResponse requestAirdrop(requestAirdropRequest)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AirdropApi()
val requestAirdropRequest : RequestAirdropRequest =  // RequestAirdropRequest | 
try {
    val result : RequestAirdropResponse = apiInstance.requestAirdrop(requestAirdropRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AirdropApi#requestAirdrop")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AirdropApi#requestAirdrop")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestAirdropRequest** | [**RequestAirdropRequest**](RequestAirdropRequest.md)|  |

### Return type

[**RequestAirdropResponse**](RequestAirdropResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


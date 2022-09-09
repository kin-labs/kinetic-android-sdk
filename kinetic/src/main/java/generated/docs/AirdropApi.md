# AirdropApi

All URIs are relative to *https://devnet.kinetic.kin.org*

Method | HTTP request | Description
------------- | ------------- | -------------
[**airdropStats**](AirdropApi.md#airdropStats) | **GET** /api/airdrop/stats | 
[**requestAirdrop**](AirdropApi.md#requestAirdrop) | **POST** /api/airdrop | 


<a name="airdropStats"></a>
# **airdropStats**
> AirdropStats airdropStats()



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AirdropApi()
try {
    val result : AirdropStats = apiInstance.airdropStats()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AirdropApi#airdropStats")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AirdropApi#airdropStats")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**AirdropStats**](AirdropStats.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

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


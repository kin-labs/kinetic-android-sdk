# org.openapitools.client - Kotlin client library for Mogami

## Requires

* Kotlin 1.4.30
* Gradle 6.8.3

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *https://devnet.mogami.io*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountApi* | [**apiAccountFeatureControllerGetAccountInfo**](docs/AccountApi.md#apiaccountfeaturecontrollergetaccountinfo) | **GET** /api/account/info/{accountId} | 
*AccountApi* | [**createAccount**](docs/AccountApi.md#createaccount) | **POST** /api/account/create | 
*AccountApi* | [**getBalance**](docs/AccountApi.md#getbalance) | **GET** /api/account/balance/{accountId} | 
*AccountApi* | [**getHistory**](docs/AccountApi.md#gethistory) | **GET** /api/account/history/{accountId} | 
*AccountApi* | [**tokenAccounts**](docs/AccountApi.md#tokenaccounts) | **GET** /api/account/token-accounts/{accountId} | 
*AirdropApi* | [**airdropStats**](docs/AirdropApi.md#airdropstats) | **GET** /api/airdrop/stats | 
*AirdropApi* | [**requestAirdrop**](docs/AirdropApi.md#requestairdrop) | **POST** /api/airdrop | 
*AppApi* | [**apiAppFeatureControllerAppWebhook**](docs/AppApi.md#apiappfeaturecontrollerappwebhook) | **POST** /api/app/{index}/webhook/{type} | 
*AppApi* | [**getAppConfig**](docs/AppApi.md#getappconfig) | **GET** /api/app/config/{index} | 
*ConfigApi* | [**config**](docs/ConfigApi.md#config) | **GET** /api/config | 
*DefaultApi* | [**apiCoreFeatureControllerUptime**](docs/DefaultApi.md#apicorefeaturecontrolleruptime) | **GET** /api/uptime | 
*DefaultApi* | [**healthCheck**](docs/DefaultApi.md#healthcheck) | **GET** /api/health-check | 
*TransactionApi* | [**getLatestBlockhash**](docs/TransactionApi.md#getlatestblockhash) | **GET** /api/transaction/latest-blockhash | 
*TransactionApi* | [**getMinimumRentExemptionBalance**](docs/TransactionApi.md#getminimumrentexemptionbalance) | **GET** /api/transaction/minimum-rent-exemption-balance | 
*TransactionApi* | [**makeTransfer**](docs/TransactionApi.md#maketransfer) | **POST** /api/transaction/make-transfer | 


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.AirdropStats](docs/AirdropStats.md)
 - [org.openapitools.client.models.AirdropStatsCounts](docs/AirdropStatsCounts.md)
 - [org.openapitools.client.models.ApiConfigSummary](docs/ApiConfigSummary.md)
 - [org.openapitools.client.models.AppConfig](docs/AppConfig.md)
 - [org.openapitools.client.models.AppConfigApp](docs/AppConfigApp.md)
 - [org.openapitools.client.models.AppConfigMint](docs/AppConfigMint.md)
 - [org.openapitools.client.models.AppTransaction](docs/AppTransaction.md)
 - [org.openapitools.client.models.BalanceResponse](docs/BalanceResponse.md)
 - [org.openapitools.client.models.CreateAccountRequest](docs/CreateAccountRequest.md)
 - [org.openapitools.client.models.HealthCheckResponse](docs/HealthCheckResponse.md)
 - [org.openapitools.client.models.HistoryResponse](docs/HistoryResponse.md)
 - [org.openapitools.client.models.LatestBlockhashResponse](docs/LatestBlockhashResponse.md)
 - [org.openapitools.client.models.MakeTransferRequest](docs/MakeTransferRequest.md)
 - [org.openapitools.client.models.MakeTransferResponse](docs/MakeTransferResponse.md)
 - [org.openapitools.client.models.MinimumRentExemptionBalanceResponse](docs/MinimumRentExemptionBalanceResponse.md)
 - [org.openapitools.client.models.RequestAirdropRequest](docs/RequestAirdropRequest.md)
 - [org.openapitools.client.models.RequestAirdropResponse](docs/RequestAirdropResponse.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.

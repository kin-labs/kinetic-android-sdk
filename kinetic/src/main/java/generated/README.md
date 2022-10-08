# org.openapitools.client - Kotlin client library for @kin-kinetic/api

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

All URIs are relative to *http://localhost:3000*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountApi* | [**createAccount**](docs/AccountApi.md#createaccount) | **POST** /api/account/create | 
*AccountApi* | [**getAccountInfo**](docs/AccountApi.md#getaccountinfo) | **GET** /api/account/info/{environment}/{index}/{accountId} | 
*AccountApi* | [**getBalance**](docs/AccountApi.md#getbalance) | **GET** /api/account/balance/{environment}/{index}/{accountId} | 
*AccountApi* | [**getHistory**](docs/AccountApi.md#gethistory) | **GET** /api/account/history/{environment}/{index}/{accountId}/{mint} | 
*AccountApi* | [**getTokenAccounts**](docs/AccountApi.md#gettokenaccounts) | **GET** /api/account/token-accounts/{environment}/{index}/{accountId}/{mint} | 
*AirdropApi* | [**requestAirdrop**](docs/AirdropApi.md#requestairdrop) | **POST** /api/airdrop | 
*AppApi* | [**getAppConfig**](docs/AppApi.md#getappconfig) | **GET** /api/app/{environment}/{index}/config | 
*AppApi* | [**getAppHealth**](docs/AppApi.md#getapphealth) | **GET** /api/app/{environment}/{index}/health | 
*TransactionApi* | [**getLatestBlockhash**](docs/TransactionApi.md#getlatestblockhash) | **GET** /api/transaction/latest-blockhash/{environment}/{index} | 
*TransactionApi* | [**getMinimumRentExemptionBalance**](docs/TransactionApi.md#getminimumrentexemptionbalance) | **GET** /api/transaction/minimum-rent-exemption-balance/{environment}/{index} | 
*TransactionApi* | [**getTransaction**](docs/TransactionApi.md#gettransaction) | **GET** /api/transaction/transaction/{environment}/{index}/{signature} | 
*TransactionApi* | [**makeTransfer**](docs/TransactionApi.md#maketransfer) | **POST** /api/transaction/make-transfer | 


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.AppConfig](docs/AppConfig.md)
 - [org.openapitools.client.models.AppConfigApi](docs/AppConfigApi.md)
 - [org.openapitools.client.models.AppConfigApp](docs/AppConfigApp.md)
 - [org.openapitools.client.models.AppConfigCluster](docs/AppConfigCluster.md)
 - [org.openapitools.client.models.AppConfigEnvironment](docs/AppConfigEnvironment.md)
 - [org.openapitools.client.models.AppConfigMint](docs/AppConfigMint.md)
 - [org.openapitools.client.models.AppHealth](docs/AppHealth.md)
 - [org.openapitools.client.models.BalanceResponse](docs/BalanceResponse.md)
 - [org.openapitools.client.models.BalanceToken](docs/BalanceToken.md)
 - [org.openapitools.client.models.ClusterType](docs/ClusterType.md)
 - [org.openapitools.client.models.Commitment](docs/Commitment.md)
 - [org.openapitools.client.models.ConfirmedSignatureInfo](docs/ConfirmedSignatureInfo.md)
 - [org.openapitools.client.models.ConfirmedTransactionMeta](docs/ConfirmedTransactionMeta.md)
 - [org.openapitools.client.models.CreateAccountRequest](docs/CreateAccountRequest.md)
 - [org.openapitools.client.models.GetTransactionResponse](docs/GetTransactionResponse.md)
 - [org.openapitools.client.models.HistoryResponse](docs/HistoryResponse.md)
 - [org.openapitools.client.models.LatestBlockhashResponse](docs/LatestBlockhashResponse.md)
 - [org.openapitools.client.models.MakeTransferRequest](docs/MakeTransferRequest.md)
 - [org.openapitools.client.models.MinimumRentExemptionBalanceResponse](docs/MinimumRentExemptionBalanceResponse.md)
 - [org.openapitools.client.models.RequestAirdropRequest](docs/RequestAirdropRequest.md)
 - [org.openapitools.client.models.RequestAirdropResponse](docs/RequestAirdropResponse.md)
 - [org.openapitools.client.models.SignatureStatus](docs/SignatureStatus.md)
 - [org.openapitools.client.models.Transaction](docs/Transaction.md)
 - [org.openapitools.client.models.TransactionData](docs/TransactionData.md)
 - [org.openapitools.client.models.TransactionError](docs/TransactionError.md)
 - [org.openapitools.client.models.TransactionErrorType](docs/TransactionErrorType.md)
 - [org.openapitools.client.models.TransactionResponse](docs/TransactionResponse.md)
 - [org.openapitools.client.models.TransactionStatus](docs/TransactionStatus.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.

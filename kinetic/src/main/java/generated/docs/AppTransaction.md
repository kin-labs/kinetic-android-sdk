
# AppTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** |  | 
**createdAt** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**updatedAt** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**amount** | **kotlin.String** |  | 
**destination** | **kotlin.String** |  | 
**errors** | [**kotlin.collections.List&lt;AppTransactionError&gt;**](AppTransactionError.md) |  | 
**explorerUrl** | **kotlin.String** |  | 
**feePayer** | **kotlin.String** |  | 
**mint** | **kotlin.String** |  | 
**processingDuration** | [**java.math.BigDecimal**](java.math.BigDecimal.md) |  | 
**referenceId** | **kotlin.String** |  | 
**referenceType** | **kotlin.String** |  | 
**signature** | **kotlin.String** |  | 
**solanaCommitted** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**solanaCommittedDuration** | [**java.math.BigDecimal**](java.math.BigDecimal.md) |  | 
**solanaFinalized** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**solanaFinalizedDuration** | [**java.math.BigDecimal**](java.math.BigDecimal.md) |  | 
**solanaStart** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**solanaTransaction** | [**kotlin.Any**](.md) |  | 
**source** | **kotlin.String** |  | 
**status** | [**inline**](#Status) |  | 
**totalDuration** | [**java.math.BigDecimal**](java.math.BigDecimal.md) |  | 
**webhookEventStart** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**webhookEventEnd** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**webhookEventDuration** | [**java.math.BigDecimal**](java.math.BigDecimal.md) |  | 
**webhookVerifyStart** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**webhookVerifyEnd** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  | 
**webhookVerifyDuration** | [**java.math.BigDecimal**](java.math.BigDecimal.md) |  | 


<a name="Status"></a>
## Enum: status
Name | Value
---- | -----
status | Committed, Confirmed, Failed, Finalized, Processing




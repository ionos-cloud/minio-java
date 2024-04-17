# Java Client API Reference

## Create Client.

```java
ApiClient apiClient =
    ApiClient.builder()
        .endpoint("ENDPOINT")
        .credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
        .build();
```

| Bucket operations                                                 | Object operations                                       |
|-------------------------------------------------------------------|---------------------------------------------------------|
| [`bucketExists`](#bucketExists)                                   | [`composeObject`](#composeObject)                       |
| [`deleteBucketEncryption`](#deleteBucketEncryption)               | [`copyObject`](#copyObject)                             |
| [`deleteBucketLifecycle`](#deleteBucketLifecycle)                 | [`deleteObjectTags`](#deleteObjectTags)                 |
| [`deleteBucketNotification`](#deleteBucketNotification)           | [`disableObjectLegalHold`](#disableObjectLegalHold)     |
| [`deleteBucketPolicy`](#deleteBucketPolicy)                       | [`downloadObject`](#downloadObject)                     |
| [`deleteBucketReplication`](#deleteBucketReplication)             | [`enableObjectLegalHold`](#enableObjectLegalHold)       |
| [`deleteBucketTags`](#deleteBucketTags)                           | [`getObject`](#getObject)                               |
| [`deleteObjectLockConfiguration`](#deleteObjectLockConfiguration) | [`getObjectRetention`](#getObjectRetention)             |
| [`getBucketEncryption`](#getBucketEncryption)                     | [`getObjectTags`](#getObjectTags)                       |
| [`getBucketLifecycle`](#getBucketLifecycle)                       | [`getPresignedObjectUrl`](#getPresignedObjectUrl)       |
| [`getBucketNotification`](#getBucketNotification)                 | [`getPresignedPostFormData`](#getPresignedPostFormData) |
| [`getBucketPolicy`](#getBucketPolicy)                             | [`isObjectLegalHoldEnabled`](#isObjectLegalHoldEnabled) |
| [`getBucketReplication`](#getBucketReplication)                   | [`listObjects`](#listObjects)                           |
| [`getBucketTagging`](#getBucketTagging)                                 | [`putObject`](#putObject)                               |
| [`getBucketVersioning`](#getBucketVersioning)                     | [`removeObject`](#removeObject)                         |
| [`getObjectLockConfiguration`](#getObjectLockConfiguration)       | [`removeObjects`](#removeObjects)                       |
| [`listBuckets`](#listBuckets)                                     | [`restoreObject`](#restoreObject)                       |
| [`listenBucketNotification`](#listenBucketNotification)           | [`selectObjectContent`](#selectObjectContent)           |
| [`makeBucket`](#makeBucket)                                       | [`setObjectRetention`](#setObjectRetention)             |
| [`deleteBucket`](#deleteBucket)                                   | [`setObjectTags`](#setObjectTags)                       |
| [`putBucketEncryption`](#putBucketEncryption)                     | [`statObject`](#statObject)                             |
| [`setBucketLifecycle`](#setBucketLifecycle)                       | [`uploadObject`](#uploadObject)                         |
| [`setBucketNotification`](#setBucketNotification)                 | [`uploadSnowballObjects`](#uploadSnowballObjects)       |
| [`putBucketPolicy`](#putBucketPolicy)                             |                                                         |
| [`setBucketReplication`](#setBucketReplication)                   |                                                         |
| [`putBucketTagging`](#putBucketTagging)                                 |                                                         |
| [`setBucketVersioning`](#setBucketVersioning)                     |                                                         |
| [`setObjectLockConfiguration`](#setObjectLockConfiguration)       |                                                         |

## 1. Client Builder

Client Builder is used to create a client. Builder has below methods to accept arguments.
| Method          | Description                                                                                                                                |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| `endpoint()`    | Accepts endpoint as a String, URL or okhttp3.HttpUrl object and optionally accepts port number and flag to enable secure (TLS) connection. |
|                 | Endpoint as a string can be formatted like below:                                                                                          |
|                 | `<url>`                                                                                                                 |
| `credentials()` | Accepts access key (aka user ID) and secret key (aka password) of an account in S3 service.                                                |
| `region()`      | Accepts region name of S3 service. If specified, all operations use this region otherwise region is probed per bucket.                     |
| `httpClient()`  | Custom HTTP client to override default.                                                                                                    |

__Examples__


```java
// 1. Create client to S3 service 'ENDPOINT' at port 443 with TLS security
// for anonymous access.
ApiClient s3Client = ApiClient.builder().endpoint("https://ENDPOINT").build();

// 2. Create client to S3 service 'ENDPOINT' at port 443 with TLS security
// using URL object for anonymous access.
ApiClient s3Client = ApiClient.builder().endpoint(new URL("https://ENDPOINT")).build();

// 3. Create client to S3 service 'ENDPOINT' at port 9000 with TLS security
// using okhttp3.HttpUrl object for anonymous access.
ApiClient s3Client =
    ApiClient.builder().endpoint(HttpUrl.parse("https://ENDPOINT")).build();

// 4. Create client to S3 service 'ENDPOINT' at port 80 with TLS security
// for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
	    .endpoint("ENDPOINT")
		.credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
		.build();

// 5. Create client to S3 service 'ENDPOINT' at port 443 with non-TLS security
// for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
        .endpoint("ENDPOINT", 433, false)
		.credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
		.build();

// 6. Create client to S3 service 'ENDPOINT' at port 80 with non-TLS security
// for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
	    .endpoint("ENDPOINT", 80, false)
        .credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
		.build();

// 7. Create client to S3 service 'ENDPOINT' at port 80 with TLS security
// for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
	    .endpoint("ENDPOINT", 80, true)
        .credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
		.build();

// 8. Create client to S3 service 'ENDPOINT' at port 80 with non-TLS security
// and region 'us-west-1' for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
	    .endpoint("ENDPOINT", 80, false)
        .credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
		.region("us-west-1")
		.build();

// 9. Create client to S3 service 'ENDPOINT' at port 443 with TLS security
// and region 'eu-west-2' for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
	    .endpoint("ENDPOINT", 443, true)
		.credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY").
		.region("eu-west-2")
		.build();

// 10. Create client to S3 service 'ENDPOINT' at port 443 with TLS security,
// region 'eu-central-1' and custom HTTP client for authenticated access.
ApiClient s3Client =
    ApiClient.builder()
	    .endpoint("ENDPOINT", 443, true)
        .credentials("YOUR-ACCESSKEYID", "YOUR-SECRETACCESSKEY")
		.region("eu-central-1")
		.httpClient(customHttpClient)
		.build();
```

## Common Exceptions
All APIs throw below exceptions in addition to specific to API.

| Exception                 | Cause                                                                |
|:--------------------------|:---------------------------------------------------------------------|
| ErrorResponseException    | Thrown to indicate S3 service returned an error response.            |
| IllegalArgumentException  | Throws to indicate invalid argument passed.                          |
| InsufficientDataException | Thrown to indicate not enough data available in InputStream.         |
| InternalException         | Thrown to indicate internal library error.                           |
| InvalidKeyException       | Thrown to indicate missing of HMAC SHA-256 library.                  |
| InvalidResponseException  | Thrown to indicate S3 service returned invalid or no error response. |
| IOException               | Thrown to indicate I/O error on S3 operation.                        |
| NoSuchAlgorithmException  | Thrown to indicate missing of MD5 or SHA-256 digest library.         |
| ServerException           | Thrown to indicate HTTP server error.                                |
| XmlParserException        | Thrown to indicate XML parsing error.                                |

## 2. Bucket operations

<a name="bucketExists"></a>
### bucketExists(HeadBucketArgs args)
`public boolean bucketExists(HeadBucketArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#bucketExists-com.ionoscloud.s3.HeadBucketArgs-)_

Checks if a bucket exists.

__Parameters__
| Parameter      | Type                 | Description    |
|:---------------|:---------------------|:---------------|
| ``bucketName`` | _[HeadBucketArgs]_ | Arguments.     |

| Returns                                |
|:---------------------------------------|
| _boolean_ - True if the bucket exists. |

__Example__
```java
// Check whether 'my-bucketname' exists or not.
boolean found =
  apiClient.bucketExists(HeadBucketArgs.builder().bucket("my-bucketname").build());
if (found) {
  System.out.println("my-bucketname exists");
} else {
  System.out.println("my-bucketname does not exist");
}
```

<a name="deleteBucketEncryption"></a>
### deleteBucketEncryption(DeleteBucketEncryptionArgs args)
`private void deleteBucketEncryption(DeleteBucketEncryptionArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucketEncryption-com.ionoscloud.s3.DeleteBucketEncryptionArgs-)_

Deletes encryption configuration of a bucket.

__Parameters__
| Parameter | Type                           | Description |
|:----------|:-------------------------------|:------------|
| ``args``  | _[DeleteBucketEncryptionArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteBucketEncryption(
    DeleteBucketEncryptionArgs.builder().bucket("my-bucketname").build());
```

<a name="deleteBucketLifecycle"></a>
### deleteBucketLifecycle(DeleteBucketLifecycleArgs args)
`private void deleteBucketLifecycle(DeleteBucketLifecycleArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucketLifecycle-com.ionoscloud.s3.DeleteBucketLifecycleArgs-)_

Deletes lifecycle configuration of a bucket.

__Parameters__
| Parameter | Type                          | Description |
|:----------|:------------------------------|:------------|
| ``args``  | _[DeleteBucketLifecycleArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteBucketLifecycle(
    DeleteBucketLifecycleArgs.builder().bucket("my-bucketname").build());
```

<a name="deleteBucketTags"></a>
### deleteBucketTags(DeleteBucketTaggingArgs args)
`private void deleteBucketTags(DeleteBucketTaggingArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucketTags-com.ionoscloud.s3.DeleteBucketTaggingArgs-)_

Deletes tags of a bucket.

__Parameters__
| Parameter | Type                     | Description |
|:----------|:-------------------------|:------------|
| ``args``  | _[DeleteBucketTaggingArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteBucketTags(DeleteBucketTaggingArgs.builder().bucket("my-bucketname").build());
```

<a name="deleteBucketPolicy"></a>
### deleteBucketPolicy(DeleteBucketPolicyArgs args)
`private void deleteBucketPolicy(DeleteBucketPolicyArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucketPolicy-com.ionoscloud.s3.DeleteBucketPolicyArgs-)_

Deletes bucket policy configuration of a bucket.

__Parameters__
| Parameter | Type                       | Description |
|:----------|:---------------------------|:------------|
| ``args``  | _[DeleteBucketPolicyArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucket("my-bucketname").build());
```

<a name="deleteBucketReplication"></a>
### deleteBucketReplication(DeleteBucketReplicationArgs args)
`private void deleteBucketReplication(DeleteBucketReplicationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucketReplication-com.ionoscloud.s3.DeleteBucketReplicationArgs-)_

Deletes bucket replication configuration of a bucket.

__Parameters__
| Parameter | Type                            | Description |
|:----------|:--------------------------------|:------------|
| ``args``  | _[DeleteBucketReplicationArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteBucketReplication(
    DeleteBucketReplicationArgs.builder().bucket("my-bucketname").build());
```

<a name="deleteBucketNotification"></a>
### deleteBucketNotification(DeleteBucketNotificationArgs args)
`public void deleteBucketNotification(DeleteBucketNotificationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucketNotification-com.ionoscloud.s3.DeleteBucketNotificationArgs-)_

Deletes notification configuration of a bucket.

__Parameters__
| Parameter | Type                             | Description |
|:----------|:---------------------------------|:------------|
| ``args``  | _[DeleteBucketNotificationArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteBucketNotification(
    DeleteBucketNotificationArgs.builder().bucket("my-bucketname").build());
```

<a name="deleteObjectLockConfiguration"></a>
### deleteObjectLockConfiguration(DeleteObjectLockConfigurationArgs args)
`public void deleteObjectLockConfiguration(DeleteObjectLockConfigurationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteObjectLockConfiguration-com.ionoscloud.s3.DeleteObjectLockConfigurationArgs-)_

Deletes object-lock configuration in a bucket.

__Parameters__
| Parameter | Type                                  | Description |
|:----------|:--------------------------------------|:------------|
| ``args``  | _[DeleteObjectLockConfigurationArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteObjectLockConfiguration(
    DeleteObjectLockConfigurationArgs.builder().bucket("my-bucketname").build());
```

<a name="getBucketEncryption"></a>
### getBucketEncryption(GetBucketEncryptionArgs args)
`public SseConfiguration getBucketEncryption(GetBucketEncryptionArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getBucketEncryption-com.ionoscloud.s3.GetBucketEncryptionArgs-)_

Gets encryption configuration of a bucket.

__Parameters__
| Parameter | Type                        | Description |
|:----------|:----------------------------|:------------|
| ``args``  | _[GetBucketEncryptionArgs]_ | Arguments.  |

| Returns                                                      |
|:-------------------------------------------------------------|
| _[SseConfiguration]_ - Server-side encryption configuration. |

__Example__
```java
SseConfiguration config =
    apiClient.getBucketEncryption(
        GetBucketEncryptionArgs.builder().bucket("my-bucketname").build());
```

<a name="getBucketLifecycle"></a>
### getBucketLifecycle(GetBucketLifecycleArgs args)
`public LifecycleConfiguration getBucketLifecycle(GetBucketLifecycleArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getBucketLifecycle-com.ionoscloud.s3.GetBucketLifecycleArgs-)_

Gets lifecycle configuration of a bucket.

__Parameters__
| Parameter | Type                       | Description |
|:----------|:---------------------------|:------------|
| ``args``  | _[GetBucketLifecycleArgs]_ | Arguments.  |

| Returns                                               |
|:------------------------------------------------------|
| _[LifecycleConfiguration]_ - lifecycle configuration. |

__Example__
```java
LifecycleConfiguration config =
    apiClient.getBucketLifecycle(
	    GetBucketLifecycleArgs.builder().bucket("my-bucketname").build());
System.out.println("Lifecycle configuration: " + config);
```

<a name="getBucketNotification"></a>
### getBucketNotification(GetBucketNotificationArgs args)
`public NotificationConfiguration getBucketNotification(GetBucketNotificationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getBucketNotification-com.ionoscloud.s3.GetBucketNotificationArgs-)_

Gets notification configuration of a bucket.

__Parameters__
| Parameter | Type                          | Description |
|:----------|:------------------------------|:------------|
| ``args``  | _[GetBucketNotificationArgs]_ | Arguments.  |

| Returns                                                     |
|:------------------------------------------------------------|
| _[NotificationConfiguration]_ - Notification configuration. |

__Example__
```java
NotificationConfiguration config =
    apiClient.getBucketNotification(
	    GetBucketNotificationArgs.builder().bucket("my-bucketname").build());
```

<a name="getBucketPolicy"></a>
### getBucketPolicy(GetBucketPolicyArgs args)
`public String getBucketPolicy(GetBucketPolicyArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getBucketPolicy-com.ionoscloud.s3.GetBucketPolicyArgs-)_

Gets bucket policy configuration of a bucket.

__Parameters__
| Parameter | Type                    | Description |
|:----------|:------------------------|:------------|
| ``args``  | _[GetBucketPolicyArgs]_ | Arguments.  |


| Returns                                                |
|:-------------------------------------------------------|
| _String_ - Bucket policy configuration as JSON string. |

__Example__
```java
String config =
    apiClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket("my-bucketname").build());
```

<a name="getBucketReplication"></a>
### getBucketReplication(GetBucketReplicationArgs args)
`public ReplicationConfiguration getBucketReplication(GetBucketReplicationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getBucketReplication-com.ionoscloud.s3.GetBucketReplicationArgs-)_

Gets bucket replication configuration of a bucket.

__Parameters__
| Parameter | Type                         | Description |
|:----------|:-----------------------------|:------------|
| ``args``  | _[GetBucketReplicationArgs]_ | Arguments.  |


| Returns                                                          |
|:-----------------------------------------------------------------|
| _[ReplicationConfiguration]_ - Bucket replication configuration. |

__Example__
```java
ReplicationConfiguration config =
    apiClient.getBucketReplication(
	    GetBucketReplicationArgs.builder().bucket("my-bucketname").build());
```

<a name="getBucketTagging"></a>
### getBucketTagging(GetBucketTaggingArgs args)
`public Tags getBucketTagging(GetBucketTaggingArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.htmlgetBucketTagging-com.ionoscloud.s3.GetBucketTaggingArgs-)_

Gets tags of a bucket.

__Parameters__
| Parameter | Type                  | Description |
|:----------|:----------------------|:------------|
| ``args``  | _[GetBucketTaggingArgs]_ | Arguments.  |


| Returns          |
|:-----------------|
| _[Tags]_ - tags. |

__Example__
```java
Tags tags = apiClient.getBucketTagging(GetBucketTaggingArgs.builder().bucket("my-bucketname").build());
```

<a name="getBucketVersioning"></a>
### getBucketVersioning(GetBucketVersioningArgs args)
`public VersioningConfiguration getBucketVersioning(GetBucketVersioningArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getBucketVersioning-com.ionoscloud.s3.GetBucketVersioningArgs-)_

Gets versioning configuration of a bucket.

__Parameters__
| Parameter | Type                        | Description |
|:----------|:----------------------------|:------------|
| ``args``  | _[GetBucketVersioningArgs]_ | Arguments.  |

| Returns                                                 |
|:--------------------------------------------------------|
| _[VersioningConfiguration]_ - Versioning configuration. |

__Example__
```java
VersioningConfiguration config =
    apiClient.getBucketVersioning(
        GetBucketVersioningArgs.builder().bucket("my-bucketname").build());
```

<a name="getObjectLockConfiguration"></a>
### getObjectLockConfiguration(GetObjectLockConfigurationArgs args)
`public ObjectLockConfiguration getObjectLockConfiguration(GetObjectLockConfigurationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getObjectLockConfiguration-com.ionoscloud.s3.GetObjectLockConfigurationArgs-)_

Gets object-lock configuration in a bucket.

__Parameters__

| Parameter | Type                               | Description |
|:----------|:-----------------------------------|:------------|
| ``args``  | _[GetObjectLockConfigurationArgs]_ | Arguments.  |

| Returns                                                        |
|:---------------------------------------------------------------|
| _[ObjectLockConfiguration]_ - Default retention configuration. |

__Example__
```java
ObjectLockConfiguration config =
    apiClient.getObjectLockConfiguration(
	    GetObjectLockConfigurationArgs.builder().bucket("my-bucketname").build());
System.out.println("Mode: " + config.mode());
System.out.println("Duration: " + config.duration().duration() + " " + config.duration().unit());
```

<a name="listBuckets"></a>
### listBuckets()
`public List<Bucket> listBuckets()` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#listBuckets--)_

Lists bucket information of all buckets.

| Returns                                        |
|:-----------------------------------------------|
| _List<[Bucket]>_ - List of bucket information. |

__Example__
```java
List<Bucket> bucketList = apiClient.listBuckets();
for (Bucket bucket : bucketList) {
  System.out.println(bucket.creationDate() + ", " + bucket.name());
}
```

<a name="listBuckets"></a>
### listBuckets(ListBucketsArgs args)
`public List<Bucket> listBuckets(ListBucketsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#listBuckets-com.ionoscloud.s3.ListBucketsArgs-)_

Lists bucket information of all buckets.

__Parameters__
| Parameter | Type                | Description |
|:----------|:--------------------|:------------|
| ``args``  | _[ListBucketsArgs]_ | Arguments.  |

| Returns                                        |
|:-----------------------------------------------|
| _List<[Bucket]>_ - List of bucket information. |

__Example__
```java
List<Bucket> bucketList =
    apiClient.listBuckets(ListBuckets.builder().extraHeaders(headers).build());
for (Bucket bucket : bucketList) {
  System.out.println(bucket.creationDate() + ", " + bucket.name());
}
```

<a name="listenBucketNotification"></a>
### listenBucketNotification(ListenBucketNotificationArgs args)
`public CloseableIterator<Result<NotificationRecords>> listenBucketNotification(ListenBucketNotificationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#listenBucketNotification-com.ionoscloud.s3.ListenBucketNotificationArgs-)_

Listens events of object prefix and suffix of a bucket. The returned closable iterator is lazily evaluated hence its required to iterate to get new records and must be used with try-with-resource to release underneath network resources.

__Parameters__
| Parameter | Type                             | Description |
|:----------|:---------------------------------|:------------|
| ``args``  | _[ListenBucketNotificationArgs]_ | Arguments.  |

| Returns                                                                                                 |
|:--------------------------------------------------------------------------------------------------------|
| _[CloseableIterator]<[Result]<[NotificationRecords]>>_ - Lazy closable iterator contains event records. |

__Example__
```java
String[] events = {"s3:ObjectCreated:*", "s3:ObjectAccessed:*"};
try (CloseableIterator<Result<NotificationRecords>> ci =
    apiClient.listenBucketNotification(
        ListenBucketNotificationArgs.builder()
            .bucket("bucketName")
            .prefix("")
            .suffix("")
            .events(events)
            .build())) {
  while (ci.hasNext()) {
    NotificationRecords records = ci.next().get();
    for (Event event : records.events()) {
      System.out.println("Event " + event.eventType() + " occurred at " + event.eventTime()
          + " for " + event.bucketName() + "/" + event.objectName());
    }
  }
}
```

<a name="listObjects"></a>
### listObjects(ListObjectsArgs args)
`public Iterable<Result<Item>> listObjects(ListObjectsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#listObjects-com.ionoscloud.s3.ListObjectsArgs-)_

Lists object information of a bucket.

__Parameters__
| Parameter        | Type                | Description               |
|:-----------------|:--------------------|:--------------------------|
| ``args``         | _[ListObjectsArgs]_ | Arguments to list objects |

| Returns                                                                   |
|:--------------------------------------------------------------------------|
| _Iterable<[Result]<[Item]>>_ - Lazy iterator contains object information. |

__Example__
```java
// Lists objects information.
Iterable<Result<Item>> results = apiClient.listObjects(
    ListObjectsArgs.builder().bucket("my-bucketname").build());

// Lists objects information recursively.
Iterable<Result<Item>> results = apiClient.listObjects(
    ListObjectsArgs.builder().bucket("my-bucketname").recursive(true).build());

// Lists maximum 100 objects information whose names starts with 'E' and after 'ExampleGuide.pdf'.
Iterable<Result<Item>> results = apiClient.listObjects(
    ListObjectsArgs.builder()
        .bucket("my-bucketname")
        .startAfter("ExampleGuide.pdf")
        .prefix("E")
        .maxKeys(100)
        .build());

// Lists maximum 100 objects information with version whose names starts with 'E' and after
// 'ExampleGuide.pdf'.
Iterable<Result<Item>> results = apiClient.listObjects(
    ListObjectsArgs.builder()
        .bucket("my-bucketname")
        .startAfter("ExampleGuide.pdf")
        .prefix("E")
        .maxKeys(100)
        .includeVersions(true)
        .build());
```

### makeBucket(MakeBucketArgs args)
`public void makeBucket(MakeBucketArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#makeBucket-com.ionoscloud.s3.MakeBucketArgs-)_

Creates a bucket with given region and object lock feature enabled.

__Parameters__

| Parameter      | Type               | Description                |
|:---------------|:-------------------|:---------------------------|
| ``args``       | _[MakeBucketArgs]_ | Arguments to create bucket |

__Example__

```java
// Create bucket with default region.
apiClient.makeBucket(
    MakeBucketArgs.builder()
        .bucket("my-bucketname")
        .build());

// Create bucket with specific region.
apiClient.makeBucket(
    MakeBucketArgs.builder()
        .bucket("my-bucketname")
        .region("us-west-1")
        .build());

// Create object-lock enabled bucket with specific region.
apiClient.makeBucket(
    MakeBucketArgs.builder()
        .bucket("my-bucketname")
        .region("us-west-1")
        .objectLock(true)
        .build());
```

<a name="deleteBucket"></a>
### deleteBucket(DeleteBucketArgs args)
`public void deleteBucket(DeleteBucketArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteBucket-com.ionoscloud.s3.DeleteBucketArgs-)_

Removes an empty bucket.

__Parameters__

| Parameter    | Type                 | Description     |
|:-------------|:---------------------|:----------------|
| ``args``     | _[DeleteBucketArgs]_ | Arguments.      |

__Example__
```java
apiClient.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
```

<a name="putBucketEncryption"></a>
### putBucketEncryption(PutBucketEncryptionArgs args)
`public void putBucketEncryption(PutBucketEncryptionArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#putBucketEncryption-com.ionoscloud.s3.PutBucketEncryptionArgs-)_

Sets encryption configuration of a bucket.

__Parameters__
| Parameter | Type                        | Description |
|:----------|:----------------------------|:------------|
| ``args``  | _[PutBucketEncryptionArgs]_ | Arguments.  |

__Example__
```java
apiClient.putBucketEncryption(
    PutBucketEncryptionArgs.builder().bucket("my-bucketname").config(config).build());
 ```

<a name="setBucketLifecycle"></a>
### setBucketLifecycle(SetBucketLifecycleArgs args)
`public void setBucketLifecycle(SetBucketLifecycleArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setBucketLifecycle-com.ionoscloud.s3.SetBucketLifecycleArgs-)_

Sets lifecycle configuration to a bucket.

__Parameters__
| Parameter | Type                       | Description |
|:----------|:---------------------------|:------------|
| ``args``  | _[SetBucketLifecycleArgs]_ | Arguments.  |

__Example__
```java
List<LifecycleRule> rules = new LinkedList<>();
rules.add(
    new LifecycleRule(
        Status.ENABLED,
        null,
        null,
        new RuleFilter("documents/"),
        "rule1",
        null,
        null,
        new Transition((ZonedDateTime) null, 30, "GLACIER")));
rules.add(
    new LifecycleRule(
        Status.ENABLED,
        null,
        new Expiration((ZonedDateTime) null, 365, null),
        new RuleFilter("logs/"),
        "rule2",
        null,
        null,
        null));
LifecycleConfiguration config = new LifecycleConfiguration(rules);
apiClient.setBucketLifecycle(
    SetBucketLifecycleArgs.builder().bucket("my-bucketname").config(config).build());
```

<a name="setBucketNotification"></a>
### setBucketNotification(SetBucketNotificationArgs args)
`public void setBucketNotification(SetBucketNotificationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setBucketNotification-com.ionoscloud.s3.SetBucketNotificationArgs-)_

Sets notification configuration to a bucket.

__Parameters__

| Parameter | Type                          | Description |
|:----------|:------------------------------|:------------|
| ``args``  | _[SetBucketNotificationArgs]_ | Arguments.  |

__Example__
```java
List<EventType> eventList = new LinkedList<>();
eventList.add(EventType.OBJECT_CREATED_PUT);
eventList.add(EventType.OBJECT_CREATED_COPY);

QueueConfiguration queueConfiguration = new QueueConfiguration();
queueConfiguration.setQueue("arn:ionos:sqs::1:webhook");
queueConfiguration.setEvents(eventList);
queueConfiguration.setPrefixRule("images");
queueConfiguration.setSuffixRule("pg");

List<QueueConfiguration> queueConfigurationList = new LinkedList<>();
queueConfigurationList.add(queueConfiguration);

NotificationConfiguration config = new NotificationConfiguration();
config.setQueueConfigurationList(queueConfigurationList);

apiClient.setBucketNotification(
    SetBucketNotificationArgs.builder().bucket("my-bucketname").config(config).build());
```

<a name="putBucketPolicy"></a>
### putBucketPolicy(PutBucketPolicyArgs args)
`public void putBucketPolicy(PutBucketPolicyArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#putBucketPolicy-com.ionoscloud.s3.PutBucketPolicyArgs-)_

Sets bucket policy configuration to a bucket.

__Parameters__

| Parameter | Type                    | Description |
|:----------|:------------------------|:------------|
| ``args``  | _[PutBucketPolicyArgs]_ | Arguments.  |

__Example__
```java
// Assume policyJson contains below JSON string;
// {
//     "Statement": [
//         {
//             "Action": [
//                 "s3:GetBucketLocation",
//                 "s3:ListBucket"
//             ],
//             "Effect": "Allow",
//             "Principal": "*",
//             "Resource": "arn:aws:s3:::my-bucketname"
//         },
//         {
//             "Action": "s3:GetObject",
//             "Effect": "Allow",
//             "Principal": "*",
//             "Resource": "arn:aws:s3:::my-bucketname/myobject*"
//         }
//     ],
//     "Version": "2012-10-17"
// }
//
apiClient.putBucketPolicy(
    PutBucketPolicyArgs.builder().bucket("my-bucketname").config(policyJson).build());
```

<a name="setBucketReplication"></a>
### setBucketReplication(SetBucketReplicationArgs args)
`public void setBucketReplication(SetBucketReplicationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setBucketReplication-com.ionoscloud.s3.SetBucketReplicationArgs-)_

Sets bucket replication configuration to a bucket.

__Parameters__

| Parameter | Type                         | Description |
|:----------|:-----------------------------|:------------|
| ``args``  | _[SetBucketReplicationArgs]_ | Arguments.  |

__Example__
```java
Map<String, String> tags = new HashMap<>();
tags.put("key1", "value1");
tags.put("key2", "value2");

ReplicationRule rule =
    new ReplicationRule(
        new DeleteMarkerReplication(Status.DISABLED),
        new ReplicationDestination(
            null, null, "REPLACE-WITH-ACTUAL-DESTINATION-BUCKET-ARN", null, null, null, null),
        null,
        new RuleFilter(new AndOperator("TaxDocs", tags)),
        "rule1",
        null,
        1,
        null,
        Status.ENABLED);

List<ReplicationRule> rules = new LinkedList<>();
rules.add(rule);

ReplicationConfiguration config =
    new ReplicationConfiguration("REPLACE-WITH-ACTUAL-ROLE", rules);

apiClient.setBucketReplication(
    SetBucketReplicationArgs.builder().bucket("my-bucketname").config(config).build());
```

<a name="putBucketTagging"></a>
### putBucketTagging(PutBucketTaggingArgs args)
`public void putBucketTagging(PutBucketTaggingArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#putBucketTagging-com.ionoscloud.s3.PutBucketTaggingArgs-)_

Sets tags to a bucket.

__Parameters__

| Parameter | Type                  | Description |
|:----------|:----------------------|:------------|
| ``args``  | _[PutBucketTaggingArgs]_ | Arguments.  |

__Example__
```java
Map<String, String> map = new HashMap<>();
map.put("Project", "Project One");
map.put("User", "jsmith");
apiClient.putBucketTagging(PutBucketTaggingArgs.builder().bucket("my-bucketname").tags(map).build());
```

<a name="setBucketVersioning"></a>
### setBucketVersioning(SetBucketVersioningArgs args)
`public void setBucketVersioning(SetBucketVersioningArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setBucketVersioning-com.ionoscloud.s3.SetBucketVersioningArgs-)_

Sets versioning configuration of a bucket.

__Parameters__
| Parameter | Type                        | Description |
|:----------|:----------------------------|:------------|
| ``args``  | _[SetBucketVersioningArgs]_ | Arguments.  |

__Example__
```java
apiClient.setBucketVersioning(
    SetBucketVersioningArgs.builder().bucket("my-bucketname").config(config).build());
 ```

<a name="setObjectLockConfiguration"></a>
### setObjectLockConfiguration(SetObjectLockConfigurationArgs args)
`public void setObjectLockConfiguration(SetObjectLockConfigurationArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setObjectLockConfiguration-com.ionoscloud.s3.SetObjectLockConfigurationArgs-)_

Sets object-lock configuration in a bucket.

__Parameters__
| Parameter | Type                               | Description |
|:----------|:-----------------------------------|:------------|
| ``args``  | _[SetObjectLockConfigurationArgs]_ | Arguments.  |

__Example__
```java
ObjectLockConfiguration config =
    new ObjectLockConfiguration(RetentionMode.COMPLIANCE, new RetentionDurationDays(100));
apiClient.setObjectLockConfiguration(
    SetObjectLockConfigurationArgs.builder().bucket("my-bucketname").config(config).build());
```

## 3. Object operations

<a name="composeObject"></a>
### composeObject(ComposeObjectArgs args)
`public ObjectWriteResponse composeObject(ComposeObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#composeObject-com.ionoscloud.s3.ComposeObjectArgs--)_

Creates an object by combining data from different source objects using server-side copy.

 __Parameters__
| Param          | Type                     | Description   |
|:---------------|:-------------------------|:--------------|
| ``args``       | _[ComposeObjectArgs]_    | Arguments.    |

| Returns                                                          |
|:-----------------------------------------------------------------|
| _[ObjectWriteResponse]_ - Contains information of created object.|

__Example__
 ```java
List<ComposeSource> sourceObjectList = new ArrayList<ComposeSource>();
sourceObjectList.add(
  ComposeSource.builder().bucket("my-job-bucket").object("my-objectname-part-one").build());
sourceObjectList.add(
  ComposeSource.builder().bucket("my-job-bucket").object("my-objectname-part-two").build());
sourceObjectList.add(
  ComposeSource.builder().bucket("my-job-bucket").object("my-objectname-part-three").build());

// Create my-bucketname/my-objectname by combining source object list.
apiClient.composeObject(
  ComposeObjectArgs.builder()
      .bucket("my-bucketname")
      .object("my-objectname")
      .sources(sourceObjectList)
      .build());

// Create my-bucketname/my-objectname with user metadata by combining source object
// list.
Map<String, String> userMetadata = new HashMap<>();
userMetadata.put("My-Project", "Project One");
apiClient.composeObject(
    ComposeObjectArgs.builder()
      .bucket("my-bucketname")
      .object("my-objectname")
      .sources(sourceObjectList)
      .userMetadata(userMetadata)
      .build());

// Create my-bucketname/my-objectname with user metadata and server-side encryption
// by combining source object list.
apiClient.composeObject(
  ComposeObjectArgs.builder()
      .bucket("my-bucketname")
      .object("my-objectname")
      .sources(sourceObjectList)
      .userMetadata(userMetadata)
      .ssec(sse)
      .build());
```

<a name="copyObject"></a>
### copyObject(CopyObjectArgs args)
`public ObjectWriteResponse copyObject(CopyObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#copyObject-com.ionoscloud.s3.CopyObjectArgs-)_

Creates an object by server-side copying data from another object.

__Parameters__
| Parameter | Type               | Description |
|:----------|:-------------------|:------------|
| ``args``  | _[CopyObjectArgs]_ | Arguments.  |

| Returns                                                          |
|:-----------------------------------------------------------------|
| _[ObjectWriteResponse]_ - Contains information of created object.|

__Example__

```java
// Create object "my-objectname" in bucket "my-bucketname" by copying from object
// "my-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-objectname")
                .build())
        .build());

// Create object "my-objectname" in bucket "my-bucketname" by copying from object
// "my-source-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-source-objectname")
                .build())
        .build());

// Create object "my-objectname" in bucket "my-bucketname" with SSE-KMS server-side
// encryption by copying from object "my-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-objectname")
                .build())
        .sse(sseKms) // Replace with actual key.
        .build());

// Create object "my-objectname" in bucket "my-bucketname" with SSE-S3 server-side
// encryption by copying from object "my-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-objectname")
                .build())
        .sse(sseS3) // Replace with actual key.
        .build());

// Create object "my-objectname" in bucket "my-bucketname" with SSE-C server-side encryption
// by copying from object "my-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-objectname")
                .build())
        .sse(ssec) // Replace with actual key.
        .build());

// Create object "my-objectname" in bucket "my-bucketname" by copying from SSE-C encrypted
// object "my-source-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-source-objectname")
                .ssec(ssec) // Replace with actual key.
                .build())
        .build());

// Create object "my-objectname" in bucket "my-bucketname" with custom headers conditionally
// by copying from object "my-objectname" in bucket "my-source-bucketname".
apiClient.copyObject(
    CopyObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .source(
            CopySource.builder()
                .bucket("my-source-bucketname")
                .object("my-objectname")
                .matchETag(etag) // Replace with actual etag.
                .build())
        .headers(headers) // Replace with actual headers.
        .build());
```

<a name="deleteObjectTags"></a>
### deleteObjectTags(DeleteObjectTagsArgs args)
`private void deleteObjectTags(DeleteObjectTagsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#deleteObjectTags-com.ionoscloud.s3.DeleteObjectTagsArgs-)_

Deletes tags of an object.

__Parameters__
| Parameter | Type                     | Description |
|:----------|:-------------------------|:------------|
| ``args``  | _[DeleteObjectTagsArgs]_ | Arguments.  |

__Example__
```java
apiClient.deleteObjectTags(
    DeleteObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());
```

<a name="disableObjectLegalHold"></a>
### disableObjectLegalHold(DisableObjectLegalHoldArgs args)
`public void disableObjectLegalHold(DisableObjectLegalHoldArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#disableObjectLegalHold-com.ionoscloud.s3.DisableObjectLegalHoldArgs-)_

Disables legal hold on an object.

 __Parameters__

| Parameter      | Type                           | Description  |
|:---------------|:-------------------------------|:-------------|
| ``args``       | _[DisableObjectLegalHoldArgs]_ | Arguments.   |

 __Example__

```java
// Disables legal hold on an object.
apiClient.disableObjectLegalHold(
    DisableObjectLegalHoldArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .build());
```

<a name="enableObjectLegalHold"></a>
### enableObjectLegalHold(EnableObjectLegalHoldArgs args)
`public void enableObjectLegalHold(EnableObjectLegalHoldArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#enableObjectLegalHold-com.ionoscloud.s3.EnableObjectLegalHoldArgs-)_

Enables legal hold on an object.

 __Parameters__

| Parameter      | Type                          | Description  |
|:---------------|:------------------------------|:-------------|
| ``args``       | _[EnableObjectLegalHoldArgs]_ | Argumments.  |

 __Example__
 ```java

 // Disables legal hold on an object.
apiClient.enableObjectLegalHold(
    EnableObjectLegalHoldArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .build());
```

<a name="getObject"></a>
### getObject(GetObjectArgs args)
`public InputStream getObject(GetObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getObject-com.ionoscloud.s3.GetObjectArgs-)_

Gets data of an object. Returned `InputStream` must be closed after use to release network resources.

__Parameters__
| Parameter      | Type            | Description                |
|:---------------|:----------------|:---------------------------|
| ``args``       | _GetObjectArgs_ | Arguments.                 |

| Returns                               |
|:--------------------------------------|
| _InputStream_ - Contains object data. |

__Example__
```java
// get object given the bucket and object name
try (InputStream stream = apiClient.getObject(
  GetObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .build())) {
  // Read data from stream
}

// get object data from offset
try (InputStream stream = apiClient.getObject(
  GetObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .offset(1024L)
  .build())) {
  // Read data from stream
}

// get object data from offset to length
try (InputStream stream = apiClient.getObject(
  GetObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .offset(1024L)
  .length(4096L)
  .build())) {
  // Read data from stream
}

// get data of an SSE-C encrypted object
try (InputStream stream = apiClient.getObject(
  GetObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .ssec(ssec)
  .build())) {
  // Read data from stream
}

// get object data from offset to length of an SSE-C encrypted object
try (InputStream stream = apiClient.getObject(
  GetObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .offset(1024L)
  .length(4096L)
  .ssec(ssec)
  .build())) {
  // Read data from stream
}
```

<a name="downloadObject"></a>
### downloadObject(DownloadObjectArgs args)
`public void downloadObject(DownloadObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getObject-com.ionoscloud.s3.DownloadObjectArgs-)_

Downloads data of an object to file.

__Parameters__
| Parameter        | Type                 | Description                  |
|:-----------------|:---------------------|:-----------------------------|
| ``args``         | _DownloadObjectArgs_ | Arguments.                   |

__Example__
```java
// Download object given the bucket, object name and output file name
apiClient.downloadObject(
  DownloadObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .filename("my-object-file")
  .build());

// Download server-side encrypted object in bucket to given file name
apiClient.downloadObject(
  DownloadObjectArgs.builder()
  .bucket("my-bucketname")
  .object("my-objectname")
  .ssec(ssec)
  .filename("my-object-file")
  .build());
```

 <a name="getObjectRetention"></a>
### getObjectRetention(GetObjectRetentionArgs args)
`public Retention getObjectRetention(GetObjectRetentionArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getObjectRetention-com.ionoscloud.s3.GetObjectRetentionArgs-)_

Gets retention configuration of an object.

 __Parameters__

| Parameter      | Type                       | Description   |
|:---------------|:---------------------------|:--------------|
| ``args``       | _[GetObjectRetentionArgs]_ | Arguments.    |

| Returns                                         |
|:------------------------------------------------|
| _[Retention]_ - Object retention configuration. |

 __Example__
 ```java
// Object with version id.
Retention retention =
    apiClient.getObjectRetention(
        GetObjectRetentionArgs.builder()
            .bucket("my-bucketname")
            .object("my-objectname")
            .versionId("object-version-id")
            .build());
System.out.println("mode: " + retention.mode() + "until: " + retention.retainUntilDate());
```

<a name="getObjectTags"></a>
### getObjectTags(GetObjectTagsArgs args)
`public Tags getObjectTags(GetObjectTagsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getObjectTags-com.ionoscloud.s3.GetObjectTagsArgs-)_

Gets tags of an object.

__Parameters__
| Parameter | Type                  | Description |
|:----------|:----------------------|:------------|
| ``args``  | _[GetObjectTagsArgs]_ | Arguments.  |


| Returns          |
|:-----------------|
| _[Tags]_ - tags. |

__Example__
```java
Tags tags = apiClient.getObjectTags(
    GetObjectTagsArgs.builder().bucket("my-bucketname").object("my-objectname").build());
```

 <a name="getPresignedObjectUrl"></a>
### getPresignedObjectUrl(GetPresignedObjectUrlArgs args)
`public String getPresignedObjectUrl(GetPresignedObjectUrlArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getPresignedObjectUrl-com.ionoscloud.s3.GetPresignedObjectUrlArgs-)_

Gets presigned URL of an object for HTTP method, expiry time and custom request parameters.

 __Parameters__
| Parameter   | Type                           | Description  |
|:------------|:-------------------------------|:-------------|
| ``args``    | _[GetPresignedObjectUrlArgs]_  | Arguments.   |

| Returns                |
|:-----------------------|
| _String_ - URL string. |

 __Example__
 ```java
// Get presigned URL string to download 'my-objectname' in 'my-bucketname'
// with an expiration of 2 hours.
//
// Additionally also add 'response-content-type' to dynamically set content-type
// for the server response.
Map<String, String> reqParams = new HashMap<String, String>();
reqParams.put("response-content-type", "application/json");

String url =
    apiClient.getPresignedObjectUrl(
        GetPresignedObjectUrlArgs.builder()
            .method(Method.GET)
            .bucket("my-bucketname")
            .object("my-objectname")
            .expiry(2, TimeUnit.HOURS)
            .extraQueryParams(reqParams)
            .build());
System.out.println(url);

// Get presigned URL string to upload 'my-objectname' in 'my-bucketname'
// with an expiration of 1 day.
String url =
    apiClient.getPresignedObjectUrl(
        GetPresignedObjectUrlArgs.builder()
            .method(Method.PUT)
            .bucket("my-bucketname")
            .object("my-objectname")
            .expiry(1, TimeUnit.DAYS)
            .build());
System.out.println(url);

// Get presigned URL string to lookup metadata for 'my-objectname' in 'my-bucketname'
// with an expiration of 2 hours.
//
// Additionally also add 'response-content-type' to dynamically set content-type
// for the server metadata response.
Map<String, String> reqParams = new HashMap<String, String>();
reqParams.put("response-content-type", "application/json");

String url =
    apiClient.getPresignedObjectUrl(
        GetPresignedObjectUrlArgs.builder()
            .method(Method.HEAD)
            .bucket("my-bucketname")
            .object("my-objectname")
            .expiry(2, TimeUnit.HOURS)
            .extraQueryParams(reqParams)
            .build());
System.out.println(url);
```

 <a name="isObjectLegalHoldEnabled"></a>
### isObjectLegalHoldEnabled(IsObjectLegalHoldEnabledArgs args)
`public boolean isObjectLegalHoldEnabled(IsObjectLegalHoldEnabledArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#isObjectLegalHoldEnabled-com.ionoscloud.s3.IsObjectLegalHoldEnabledArgs-)_

Returns true if legal hold is enabled on an object.

 __Parameters__

| Parameter | Type                             | Description  |
|:----------|:---------------------------------|:-------------|
| ``args``  | _[IsObjectLegalHoldEnabledArgs]_ | Arguments.   |


| Returns                                    |
|:-------------------------------------------|
| _boolean_ - True if legal hold is enabled. |

 __Example__

```java
boolean status =
    s3Client.isObjectLegalHoldEnabled(
       IsObjectLegalHoldEnabledArgs.builder()
            .bucket("my-bucketname")
            .object("my-objectname")
            .versionId("object-versionId")
            .build());
if (status) {
  System.out.println("Legal hold is on");
else {
  System.out.println("Legal hold is off");
}
```

<a name="getPresignedPostFormData"></a>
### getPresignedPostFormData(PostPolicy policy)
`public Map<String,String> getPresignedPostFormData(PostPolicy policy)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#getPresignedPostFormData-com.ionoscloud.s3.PostPolicy-)_

Gets form-data of [PostPolicy] of an object to upload its data using POST method.

__Parameters__
| Parameter  | Type           | Description               |
|:-----------|:---------------|:--------------------------|
| ``policy`` | _[PostPolicy]_ | Post policy of an object. |

| Returns                                                                           |
|:----------------------------------------------------------------------------------|
| _Map<String, String>_ - Contains form-data to upload an object using POST method. |

__Example__
```java
// Create new post policy for 'my-bucketname' with 7 days expiry from now.
PostPolicy policy = new PostPolicy("my-bucketname", ZonedDateTime.now().plusDays(7));

// Add condition that 'key' (object name) equals to 'my-objectname'.
policy.addEqualsCondition("key", "my-objectname");

// Add condition that 'Content-Type' starts with 'image/'.
policy.addStartsWithCondition("Content-Type", "image/");

// Add condition that 'content-length-range' is between 64kiB to 10MiB.
policy.addContentLengthRangeCondition(64 * 1024, 10 * 1024 * 1024);

Map<String, String> formData = apiClient.getPresignedPostFormData(policy);

// Upload an image using POST object with form-data.
MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
multipartBuilder.setType(MultipartBody.FORM);
for (Map.Entry<String, String> entry : formData.entrySet()) {
  multipartBuilder.addFormDataPart(entry.getKey(), entry.getValue());
}
multipartBuilder.addFormDataPart("key", "my-objectname");
multipartBuilder.addFormDataPart("Content-Type", "image/png");

// "file" must be added at last.
multipartBuilder.addFormDataPart(
    "file", "my-objectname", RequestBody.create(new File("Pictures/avatar.png"), null));

Request request =
    new Request.Builder()
        .url("<url>")
        .post(multipartBuilder.build())
        .build();
OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
Response response = httpClient.newCall(request).execute();
if (response.isSuccessful()) {
  System.out.println("Pictures/avatar.png is uploaded successfully using POST object");
} else {
  System.out.println("Failed to upload Pictures/avatar.png");
}
```

<a name="putObject"></a>
### putObject(PutObjectArgs args)
`public ObjectWriteResponse putObject(PutObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#putObject-com.ionoscloud.s3.PutObjectArgs-)_

Uploads given stream as object in bucket.

__Parameters__
| Parameter | Type              | Description |
|:----------|:------------------|:------------|
| ``args``  | _[PutObjectArgs]_ | Arguments.  |

| Returns                                                          |
|:-----------------------------------------------------------------|
| _[ObjectWriteResponse]_ - Contains information of created object.|

__Example__
```java
// Upload known sized input stream.
apiClient.putObject(
    PutObjectArgs.builder().bucket("my-bucketname").object("my-objectname").stream(
            inputStream, size, -1)
        .contentType("video/mp4")
        .build());

// Upload unknown sized input stream.
apiClient.putObject(
    PutObjectArgs.builder().bucket("my-bucketname").object("my-objectname").stream(
            inputStream, -1, 10485760)
        .contentType("video/mp4")
        .build());

// Create object ends with '/' (also called as folder or directory).
apiClient.putObject(
    PutObjectArgs.builder().bucket("my-bucketname").object("path/to/").stream(
            new ByteArrayInputStream(new byte[] {}), 0, -1)
        .build());

// Upload input stream with headers and user metadata.
Map<String, String> headers = new HashMap<>();
headers.put("X-Amz-Storage-Class", "REDUCED_REDUNDANCY");
Map<String, String> userMetadata = new HashMap<>();
userMetadata.put("My-Project", "Project One");
apiClient.putObject(
    PutObjectArgs.builder().bucket("my-bucketname").object("my-objectname").stream(
            inputStream, size, -1)
        .headers(headers)
        .userMetadata(userMetadata)
        .build());

// Upload input stream with server-side encryption.
apiClient.putObject(
    PutObjectArgs.builder().bucket("my-bucketname").object("my-objectname").stream(
            inputStream, size, -1)
        .sse(sse)
        .build());
```

<a name="uploadObject"></a>
### uploadObject(UploadObjectArgs args)
`public void uploadObject(UploadObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#uploadObject-com.ionoscloud.s3.UploadObjectArgs-)_

Uploads contents from a file as object in bucket.

__Parameters__
| Parameter | Type                 | Description |
|:----------|:---------------------|:------------|
| ``args``  | _[UploadObjectArgs]_ | Arguments.  |

__Example__
```java
// Upload an JSON file.
apiClient.uploadObject(
    UploadObjectArgs.builder()
        .bucket("my-bucketname").object("my-objectname").filename("person.json").build());

// Upload a video file.
apiClient.uploadObject(
    UploadObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .filename("my-video.avi")
        .contentType("video/mp4")
        .build());
```

<a name="uploadSnowballObjects"></a>
### uploadSnowballObjects(UploadSnowballObjectsArgs args)
`public void uploadSnowballObjects(UploadSnowballObjectsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#uploadSnowballObjects-com.ionoscloud.s3.UploadSnowballObjectsArgs-)_

Uploads multiple objects in a single put call. It is done by creating intermediate TAR file optionally compressed which is uploaded to S3 service.

__Parameters__
| Parameter | Type                          | Description |
|:----------|:------------------------------|:------------|
| ``args``  | _[UploadSnowballObjectsArgs]_ | Arguments.  |

__Example__
```java
List<SnowballObject> objects = new ArrayList<SnowballObject>();
objects.add(
    new SnowballObject(
        "my-object-one",
        new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8)),
        5,
        null));
objects.add(
    new SnowballObject(
        "my-object-two",
        new ByteArrayInputStream("java".getBytes(StandardCharsets.UTF_8)),
        4,
        null));
apiClient.uploadSnowballObjects(
    UploadSnowballObjectsArgs.builder().bucket("my-bucketname").objects(objects).build());
```

<a name="removeObject"></a>
### removeObject(RemoveObjectArgs args)
`public void removeObject(RemoveObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#removeObject-com.ionoscloud.s3.RemoveObjectArgs-)_

Removes an object.

__Parameters__
| Parameter | Type                 | Description |
|:----------|:---------------------|:------------|
| ``args``  | _[RemoveObjectArgs]_ | Arguments.  |

__Example__
```java
// Remove object.
apiClient.removeObject(
    RemoveObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());

// Remove versioned object.
apiClient.removeObject(
    RemoveObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-versioned-objectname")
        .versionId("my-versionid")
        .build());

// Remove versioned object bypassing Governance mode.
apiClient.removeObject(
    RemoveObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-versioned-objectname")
        .versionId("my-versionid")
        .bypassRetentionMode(true)
        .build());
```

<a name="removeObjects"></a>
### removeObjects(RemoveObjectsArgs args)
`public Iterable<Result<DeleteError>> removeObjects(RemoveObjectsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#removeObjects-com.ionoscloud.s3.RemoveObjectsArgs-)_

Removes multiple objects lazily. Its required to iterate the returned Iterable to perform removal.

__Parameters__
| Parameter | Type                  | Description |
|:----------|:----------------------|:------------|
| ``args``  | _[RemoveObjectsArgs]_ | Arguments.  |

| Returns                                                                             |
|:------------------------------------------------------------------------------------|
| _Iterable<[Result]<[DeleteError]>>_ - Lazy iterator contains object removal status. |

__Example__
```java
List<DeleteObject> objects = new LinkedList<>();
objects.add(new DeleteObject("my-objectname1"));
objects.add(new DeleteObject("my-objectname2"));
objects.add(new DeleteObject("my-objectname3"));
Iterable<Result<DeleteError>> results =
    apiClient.removeObjects(
        RemoveObjectsArgs.builder().bucket("my-bucketname").objects(objects).build());
for (Result<DeleteError> result : results) {
  DeleteError error = result.get();
  System.out.println(
      "Error in deleting object " + error.objectName() + "; " + error.message());
}
```

<a name="restoreObject"></a>
### restoreObject(RestoreObjectArgs args)
`public void restoreObject(RestoreObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#restoreObject-com.ionoscloud.s3.RestoreObjectArgs-)_

Restores an object.

__Parameters__
| Parameter | Type                  | Description |
|:----------|:----------------------|:------------|
| ``args``  | _[RestoreObjectArgs]_ | Arguments.  |

__Example__
```java
// Restore object.
apiClient.restoreObject(
    RestoreObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .request(new RestoreRequest(null, null, null, null, null, null))
        .build());

// Restore versioned object.
apiClient.restoreObject(
    RestoreObjectArgs.builder()
        .bucket("my-bucketname")
        .object("my-versioned-objectname")
        .versionId("my-versionid")
        .request(new RestoreRequest(null, null, null, null, null, null))
        .build());
```

 <a name="selectObjectContent"></a>
### selectObjectContent(SelectObjectContentArgs args)
`public SelectResponseStream selectObjectContent(SelectObjectContentArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#selectObjectContent-com.ionoscloud.s3.SelectObjectContentArgs-)_

Selects content of a object by SQL expression.

__Parameters__

| Parameter           | Type                                | Description                           |
|:--------------------|:------------------------------------|:--------------------------------------|
| ``args``            | _[SelectObjectContentArgs]_           | Arguments.                            |

| Returns                                                            |
|:-------------------------------------------------------------------|
| _[SelectResponseStream]_ - Contains filtered records and progress. |

__Example__
```java
String sqlExpression = "select * from S3Object";
InputSerialization is = new InputSerialization(null, false, null, null, FileHeaderInfo.USE, null, null, null);
OutputSerialization os = new OutputSerialization(null, null, null, QuoteFields.ASNEEDED, null);
SelectResponseStream stream =
    apiClient.selectObjectContent(
        SelectObjectContentArgs.builder()
            .bucket("my-bucketname")
            .object("my-objectName")
            .sqlExpression(sqlExpression)
            .inputSerialization(is)
            .outputSerialization(os)
            .requestProgress(true)
            .build());

byte[] buf = new byte[512];
int bytesRead = stream.read(buf, 0, buf.length);
System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));

Stats stats = stream.stats();
System.out.println("bytes scanned: " + stats.bytesScanned());
System.out.println("bytes processed: " + stats.bytesProcessed());
System.out.println("bytes returned: " + stats.bytesReturned());

stream.close();
```

<a name="setObjectRetention"></a>
### setObjectRetention(SetObjectRetentionArgs args)
`public void setObjectLockRetention(SetObjectRetentionArgs)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setObjectRetention-com.ionoscloud.s3.SetObjectRetentionArgs-)_

Sets retention configuration to an object.

 __Parameters__

| Parameter        | Type                       | Description  |
|:-----------------|:---------------------------|:-------------|
| ``args``         | _[SetObjectRetentionArgs]_ | Arguments.   |

 __Example__
```java
Retention retention = new Retention(RetentionMode.COMPLIANCE, ZonedDateTime.now().plusYears(1));
apiClient.setObjectRetention(
    SetObjectRetentionArgs.builder()
        .bucket("my-bucketname")
        .object("my-objectname")
        .config(retention)
        .bypassGovernanceMode(true)
        .build());
```

<a name="setObjectTags"></a>
### setObjectTags(SetObjectTagsArgs args)
`public void setObjectTags(SetObjectTagsArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#setObjectTags-com.ionoscloud.s3.SetObjectTagsArgs-)_

Sets tags to an object.

__Parameters__

| Parameter | Type                  | Description |
|:----------|:----------------------|:------------|
| ``args``  | _[SetObjectTagsArgs]_ | Arguments.  |

__Example__
```java
Map<String, String> map = new HashMap<>();
map.put("Project", "Project One");
map.put("User", "jsmith");
apiClient.setObjectTags(
    SetObjectTagsArgs.builder().bucket("my-bucketname").object("my-objectname").tags(map).build());
```

<a name="statObject"></a>
### statObject(StatObjectArgs args)
`public ObjectStat statObject(StatObjectArgs args)` _[[Javadoc]](http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#statObject-com.ionoscloud.s3.StatObjectArgs-)_

Gets object information and metadata of an object.

__Parameters__
| Parameter | Type               | Description |
|:----------|:-------------------|:------------|
| ``args``  | _[StatObjectArgs]_ | Arguments.  |

| Returns                                                     |
|:------------------------------------------------------------|
| _[ObjectStat]_ - Populated object information and metadata. |

__Example__
```java
// Get information of an object.
ObjectStat objectStat =
    apiClient.statObject(
        StatObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());

// Get information of SSE-C encrypted object.
ObjectStat objectStat =
    apiClient.statObject(
        StatObjectArgs.builder()
            .bucket("my-bucketname")
            .object("my-objectname")
            .ssec(ssec)
            .build());

// Get information of a versioned object.
ObjectStat objectStat =
    apiClient.statObject(
        StatObjectArgs.builder()
            .bucket("my-bucketname")
            .object("my-objectname")
            .versionId("version-id")
            .build());

// Get information of a SSE-C encrypted versioned object.
ObjectStat objectStat =
    apiClient.statObject(
        StatObjectArgs.builder()
            .bucket("my-bucketname")
            .object("my-objectname")
            .versionId("version-id")
            .ssec(ssec)
            .build());
```

## 5. Explore Further
- [Build your own Photo API Service - Full Application Example ](https://github.com/ionos-cloud/sdk-java-s3-rest-example)
- [Complete JavaDoc](http://github.com/ionos-cloud/sdk-java-s3/)

[constructor-1]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-
[constructor-2]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.net.URL-
[constructor-3]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-okhttp3.HttpUrl-
[constructor-4]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-java.lang.String-java.lang.String-
[constructor-5]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-int-java.lang.String-java.lang.String-
[constructor-6]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-java.lang.String-java.lang.String-boolean-
[constructor-7]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-int-java.lang.String-java.lang.String-java.lang.String-boolean-
[constructor-8]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-okhttp3.HttpUrl-java.lang.String-java.lang.String-
[constructor-9]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.net.URL-java.lang.String-java.lang.String-
[constructor-10]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-java.lang.String-java.lang.String-java.lang.String-
[constructor-11]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-int-java.lang.String-java.lang.String-java.lang.String-boolean-
[constructor-12]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ApiClient.html#ApiClient-java.lang.String-java.lang.Integer-java.lang.String-java.lang.String-java.lang.String-java.lang.Boolean-okhttp3.OkHttpClient-
[NotificationConfiguration]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/NotificationConfiguration.html
[ObjectLockConfiguration]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/ObjectLockConfiguration.html
[Bucket]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/Bucket.html
[CloseableIterator]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3CloseableIterator.html
[Result]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3Result.html
[NotificationRecords]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/NotificationRecords.html
[Upload]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/Upload.html
[Item]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/Item.html
[ComposeSource]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ComposeSource.html
[ServerSideEncryption]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ServerSideEncryption.html
[ServerSideEncryptionCustomerKey]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ServerSideEncryptionCustomerKey.html
[CopyConditions]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3CopyConditions.html
[PostPolicy]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3PostPolicy.html
[PutObjectOptions]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3PutObjectOptions.html
[InputSerialization]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/InputSerialization.html
[OutputSerialization]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/OutputSerialization.html
[Retention]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/Retention.html
[ObjectStat]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ObjectStat.html
[DeleteError]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/DeleteError.html
[SelectResponseStream]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SelectResponseStream.html
[MakeBucketArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3MakeBucketArgs.html
[ListObjectsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ListObjectsArgs.html
[DeleteBucketArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketArgs.html
[SetObjectRetentionArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetObjectRetentionArgs.html
[GetObjectRetentionArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetObjectRetentionArgs.html
[Method]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3http/Method.html
[StatObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3StatObjectArgs.html
[RemoveObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3RemoveObjectArgs.html
[SseConfiguration]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/SseConfiguration.html
[DeleteBucketEncryptionArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketEncryptionArgs.html
[GetBucketEncryptionArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketEncryptionArgs.html
[PutBucketEncryptionArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3PutBucketEncryptionArgs.html
[Tags]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/Tags.html
[DeleteBucketTaggingArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketTaggingArgs.html
[GetBucketTaggingArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketTaggingArgs.html
[PutBucketTaggingArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3PutBucketTaggingArgs.html
[DeleteObjectTagsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteObjectTagsArgs.html
[GetObjectTagsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetObjectTagsArgs.html
[SetObjectTagsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetObjectTagsArgs.html
[LifecycleConfiguration]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/LifecycleConfiguration.html
[DeleteBucketLifecycleArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketLifecycleArgs.html
[GetBucketLifecycleArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketLifecycleArgs.html
[SetBucketLifecycleArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetBucketLifecycleArgs.html
[GetBucketPolicyArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketPolicyArgs.html
[PutBucketPolicyArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3PutBucketPolicyArgs.html
[DeleteBucketPolicyArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketPolicyArgs.html
[GetObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetObjectArgs.html
[DownloadObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DownloadObjectArgs.html
[HeadBucketArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3HeadBucketArgs.html
[EnableObjectLegalHoldArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3EnableObjectLegalHoldArgs.html
[DisableObjectLegalHoldArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DisableObjectLegalHoldArgs.html
[IsObjectLegalHoldEnabledArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3IsObjectLegalHoldEnabledArgs.html
[DeleteBucketNotificationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketNotificationArgs.html
[GetBucketNotificationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketNotificationArgs.html
[SetBucketNotificationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetBucketNotificationArgs.html
[ListenBucketNotificationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ListenBucketNotificationArgs.html
[SelectObjectContentArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SelectObjectContentArgs.html
[GetObjectLockConfigurationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetObjectLockConfigurationArgs.html
[SetObjectLockConfigurationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetObjectLockConfigurationArgs.html
[DeleteObjectLockConfigurationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteObjectLockConfigurationArgs.html
[GetPresignedObjectUrlArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetPresignedObjectUrlArgs.html
[RemoveObjectsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3RemoveObjectsArgs.html
[CopyObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3CopyObjectArgs.html
[PutObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3PutObjectArgs.html
[UploadObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3UploadObjectArgs.html
[UploadSnowballObjectsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3UploadSnowballObjectsArgs.html
[ComposeObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ComposeObjectArgs.html
[ObjectWriteResponse]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ObjectWriteResponse.html
[ListBucketsArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3ListBucketsArgs.html
[DeleteBucketReplicationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3DeleteBucketReplicationArgs.html
[GetBucketReplicationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketReplicationArgs.html
[SetBucketReplicationArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetBucketReplicationArgs.html
[ReplicationConfiguration]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/ReplicationConfiguration.html
[VersioningConfiguration]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3messages/VersioningConfiguration.html
[GetBucketVersioningArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3GetBucketVersioningArgs.html
[SetBucketVersioningArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3SetBucketVersioningArgs.html
[RestoreObjectArgs]: http://github.com/ionos-cloud/sdk-java-s3/com/ionoscloud/s3RestoreObjectArgs.html

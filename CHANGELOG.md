* Allow empty ETag in CompleteMultipartUploadOutput
* Allow empty Filter and Prefix field in LifecycleRule
* updated ResponseDate datetime format to accept nanoseconds
* Commented some tests, altered some variable in FunctionalTest to pass tests with the IONOS API
* Rename some methods and their argument objects to correspond with Ionos operation Ids:
    bucketExists -> headBucket
    removeBucket -> deleteBucket
    SetBucketEncryption -> putBucketEncryption
    SetBucketPolicy -> putBucketPolicy
    setBucketTags -> putBucketTagging
    getBucketTags -> getBucketTagging
    deleteBucketTags -> deleteBucketTagging
    uploadObject -> postObject
    getObjectTags -> getObjectTagging
    setObjectTags -> putObjectTagging
    deleteObjectTags -> deleteObjectTagging
    setBucketLifecycle -> putBucketLifecycle
    setBucketVersioning -> putBucketVersioning
    setObjectLockConfiguration -> putObjectLockConfiguration
    setObjectRetention -> putObjectRetention
    isObjectLegalHoldEnabled -> getObjectLegalHold
    enableObjectLegalHold -> putObjectLegalHold
* Remove AdminApi
* Rename package from "io.minio" to "com.ionoscloud.s3"
* Remove or replace Minio references with Ionoscloud (alter class names, comments, variable names and values etc.)

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
* Added `IONOS_DEBUG_FILE` environment variable to enable debugging to a file. ("/dev/stdout" can be used)
* Remove AdminApi
* Rename package from "io.minio" to "com.ionoscloud.s3"
* Remove or replace Minio references with Ionoscloud (alter class names, comments, variable names and values etc.)

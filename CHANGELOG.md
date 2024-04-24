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

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketEncryption} and {@link
 * ApiClient#getBucketEncryption}.
 */
public class GetBucketEncryptionArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketEncryptionArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketEncryptionArgs> {}
}

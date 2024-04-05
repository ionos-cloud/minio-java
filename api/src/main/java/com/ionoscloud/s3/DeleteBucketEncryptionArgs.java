

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteBucketEncryption} and {@link
 * ApiClient#deleteBucketEncryption}.
 */
public class DeleteBucketEncryptionArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketEncryptionArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, DeleteBucketEncryptionArgs> {}
}

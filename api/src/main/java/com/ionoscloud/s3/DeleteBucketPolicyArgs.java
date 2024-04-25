package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteBucketPolicy} and {@link
 * ApiClient#deleteBucketPolicy}.
 */
public class DeleteBucketPolicyArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketPolicyArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, DeleteBucketPolicyArgs> {}
}

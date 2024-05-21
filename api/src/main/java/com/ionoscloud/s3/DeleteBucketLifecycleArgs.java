package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteBucketLifecycle} and {@link
 * ApiClient#deleteBucketLifecycle}.
 */
public class DeleteBucketLifecycleArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketLifecycleArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, DeleteBucketLifecycleArgs> {}
}

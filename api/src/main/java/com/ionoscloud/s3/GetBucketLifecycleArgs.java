package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketLifecycle} and {@link
 * ApiClient#getBucketLifecycle}.
 */
public class GetBucketLifecycleArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketLifecycleArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketLifecycleArgs> {}
}



package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketPolicy} and {@link
 * ApiClient#getBucketPolicy}.
 */
public class GetBucketPolicyArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketPolicyArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketPolicyArgs> {}
}



package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketVersioning} and {@link
 * ApiClient#getBucketVersioning}.
 */
public class GetBucketVersioningArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketVersioningArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketVersioningArgs> {}
}

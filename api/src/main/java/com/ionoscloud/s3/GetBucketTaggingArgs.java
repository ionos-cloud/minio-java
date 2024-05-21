package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketTagging} and {@link ApiClient#getBucketTagging}.
 */
public class GetBucketTaggingArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketTaggingArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketTaggingArgs> {}
}

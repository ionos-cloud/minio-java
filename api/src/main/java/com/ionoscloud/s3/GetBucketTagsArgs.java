

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketTags} and {@link ApiClient#getBucketTags}.
 */
public class GetBucketTagsArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketTagsArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketTagsArgs> {}
}

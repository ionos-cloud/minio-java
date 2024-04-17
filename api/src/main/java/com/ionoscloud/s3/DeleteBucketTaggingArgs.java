

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteBucketTags} and {@link
 * ApiClient#deleteBucketTags}.
 */
public class DeleteBucketTaggingArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketTaggingArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, DeleteBucketTaggingArgs> {}
}

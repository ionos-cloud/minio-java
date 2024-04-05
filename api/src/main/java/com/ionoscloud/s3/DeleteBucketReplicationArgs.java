

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteBucketReplication} and {@link
 * ApiClient#deleteBucketReplication}.
 */
public class DeleteBucketReplicationArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketReplicationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, DeleteBucketReplicationArgs> {}
}

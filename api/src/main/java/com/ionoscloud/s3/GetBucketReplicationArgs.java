package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketReplication} and {@link
 * ApiClient#getBucketReplication}.
 */
public class GetBucketReplicationArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketReplicationArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, GetBucketReplicationArgs> {}
}



package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#bucketExists} and {@link ApiClient#bucketExists}. */
public class BucketExistsArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link BucketExistsArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, BucketExistsArgs> {}
}

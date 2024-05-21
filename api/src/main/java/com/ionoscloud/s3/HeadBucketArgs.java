package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#bucketExists} and {@link ApiClient#bucketExists}. */
public class HeadBucketArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link HeadBucketArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, HeadBucketArgs> {}
}

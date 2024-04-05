

package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#removeBucket} and {@link ApiClient#removeBucket}. */
public class RemoveBucketArgs extends BucketArgs {

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link RemoveBucketArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, RemoveBucketArgs> {}
}

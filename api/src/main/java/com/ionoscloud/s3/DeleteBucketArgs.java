

package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#deleteBucket} and {@link ApiClient#deleteBucket}. */
public class DeleteBucketArgs extends BucketArgs {

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, DeleteBucketArgs> {}
}

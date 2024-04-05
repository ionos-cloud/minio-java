

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteBucketNotification} and {@link
 * ApiClient#deleteBucketNotification}.
 */
public class DeleteBucketNotificationArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteBucketNotificationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, DeleteBucketNotificationArgs> {}
}

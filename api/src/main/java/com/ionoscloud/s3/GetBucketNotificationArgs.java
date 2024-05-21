package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getBucketNotification} and {@link
 * ApiClient#getBucketNotification}.
 */
public class GetBucketNotificationArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetBucketNotificationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, GetBucketNotificationArgs> {}
}

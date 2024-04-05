

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getObjectLockConfiguration} and {@link
 * ApiClient#getObjectLockConfiguration}.
 */
public class GetObjectLockConfigurationArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetObjectLockConfigurationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, GetObjectLockConfigurationArgs> {}
}

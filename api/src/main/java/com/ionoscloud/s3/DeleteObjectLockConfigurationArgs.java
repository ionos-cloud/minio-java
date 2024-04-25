

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteObjectLockConfiguration} and {@link
 * ApiClient#deleteObjectLockConfiguration}.
 */
public class DeleteObjectLockConfigurationArgs extends BucketArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteObjectLockConfigurationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, DeleteObjectLockConfigurationArgs> {}
}

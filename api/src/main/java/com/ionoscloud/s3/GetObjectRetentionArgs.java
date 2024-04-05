

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getObjectRetention} and {@link
 * ApiClient#getObjectRetention}.
 */
public class GetObjectRetentionArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetObjectRetentionArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, GetObjectRetentionArgs> {}
}

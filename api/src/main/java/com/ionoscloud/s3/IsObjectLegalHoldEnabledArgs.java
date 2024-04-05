

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#isObjectLegalHoldEnabled} and {@link
 * ApiClient#isObjectLegalHoldEnabled}.
 */
public class IsObjectLegalHoldEnabledArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link IsObjectLegalHoldEnabledArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, IsObjectLegalHoldEnabledArgs> {}
}

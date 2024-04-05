

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#disableObjectLegalHold} and {@link
 * ApiClient#disableObjectLegalHold}.
 */
public class DisableObjectLegalHoldArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DisableObjectLegalHoldArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, DisableObjectLegalHoldArgs> {}
}

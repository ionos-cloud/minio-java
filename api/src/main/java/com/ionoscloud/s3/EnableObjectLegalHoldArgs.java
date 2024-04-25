

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#enableObjectLegalHold} and {@link
 * ApiClient#enableObjectLegalHold}.
 */
public class EnableObjectLegalHoldArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link EnableObjectLegalHoldArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, EnableObjectLegalHoldArgs> {}
}

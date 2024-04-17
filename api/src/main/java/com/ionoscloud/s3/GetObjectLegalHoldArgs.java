

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getObjectLegalHold} and {@link
 * ApiClient#getObjectLegalHold}.
 */
public class GetObjectLegalHoldArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetObjectLegalHoldArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, GetObjectLegalHoldArgs> {}
}

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#putObjectLegalHold} and {@link
 * ApiClient#putObjectLegalHold}.
 */
public class PutObjectLegalHoldArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutObjectLegalHoldArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, PutObjectLegalHoldArgs> {}
}

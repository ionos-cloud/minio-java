

package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#listBuckets} and {@link ApiClient#listBuckets}. */
public class ListBucketsArgs extends BaseArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link ListBucketsArgs}. */
  public static final class Builder extends BaseArgs.Builder<Builder, ListBucketsArgs> {
    @Override
    protected void validate(ListBucketsArgs args) {}
  }
}

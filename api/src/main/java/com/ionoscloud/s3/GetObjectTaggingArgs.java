

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getObjectTagging} and {@link ApiClient#getObjectTagging}.
 */
public class GetObjectTaggingArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetObjectTaggingArgs}. */
  public static final class Builder extends ObjectVersionArgs.Builder<Builder, GetObjectTaggingArgs> {}
}



package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#getObjectTags} and {@link ApiClient#getObjectTags}.
 */
public class GetObjectTagsArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetObjectTagsArgs}. */
  public static final class Builder extends ObjectVersionArgs.Builder<Builder, GetObjectTagsArgs> {}
}

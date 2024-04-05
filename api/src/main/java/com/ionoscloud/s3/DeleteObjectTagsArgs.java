

package com.ionoscloud.s3;

/**
 * Argument class of {@link ApiAsyncClient#deleteObjectTags} and {@link
 * ApiClient#deleteObjectTags}.
 */
public class DeleteObjectTagsArgs extends ObjectVersionArgs {
  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link DeleteObjectTagsArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, DeleteObjectTagsArgs> {}
}

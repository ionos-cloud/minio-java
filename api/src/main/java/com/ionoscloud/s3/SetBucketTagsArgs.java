

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.Tags;
import java.util.Map;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketTags} and {@link ApiClient#setBucketTags}.
 */
public class SetBucketTagsArgs extends BucketArgs {
  private Tags tags;

  public Tags tags() {
    return tags;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketTagsArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketTagsArgs> {
    private void validateTags(Tags tags) {
      validateNotNull(tags, "tags");
    }

    protected void validate(SetBucketTagsArgs args) {
      super.validate(args);
      validateTags(args.tags);
    }

    public Builder tags(Map<String, String> map) {
      validateNotNull(map, "map for tags");
      operations.add(args -> args.tags = Tags.newBucketTags(map));
      return this;
    }

    public Builder tags(Tags tags) {
      validateTags(tags);
      operations.add(args -> args.tags = tags);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SetBucketTagsArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketTagsArgs that = (SetBucketTagsArgs) o;
    return Objects.equals(tags, that.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), tags);
  }
}

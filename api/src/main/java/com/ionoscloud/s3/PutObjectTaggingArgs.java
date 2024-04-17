

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.Tags;
import java.util.Map;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putObjectTagging} and {@link ApiClient#putObjectTagging}.
 */
public class PutObjectTaggingArgs extends ObjectVersionArgs {
  private Tags tags;

  public Tags tags() {
    return tags;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutObjectTaggingArgs}. */
  public static final class Builder extends ObjectVersionArgs.Builder<Builder, PutObjectTaggingArgs> {
    private void validateTags(Tags tags) {
      validateNotNull(tags, "tags");
    }

    protected void validate(PutObjectTaggingArgs args) {
      super.validate(args);
      validateTags(args.tags);
    }

    public Builder tags(Map<String, String> map) {
      validateNotNull(map, "map for tags");
      operations.add(args -> args.tags = Tags.newObjectTags(map));
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
    if (!(o instanceof PutObjectTaggingArgs)) return false;
    if (!super.equals(o)) return false;
    PutObjectTaggingArgs that = (PutObjectTaggingArgs) o;
    return Objects.equals(tags, that.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), tags);
  }
}

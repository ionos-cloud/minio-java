package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.Tags;
import java.util.Map;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putBucketTagging} and {@link ApiClient#putBucketTagging}.
 */
public class PutBucketTaggingArgs extends BucketArgs {
  private Tags tags;

  public Tags tags() {
    return tags;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutBucketTaggingArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, PutBucketTaggingArgs> {
    private void validateTags(Tags tags) {
      validateNotNull(tags, "tags");
    }

    protected void validate(PutBucketTaggingArgs args) {
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
    if (!(o instanceof PutBucketTaggingArgs)) return false;
    if (!super.equals(o)) return false;
    PutBucketTaggingArgs that = (PutBucketTaggingArgs) o;
    return Objects.equals(tags, that.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), tags);
  }
}

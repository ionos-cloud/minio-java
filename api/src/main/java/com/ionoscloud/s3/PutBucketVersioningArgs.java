

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.VersioningConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putBucketVersioning} and {@link
 * ApiClient#putBucketVersioning}.
 */
public class PutBucketVersioningArgs extends BucketArgs {
  private VersioningConfiguration config;

  public VersioningConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutBucketVersioningArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, PutBucketVersioningArgs> {
    private void validateConfig(VersioningConfiguration config) {
      validateNotNull(config, "versioning configuration");
    }

    protected void validate(PutBucketVersioningArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(VersioningConfiguration config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PutBucketVersioningArgs)) return false;
    if (!super.equals(o)) return false;
    PutBucketVersioningArgs that = (PutBucketVersioningArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

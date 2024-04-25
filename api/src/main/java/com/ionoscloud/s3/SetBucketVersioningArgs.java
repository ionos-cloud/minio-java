

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.VersioningConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketVersioning} and {@link
 * ApiClient#setBucketVersioning}.
 */
public class SetBucketVersioningArgs extends BucketArgs {
  private VersioningConfiguration config;

  public VersioningConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketVersioningArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketVersioningArgs> {
    private void validateConfig(VersioningConfiguration config) {
      validateNotNull(config, "versioning configuration");
    }

    protected void validate(SetBucketVersioningArgs args) {
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
    if (!(o instanceof SetBucketVersioningArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketVersioningArgs that = (SetBucketVersioningArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

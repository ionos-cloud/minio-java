

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.LifecycleConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketLifecycle} and {@link
 * ApiClient#setBucketLifecycle}.
 */
public class SetBucketLifecycleArgs extends BucketArgs {
  private LifecycleConfiguration config;

  public LifecycleConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketLifecycleArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketLifecycleArgs> {
    private void validateConfig(LifecycleConfiguration config) {
      validateNotNull(config, "lifecycle configuration");
    }

    protected void validate(SetBucketLifecycleArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(LifecycleConfiguration config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SetBucketLifecycleArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketLifecycleArgs that = (SetBucketLifecycleArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

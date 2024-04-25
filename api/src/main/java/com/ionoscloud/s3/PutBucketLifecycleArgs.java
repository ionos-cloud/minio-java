package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.LifecycleConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putBucketLifecycle} and {@link
 * ApiClient#putBucketLifecycle}.
 */
public class PutBucketLifecycleArgs extends BucketArgs {
  private LifecycleConfiguration config;

  public LifecycleConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutBucketLifecycleArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, PutBucketLifecycleArgs> {
    private void validateConfig(LifecycleConfiguration config) {
      validateNotNull(config, "lifecycle configuration");
    }

    protected void validate(PutBucketLifecycleArgs args) {
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
    if (!(o instanceof PutBucketLifecycleArgs)) return false;
    if (!super.equals(o)) return false;
    PutBucketLifecycleArgs that = (PutBucketLifecycleArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

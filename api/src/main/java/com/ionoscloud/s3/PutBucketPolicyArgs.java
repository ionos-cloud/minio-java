package com.ionoscloud.s3;

import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putBucketPolicy} and {@link ApiClient#putBucketPolicy}.
 */
public class PutBucketPolicyArgs extends BucketArgs {
  private String config;

  public String config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutBucketPolicyArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, PutBucketPolicyArgs> {
    private void validateConfig(String config) {
      validateNotNull(config, "policy configuration");
    }

    @Override
    protected void validate(PutBucketPolicyArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(String config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PutBucketPolicyArgs)) return false;
    if (!super.equals(o)) return false;
    PutBucketPolicyArgs that = (PutBucketPolicyArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

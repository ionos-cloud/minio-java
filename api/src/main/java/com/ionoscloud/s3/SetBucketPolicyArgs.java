

package com.ionoscloud.s3;

import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketPolicy} and {@link
 * ApiClient#setBucketPolicy}.
 */
public class SetBucketPolicyArgs extends BucketArgs {
  private String config;

  public String config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketPolicyArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketPolicyArgs> {
    private void validateConfig(String config) {
      validateNotNull(config, "policy configuration");
    }

    @Override
    protected void validate(SetBucketPolicyArgs args) {
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
    if (!(o instanceof SetBucketPolicyArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketPolicyArgs that = (SetBucketPolicyArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

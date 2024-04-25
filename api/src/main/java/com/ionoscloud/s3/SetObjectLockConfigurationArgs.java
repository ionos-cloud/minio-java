

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ObjectLockConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setObjectLockConfiguration} and {@link
 * ApiClient#setObjectLockConfiguration}.
 */
public class SetObjectLockConfigurationArgs extends BucketArgs {
  private ObjectLockConfiguration config;

  public ObjectLockConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetObjectLockConfigurationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, SetObjectLockConfigurationArgs> {
    private void validateConfig(ObjectLockConfiguration config) {
      validateNotNull(config, "object-lock configuration");
    }

    @Override
    protected void validate(SetObjectLockConfigurationArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(ObjectLockConfiguration config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SetObjectLockConfigurationArgs)) return false;
    if (!super.equals(o)) return false;
    SetObjectLockConfigurationArgs that = (SetObjectLockConfigurationArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

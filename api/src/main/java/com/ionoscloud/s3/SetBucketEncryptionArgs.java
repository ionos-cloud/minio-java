

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.SseConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketEncryption} and {@link
 * ApiClient#setBucketEncryption}.
 */
public class SetBucketEncryptionArgs extends BucketArgs {
  private SseConfiguration config;

  public SseConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketEncryptionArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketEncryptionArgs> {
    private void validateConfig(SseConfiguration config) {
      validateNotNull(config, "encryption configuration");
    }

    protected void validate(SetBucketEncryptionArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(SseConfiguration config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SetBucketEncryptionArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketEncryptionArgs that = (SetBucketEncryptionArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

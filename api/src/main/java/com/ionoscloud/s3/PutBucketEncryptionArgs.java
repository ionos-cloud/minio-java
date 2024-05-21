package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.SseConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putBucketEncryption} and {@link
 * ApiClient#putBucketEncryption}.
 */
public class PutBucketEncryptionArgs extends BucketArgs {
  private SseConfiguration config;

  public SseConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutBucketEncryptionArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, PutBucketEncryptionArgs> {
    private void validateConfig(SseConfiguration config) {
      validateNotNull(config, "encryption configuration");
    }

    protected void validate(PutBucketEncryptionArgs args) {
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
    if (!(o instanceof PutBucketEncryptionArgs)) return false;
    if (!super.equals(o)) return false;
    PutBucketEncryptionArgs that = (PutBucketEncryptionArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

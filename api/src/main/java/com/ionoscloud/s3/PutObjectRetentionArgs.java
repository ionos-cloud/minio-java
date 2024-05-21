

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.Retention;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#putObjectRetention} and {@link
 * ApiClient#putObjectRetention}.
 */
public class PutObjectRetentionArgs extends ObjectVersionArgs {
  private Retention config;
  private boolean bypassGovernanceMode;

  public Retention config() {
    return config;
  }

  public boolean bypassGovernanceMode() {
    return bypassGovernanceMode;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link PutObjectRetentionArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, PutObjectRetentionArgs> {
    private void validateConfig(Retention config) {
      validateNotNull(config, "retention configuration");
    }

    protected void validate(PutObjectRetentionArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(Retention config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }

    public Builder bypassGovernanceMode(boolean bypassGovernanceMode) {
      operations.add(args -> args.bypassGovernanceMode = bypassGovernanceMode);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PutObjectRetentionArgs)) return false;
    if (!super.equals(o)) return false;
    PutObjectRetentionArgs that = (PutObjectRetentionArgs) o;
    return bypassGovernanceMode == that.bypassGovernanceMode && Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config, bypassGovernanceMode);
  }
}

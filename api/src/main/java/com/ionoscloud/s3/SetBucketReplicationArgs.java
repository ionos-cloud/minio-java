package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ReplicationConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketReplication} and {@link
 * ApiClient#setBucketReplication}.
 */
public class SetBucketReplicationArgs extends BucketArgs {
  private ReplicationConfiguration config;
  private String objectLockToken;

  public ReplicationConfiguration config() {
    return config;
  }

  public String objectLockToken() {
    return objectLockToken;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketReplicationArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketReplicationArgs> {
    private void validateConfig(ReplicationConfiguration config) {
      validateNotNull(config, "replication configuration");
    }

    private void validateObjectLockToken(String token) {
      validateNullOrNotEmptyString(token, "object lock token");
    }

    @Override
    protected void validate(SetBucketReplicationArgs args) {
      super.validate(args);
      validateConfig(args.config);
      validateObjectLockToken(args.objectLockToken);
    }

    public Builder config(ReplicationConfiguration config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }

    public Builder objectLockToken(String token) {
      validateObjectLockToken(token);
      operations.add(args -> args.objectLockToken = token);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SetBucketReplicationArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketReplicationArgs that = (SetBucketReplicationArgs) o;
    return Objects.equals(config, that.config)
        && Objects.equals(objectLockToken, that.objectLockToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config, objectLockToken);
  }
}

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.NotificationConfiguration;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#setBucketNotification} and {@link
 * ApiClient#setBucketNotification}.
 */
public class SetBucketNotificationArgs extends BucketArgs {
  private NotificationConfiguration config;

  public NotificationConfiguration config() {
    return config;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link SetBucketNotificationArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, SetBucketNotificationArgs> {
    private void validateConfig(NotificationConfiguration config) {
      validateNotNull(config, "notification configuration");
    }

    protected void validate(SetBucketNotificationArgs args) {
      super.validate(args);
      validateConfig(args.config);
    }

    public Builder config(NotificationConfiguration config) {
      validateConfig(config);
      operations.add(args -> args.config = config);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SetBucketNotificationArgs)) return false;
    if (!super.equals(o)) return false;
    SetBucketNotificationArgs that = (SetBucketNotificationArgs) o;
    return Objects.equals(config, that.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), config);
  }
}

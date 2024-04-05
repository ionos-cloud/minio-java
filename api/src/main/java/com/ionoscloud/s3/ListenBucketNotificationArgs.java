

package com.ionoscloud.s3;

import java.util.Arrays;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#listenBucketNotification} and {@link
 * ApiClient#listenBucketNotification}.
 */
public class ListenBucketNotificationArgs extends BucketArgs {
  private String prefix;
  private String suffix;
  private String[] events = null;

  public String prefix() {
    return prefix;
  }

  public String suffix() {
    return suffix;
  }

  public String[] events() {
    return Arrays.copyOf(events, events.length);
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link ListenBucketNotificationArgs}. */
  public static final class Builder
      extends BucketArgs.Builder<Builder, ListenBucketNotificationArgs> {
    private void validateEvents(String[] events) {
      validateNotNull(events, "events");
    }

    protected void validate(ListenBucketNotificationArgs args) {
      if (args.bucketName != null) {
        super.validate(args);
      }
      validateEvents(args.events);
    }

    public Builder prefix(String prefix) {
      operations.add(args -> args.prefix = prefix);
      return this;
    }

    public Builder suffix(String suffix) {
      operations.add(args -> args.suffix = suffix);
      return this;
    }

    public Builder events(String[] events) {
      validateEvents(events);
      final String[] eventsCopy = Arrays.copyOf(events, events.length);
      operations.add(args -> args.events = eventsCopy);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ListenBucketNotificationArgs)) return false;
    if (!super.equals(o)) return false;
    ListenBucketNotificationArgs that = (ListenBucketNotificationArgs) o;
    return Objects.equals(prefix, that.prefix)
        && Objects.equals(suffix, that.suffix)
        && Arrays.equals(events, that.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), prefix, suffix, events);
  }
}

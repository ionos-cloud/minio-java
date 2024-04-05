

package com.ionoscloud.s3;

import com.ionoscloud.s3.http.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Argument class of {@link ApiAsyncClient#getPresignedObjectUrl} and {@link
 * ApiClient#getPresignedObjectUrl}.
 */
public class GetPresignedObjectUrlArgs extends ObjectVersionArgs {
  // default expiration for a presigned URL is 7 days in seconds
  public static final int DEFAULT_EXPIRY_TIME = (int) TimeUnit.DAYS.toSeconds(7);

  private Method method;
  private int expiry = DEFAULT_EXPIRY_TIME;

  public Method method() {
    return method;
  }

  public int expiry() {
    return expiry;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetPresignedObjectUrlArgs}. */
  public static final class Builder
      extends ObjectVersionArgs.Builder<Builder, GetPresignedObjectUrlArgs> {
    private void validateMethod(Method method) {
      validateNotNull(method, "method");
    }

    private void validateExpiry(int expiry) {
      if (expiry < 1 || expiry > DEFAULT_EXPIRY_TIME) {
        throw new IllegalArgumentException(
            "expiry must be minimum 1 second to maximum "
                + TimeUnit.SECONDS.toDays(DEFAULT_EXPIRY_TIME)
                + " days");
      }
    }

    /* method HTTP {@link Method} to generate presigned URL. */
    public Builder method(Method method) {
      validateMethod(method);
      operations.add(args -> args.method = method);
      return this;
    }

    /*expires Expiry in seconds; defaults to 7 days. */
    public Builder expiry(int expiry) {
      validateExpiry(expiry);
      operations.add(args -> args.expiry = expiry);
      return this;
    }

    public Builder expiry(int duration, TimeUnit unit) {
      return expiry((int) unit.toSeconds(duration));
    }

    @Override
    protected void validate(GetPresignedObjectUrlArgs args) {
      super.validate(args);
      validateMethod(args.method);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GetPresignedObjectUrlArgs)) return false;
    if (!super.equals(o)) return false;
    GetPresignedObjectUrlArgs that = (GetPresignedObjectUrlArgs) o;
    return expiry == that.expiry && method == that.method;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), method, expiry);
  }
}

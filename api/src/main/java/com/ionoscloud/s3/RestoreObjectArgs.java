package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.RestoreRequest;
import java.util.Objects;

/** Argument class of {@link ApiClient#restoreObject}. */
public class RestoreObjectArgs extends ObjectVersionArgs {
  private RestoreRequest request;

  public RestoreRequest request() {
    return request;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link RestoreObjectArgs}. */
  public static final class Builder extends ObjectVersionArgs.Builder<Builder, RestoreObjectArgs> {
    private void validateRequest(RestoreRequest request) {
      validateNotNull(request, "request");
    }

    public Builder request(RestoreRequest request) {
      validateRequest(request);
      operations.add(args -> args.request = request);
      return this;
    }

    @Override
    protected void validate(RestoreObjectArgs args) {
      super.validate(args);
      validateRequest(args.request());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RestoreObjectArgs)) return false;
    if (!super.equals(o)) return false;
    RestoreObjectArgs that = (RestoreObjectArgs) o;
    return Objects.equals(request, that.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), request);
  }
}

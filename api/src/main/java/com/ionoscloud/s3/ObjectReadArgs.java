package com.ionoscloud.s3;

import java.util.Objects;
import okhttp3.HttpUrl;

/** Base argument class for reading object. */
public abstract class ObjectReadArgs extends ObjectVersionArgs {
  protected ServerSideEncryptionCustomerKey ssec;

  public ServerSideEncryptionCustomerKey ssec() {
    return ssec;
  }

  protected void validateSsec(HttpUrl url) {
    checkSse(ssec, url);
  }

  /** Base argument builder class for {@link ObjectReadArgs}. */
  public abstract static class Builder<B extends Builder<B, A>, A extends ObjectReadArgs>
      extends ObjectVersionArgs.Builder<B, A> {
    @SuppressWarnings("unchecked") // Its safe to type cast to B as B is inherited by this class
    public B ssec(ServerSideEncryptionCustomerKey ssec) {
      operations.add(args -> args.ssec = ssec);
      return (B) this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ObjectReadArgs)) return false;
    if (!super.equals(o)) return false;
    ObjectReadArgs that = (ObjectReadArgs) o;
    return Objects.equals(ssec, that.ssec);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), ssec);
  }
}

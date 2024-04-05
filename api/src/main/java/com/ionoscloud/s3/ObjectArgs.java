

package com.ionoscloud.s3;

import java.util.Objects;
import okhttp3.HttpUrl;

/** Base argument class holds object name and version ID along with bucket information. */
public abstract class ObjectArgs extends BucketArgs {
  protected String objectName;

  public String object() {
    return objectName;
  }

  protected void checkSse(ServerSideEncryption sse, HttpUrl url) {
    if (sse == null) {
      return;
    }

    if (sse.tlsRequired() && !url.isHttps()) {
      throw new IllegalArgumentException(
          sse + " operations must be performed over a secure connection.");
    }
  }

  /** Base argument builder class for {@link ObjectArgs}. */
  public abstract static class Builder<B extends Builder<B, A>, A extends ObjectArgs>
      extends BucketArgs.Builder<B, A> {
    protected void validateObjectName(String name) {
      validateNotEmptyString(name, "object name");
      for (String token : name.split("/")) {
        if (token.equals(".") || token.equals("..")) {
          throw new IllegalArgumentException(
              "object name with '.' or '..' path segment is not supported");
        }
      }
    }

    protected void validate(A args) {
      super.validate(args);
      validateObjectName(args.objectName);
    }

    @SuppressWarnings("unchecked") // Its safe to type cast to B as B is inherited by this class
    public B object(String name) {
      validateObjectName(name);
      operations.add(args -> args.objectName = name);
      return (B) this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ObjectArgs)) return false;
    if (!super.equals(o)) return false;
    ObjectArgs that = (ObjectArgs) o;
    return Objects.equals(objectName, that.objectName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), objectName);
  }
}

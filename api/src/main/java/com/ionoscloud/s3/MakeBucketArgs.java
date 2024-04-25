package com.ionoscloud.s3;

import java.util.Objects;

/** Argument class of {@link ApiAsyncClient#makeBucket} and {@link ApiClient#makeBucket}. */
public class MakeBucketArgs extends BucketArgs {
  private boolean objectLock;

  public boolean objectLock() {
    return objectLock;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link MakeBucketArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, MakeBucketArgs> {
    public Builder objectLock(boolean objectLock) {
      operations.add(args -> args.objectLock = objectLock);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MakeBucketArgs)) return false;
    if (!super.equals(o)) return false;
    MakeBucketArgs that = (MakeBucketArgs) o;
    return objectLock == that.objectLock;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), objectLock);
  }
}

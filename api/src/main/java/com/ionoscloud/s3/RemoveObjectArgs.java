package com.ionoscloud.s3;

import java.util.Objects;

/** Argument class of {@link ApiAsyncClient#removeObject} and {@link ApiClient#removeObject}. */
public class RemoveObjectArgs extends ObjectVersionArgs {
  private boolean bypassGovernanceMode;

  public boolean bypassGovernanceMode() {
    return bypassGovernanceMode;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link RemoveObjectArgs}. */
  public static final class Builder extends ObjectVersionArgs.Builder<Builder, RemoveObjectArgs> {
    public Builder bypassGovernanceMode(boolean flag) {
      operations.add(args -> args.bypassGovernanceMode = flag);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RemoveObjectArgs)) return false;
    if (!super.equals(o)) return false;
    RemoveObjectArgs that = (RemoveObjectArgs) o;
    return bypassGovernanceMode == that.bypassGovernanceMode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), bypassGovernanceMode);
  }
}

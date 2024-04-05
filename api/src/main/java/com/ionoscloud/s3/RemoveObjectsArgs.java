

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.DeleteObject;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Argument class of {@link ApiAsyncClient#removeObjects} and {@link ApiClient#removeObjects}.
 */
public class RemoveObjectsArgs extends BucketArgs {
  private boolean bypassGovernanceMode;
  private Iterable<DeleteObject> objects = new LinkedList<>();

  public boolean bypassGovernanceMode() {
    return bypassGovernanceMode;
  }

  public Iterable<DeleteObject> objects() {
    return objects;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link RemoveObjectsArgs}. */
  public static final class Builder extends BucketArgs.Builder<Builder, RemoveObjectsArgs> {
    public Builder bypassGovernanceMode(boolean flag) {
      operations.add(args -> args.bypassGovernanceMode = flag);
      return this;
    }

    public Builder objects(Iterable<DeleteObject> objects) {
      validateNotNull(objects, "objects");
      operations.add(args -> args.objects = objects);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RemoveObjectsArgs)) return false;
    if (!super.equals(o)) return false;
    RemoveObjectsArgs that = (RemoveObjectsArgs) o;
    return bypassGovernanceMode == that.bypassGovernanceMode
        && Objects.equals(objects, that.objects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), bypassGovernanceMode, objects);
  }
}

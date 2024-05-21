package com.ionoscloud.s3;

import java.util.Objects;

/** Argument class of {@link ApiAsyncClient#downloadObject} and {@link ApiClient#downloadObject}. */
public class DownloadObjectArgs extends ObjectReadArgs {
  private String filename;
  private boolean overwrite;

  public String filename() {
    return filename;
  }

  public boolean overwrite() {
    return overwrite;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument class of {@link DownloadObjectArgs}. */
  public static final class Builder extends ObjectReadArgs.Builder<Builder, DownloadObjectArgs> {
    private void validateFilename(String filename) {
      validateNotEmptyString(filename, "filename");
    }

    public Builder filename(String filename) {
      validateFilename(filename);
      operations.add(args -> args.filename = filename);
      return this;
    }

    public Builder overwrite(boolean flag) {
      operations.add(args -> args.overwrite = flag);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DownloadObjectArgs)) return false;
    if (!super.equals(o)) return false;
    DownloadObjectArgs that = (DownloadObjectArgs) o;
    if (!Objects.equals(filename, that.filename)) return false;
    return overwrite == that.overwrite;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), filename, overwrite);
  }
}

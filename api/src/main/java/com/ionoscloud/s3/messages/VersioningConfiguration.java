

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutBucketVersioning.html">PutBucketVersioning
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetBucketVersioning.html">GetBucketVersioning
 * API</a>.
 */
@Root(name = "VersioningConfiguration", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class VersioningConfiguration {
  @Element(name = "Status", required = false)
  private String status;

  @Element(name = "MfaDelete", required = false)
  private String mfaDelete;

  public VersioningConfiguration() {}

  /** Constructs a new VersioningConfiguration object with given status. */
  public VersioningConfiguration(@Nonnull Status status, @Nullable Boolean mfaDelete) {
    Objects.requireNonNull(status, "Status must not be null");
    if (status == Status.OFF) {
      throw new IllegalArgumentException("Status must be ENABLED or SUSPENDED");
    }
    this.status = status.toString();

    if (mfaDelete != null) {
      this.mfaDelete = mfaDelete ? "Enabled" : "Disabled";
    }
  }

  public Status status() {
    return Status.fromString(status);
  }

  public Boolean isMfaDeleteEnabled() {
    Boolean flag = (mfaDelete != null) ? Boolean.valueOf("Enabled".equals(mfaDelete)) : null;
    return flag;
  }

  public static enum Status {
    OFF(""),
    ENABLED("Enabled"),
    SUSPENDED("Suspended");

    private final String value;

    private Status(String value) {
      this.value = value;
    }

    public String toString() {
      return this.value;
    }

    public static Status fromString(String statusString) {
      if ("Enabled".equals(statusString)) {
        return ENABLED;
      }

      if ("Suspended".equals(statusString)) {
        return SUSPENDED;
      }

      return OFF;
    }
  }
}

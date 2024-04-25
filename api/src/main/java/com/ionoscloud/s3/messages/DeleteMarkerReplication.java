

package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote delete marker replication information for {@link ReplicationRule}. */
@Root(name = "DeleteMarkerReplication")
public class DeleteMarkerReplication {
  @Element(name = "Status", required = false)
  private Status status;

  /** Constructs new server-side encryption configuration rule. */
  public DeleteMarkerReplication(
      @Nullable @Element(name = "Status", required = false) Status status) {
    this.status = (status == null) ? Status.DISABLED : status;
  }

  public Status status() {
    return status;
  }
}

package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote delete replication information for {@link ReplicationRule}. */
@Root(name = "DeleteReplication")
public class DeleteReplication {
  @Element(name = "Status", required = false)
  private Status status;

  public DeleteReplication(@Nullable @Element(name = "Status", required = false) Status status) {
    this.status = (status == null) ? Status.DISABLED : status;
  }

  public Status status() {
    return status;
  }
}

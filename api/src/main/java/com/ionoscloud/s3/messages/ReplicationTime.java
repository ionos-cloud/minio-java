

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote replication time information for {@link ReplicationDestination}. */
@Root(name = "ReplicationTime")
public class ReplicationTime {
  @Element(name = "Time")
  private ReplicationTimeValue time;

  @Element(name = "Status")
  private Status status;

  public ReplicationTime(
      @Nonnull @Element(name = "Time") ReplicationTimeValue time,
      @Nonnull @Element(name = "Status") Status status) {
    this.time = Objects.requireNonNull(time, "Time must not be null");
    this.status = Objects.requireNonNull(status, "Status must not be null");
  }

  public ReplicationTimeValue time() {
    return this.time;
  }

  public Status status() {
    return this.status;
  }
}

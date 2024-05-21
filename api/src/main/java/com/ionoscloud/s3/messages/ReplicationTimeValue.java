package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote replication time value information for {@link Metrics}. */
@Root(name = "ReplicationTimeValue")
public class ReplicationTimeValue {
  @Element(name = "Minutes", required = false)
  private Integer minutes = 15;

  public ReplicationTimeValue(
      @Nullable @Element(name = "Minutes", required = false) Integer minutes) {
    this.minutes = minutes;
  }

  public Integer minutes() {
    return this.minutes;
  }
}

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote metrics information for {@link ReplicationDestination}. */
@Root(name = "Metrics")
public class Metrics {
  @Element(name = "EventThreshold")
  private ReplicationTimeValue eventThreshold;

  @Element(name = "Status")
  private Status status;

  public Metrics(
      @Nonnull @Element(name = "EventThreshold") ReplicationTimeValue eventThreshold,
      @Nonnull @Element(name = "Status") Status status) {
    this.eventThreshold =
        Objects.requireNonNull(eventThreshold, "Event threshold must not be null");
    this.status = Objects.requireNonNull(status, "Status must not be null");
  }

  public ReplicationTimeValue eventThreshold() {
    return this.eventThreshold;
  }

  public Status status() {
    return this.status;
  }
}

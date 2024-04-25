package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote replica modifications information for {@link SourceSelectionCriteria}. */
@Root(name = "ReplicaModifications")
public class ReplicaModifications {
  @Element(name = "Status")
  private Status status;

  public ReplicaModifications(@Nonnull @Element(name = "Status") Status status) {
    this.status = Objects.requireNonNull(status, "Status must not be null");
  }

  public Status status() {
    return this.status;
  }
}

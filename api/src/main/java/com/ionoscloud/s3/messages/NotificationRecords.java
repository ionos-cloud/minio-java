package com.ionoscloud.s3.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Object representation of JSON format of <a
 * href="http://docs.aws.amazon.com/AmazonS3/latest/dev/notification-content-structure.html">Event
 * Message Structure</a>.
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
    value = "UwF",
    justification = "Everything in this class is initialized by JSON unmarshalling.")
public class NotificationRecords {
  @JsonProperty("Records")
  private List<Event> events;

  public List<Event> events() {
    return Collections.unmodifiableList(events == null ? new LinkedList<>() : events);
  }
}

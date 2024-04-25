package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Helper class to denote common fields of {@link CloudFunctionConfiguration}, {@link
 * QueueConfiguration} and {@link TopicConfiguration}.
 */
public abstract class NotificationCommonConfiguration {
  @Element(name = "Id", required = false)
  private String id;

  @ElementList(name = "Event", inline = true)
  private List<EventType> events;

  @Element(name = "Filter", required = false)
  private Filter filter;

  public NotificationCommonConfiguration() {}

  /** Returns id. */
  public String id() {
    return id;
  }

  /** Sets id. */
  public void setId(String id) {
    this.id = id;
  }

  /** Returns events. */
  public List<EventType> events() {
    return Collections.unmodifiableList(events == null ? new LinkedList<>() : events);
  }

  /** Sets event. */
  public void setEvents(List<EventType> events) {
    this.events = Collections.unmodifiableList(events);
  }

  /** sets filter prefix rule. */
  public void setPrefixRule(String value) throws IllegalArgumentException {
    if (filter == null) {
      filter = new Filter();
    }

    filter.setPrefixRule(value);
  }

  /** sets filter suffix rule. */
  public void setSuffixRule(String value) throws IllegalArgumentException {
    if (filter == null) {
      filter = new Filter();
    }

    filter.setSuffixRule(value);
  }

  /** returns filter rule list. */
  public List<FilterRule> filterRuleList() {
    return Collections.unmodifiableList(
        filter == null ? new LinkedList<>() : filter.filterRuleList());
  }
}

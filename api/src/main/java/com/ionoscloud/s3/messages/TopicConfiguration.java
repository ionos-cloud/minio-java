

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote Topic configuration of {@link NotificationConfiguration}. */
@Root(name = "TopicConfiguration", strict = false)
public class TopicConfiguration extends NotificationCommonConfiguration {
  @Element(name = "Topic")
  private String topic;

  public TopicConfiguration() {
    super();
  }

  /** Returns topic. */
  public String topic() {
    return topic;
  }

  /** Sets topic. */
  public void setTopic(String topic) {
    this.topic = topic;
  }
}

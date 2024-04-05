

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote FilterRule configuration of {@link CloudFunctionConfiguration}, {@link
 * QueueConfiguration} or {@link TopicConfiguration}.
 */
@Root(name = "FilterRule", strict = false)
public class FilterRule {
  @Element(name = "Name")
  private String name;

  @Element(name = "Value")
  private String value;

  public FilterRule() {}

  public FilterRule(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /** Returns filter name. */
  public String name() {
    return name;
  }

  /** Returns filter value. */
  public String value() {
    return value;
  }
}

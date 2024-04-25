package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

/** Helper class to denote AND operator information for {@link RuleFilter}. */
@Root(name = "And")
public class AndOperator {
  @Element(name = "Prefix", required = false)
  @Convert(PrefixConverter.class)
  private String prefix;

  @ElementMap(
      attribute = false,
      entry = "Tag",
      inline = true,
      key = "Key",
      value = "Value",
      required = false)
  private Map<String, String> tags;

  public AndOperator(
      @Nullable @Element(name = "Prefix", required = false) String prefix,
      @Nullable
          @ElementMap(
              attribute = false,
              entry = "Tag",
              inline = true,
              key = "Key",
              value = "Value",
              required = false)
          Map<String, String> tags) {
    if (prefix == null && tags == null) {
      throw new IllegalArgumentException("At least Prefix or Tags must be set");
    }

    if (tags != null) {
      for (String key : tags.keySet()) {
        if (key.isEmpty()) {
          throw new IllegalArgumentException("Tags must not contain empty key");
        }
      }
    }

    this.prefix = prefix;
    this.tags = (tags != null) ? Collections.unmodifiableMap(tags) : null;
  }

  public String prefix() {
    return this.prefix;
  }

  public Map<String, String> tags() {
    return this.tags;
  }
}

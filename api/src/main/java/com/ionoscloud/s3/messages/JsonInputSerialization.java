

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote JSON input serialization request XML as per {@link
 * SelectObjectContentRequest}.
 */
@Root(name = "JSON")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class JsonInputSerialization {
  @Element(name = "Type", required = false)
  private JsonType type;

  /** Constructs a new JsonInputSerialization object. */
  public JsonInputSerialization(JsonType type) {
    this.type = type;
  }
}

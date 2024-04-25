

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote JSON output serialization request XML as per {@link
 * SelectObjectContentRequest}.
 */
@Root(name = "JSON")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class JsonOutputSerialization {
  @Element(name = "RecordDelimiter", required = false)
  private Character recordDelimiter;

  /** Constructs a new JsonOutputSerialization object. */
  public JsonOutputSerialization(Character recordDelimiter) {
    this.recordDelimiter = recordDelimiter;
  }
}

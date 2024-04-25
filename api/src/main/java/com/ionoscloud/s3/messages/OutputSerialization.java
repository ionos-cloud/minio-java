package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote Output Serialization information of {@link SelectObjectContentRequest}.
 */
@Root(name = "OutputSerialization")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class OutputSerialization {
  @Element(name = "CSV", required = false)
  private CsvOutputSerialization csv;

  @Element(name = "JSON", required = false)
  private JsonOutputSerialization json;

  /** Constructs a new OutputSerialization object with CSV. */
  public OutputSerialization(
      Character fieldDelimiter,
      Character quoteCharacter,
      Character quoteEscapeCharacter,
      QuoteFields quoteFields,
      Character recordDelimiter) {
    this.csv =
        new CsvOutputSerialization(
            fieldDelimiter, quoteCharacter, quoteEscapeCharacter, quoteFields, recordDelimiter);
  }

  /** Constructs a new OutputSerialization object with JSON. */
  public OutputSerialization(Character recordDelimiter) {
    this.json = new JsonOutputSerialization(recordDelimiter);
  }
}

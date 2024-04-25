package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote CSV output serialization request XML as per {@link
 * SelectObjectContentRequest}.
 */
@Root(name = "CSV")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class CsvOutputSerialization {
  @Element(name = "FieldDelimiter", required = false)
  private Character fieldDelimiter;

  @Element(name = "QuoteCharacter", required = false)
  private Character quoteCharacter;

  @Element(name = "QuoteEscapeCharacter", required = false)
  private Character quoteEscapeCharacter;

  @Element(name = "QuoteFields", required = false)
  private QuoteFields quoteFields;

  @Element(name = "RecordDelimiter", required = false)
  private Character recordDelimiter;

  /** Constructs a new CsvOutputSerialization object. */
  public CsvOutputSerialization(
      Character fieldDelimiter,
      Character quoteCharacter,
      Character quoteEscapeCharacter,
      QuoteFields quoteFields,
      Character recordDelimiter) {
    this.fieldDelimiter = fieldDelimiter;
    this.quoteCharacter = quoteCharacter;
    this.quoteEscapeCharacter = quoteEscapeCharacter;
    this.quoteFields = quoteFields;
    this.recordDelimiter = recordDelimiter;
  }
}

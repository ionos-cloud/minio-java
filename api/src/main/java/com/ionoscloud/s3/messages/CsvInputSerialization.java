

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote CSV input serialization request XML as per {@link
 * SelectObjectContentRequest}.
 */
@Root(name = "CSV")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class CsvInputSerialization {
  @Element(name = "AllowQuotedRecordDelimiter", required = false)
  private boolean allowQuotedRecordDelimiter;

  @Element(name = "Comments", required = false)
  private Character comments;

  @Element(name = "FieldDelimiter", required = false)
  private Character fieldDelimiter;

  @Element(name = "FileHeaderInfo", required = false)
  private FileHeaderInfo fileHeaderInfo;

  @Element(name = "QuoteCharacter", required = false)
  private Character quoteCharacter;

  @Element(name = "QuoteEscapeCharacter", required = false)
  private Character quoteEscapeCharacter;

  @Element(name = "RecordDelimiter", required = false)
  private Character recordDelimiter;

  /** Constructs a new CsvInputSerialization object. */
  public CsvInputSerialization(
      boolean allowQuotedRecordDelimiter,
      Character comments,
      Character fieldDelimiter,
      FileHeaderInfo fileHeaderInfo,
      Character quoteCharacter,
      Character quoteEscapeCharacter,
      Character recordDelimiter) {
    this.allowQuotedRecordDelimiter = allowQuotedRecordDelimiter;
    this.comments = comments;
    this.fieldDelimiter = fieldDelimiter;
    this.fileHeaderInfo = fileHeaderInfo;
    this.quoteCharacter = quoteCharacter;
    this.quoteEscapeCharacter = quoteEscapeCharacter;
    this.recordDelimiter = recordDelimiter;
  }
}

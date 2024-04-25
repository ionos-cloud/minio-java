

package com.ionoscloud.s3.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObjectRetention.html">PutObjectRetention
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetObjectRetention.html">GetObjectRetention
 * API</a>.
 */
@Root(name = "Retention", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class Retention {
  @Element(name = "Mode", required = false)
  private RetentionMode mode;

  @Element(name = "RetainUntilDate", required = false)
  private ResponseDate retainUntilDate;

  public Retention() {}

  /** Constructs a new Retention object with given retention until date and mode. */
  public Retention(RetentionMode mode, ZonedDateTime retainUntilDate) {
    if (mode == null) {
      throw new IllegalArgumentException("null mode is not allowed");
    }

    if (retainUntilDate == null) {
      throw new IllegalArgumentException("null retainUntilDate is not allowed");
    }

    this.mode = mode;
    this.retainUntilDate = new ResponseDate(retainUntilDate);
  }

  /** Returns mode. */
  public RetentionMode mode() {
    return this.mode;
  }

  /** Returns retain until date. */
  public ZonedDateTime retainUntilDate() {
    if (retainUntilDate != null) {
      return retainUntilDate.zonedDateTime();
    }

    return null;
  }
}

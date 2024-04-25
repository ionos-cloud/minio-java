

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObjectLegalHold.html">PutObjectLegalHold
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetObjectLegalHold.html">GetObjectLegalHold
 * API</a>.
 */
@Root(name = "LegalHold", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class LegalHold {
  @Element(name = "Status", required = false)
  private String status;

  public LegalHold() {}

  /** Constructs a new LegalHold object with given status. */
  public LegalHold(boolean status) {
    if (status) {
      this.status = "ON";
    } else {
      this.status = "OFF";
    }
  }

  /** Indicates whether the specified object has a Legal Hold in place or not. */
  public boolean status() {
    return status != null && status.equals("ON");
  }
}

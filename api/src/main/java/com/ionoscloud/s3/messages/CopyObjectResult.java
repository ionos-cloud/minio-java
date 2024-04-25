

package com.ionoscloud.s3.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_CopyObject.html">CopyObject API</a>.
 */
@Root(name = "CopyObjectResult", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class CopyObjectResult {
  @Element(name = "ETag")
  private String etag;

  @Element(name = "LastModified")
  private ResponseDate lastModified;

  public CopyObjectResult() {}

  /** Returns ETag of the object. */
  public String etag() {
    return etag;
  }

  /** Returns last modified time. */
  public ZonedDateTime lastModified() {
    return lastModified.zonedDateTime();
  }
}

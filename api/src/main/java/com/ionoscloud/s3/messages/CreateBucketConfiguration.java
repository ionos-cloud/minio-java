

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateBucket.html">CreateBucket
 * API</a>.
 */
@Root(name = "CreateBucketConfiguration")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class CreateBucketConfiguration {
  @Element(name = "LocationConstraint")
  private String locationConstraint;

  /** Constructs a new CreateBucketConfiguration object with given location constraint. */
  public CreateBucketConfiguration(String locationConstraint) {
    this.locationConstraint = locationConstraint;
  }
}

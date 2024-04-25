package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetBucketLocation.html">GetBucketLocation
 * API</a>.
 */
@Root(name = "LocationConstraint", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class LocationConstraint {
  @Text(required = false)
  private String location = "";

  public LocationConstraint() {}

  public String location() {
    return location;
  }
}

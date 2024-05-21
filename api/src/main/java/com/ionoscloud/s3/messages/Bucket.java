package com.ionoscloud.s3.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote bucket information for {@link ListAllMyBucketsResult}. */
@Root(name = "Bucket", strict = false)
public class Bucket {
  @Element(name = "Name")
  private String name;

  @Element(name = "CreationDate")
  private ResponseDate creationDate;

  public Bucket() {}

  /** Returns bucket name. */
  public String name() {
    return name;
  }

  /** Returns creation date. */
  public ZonedDateTime creationDate() {
    return creationDate.zonedDateTime();
  }
}

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;

/** Helper class to denote delete marker information in {@link ListVersionsResult}. */
@Root(name = "DeleteMarker", strict = false)
public class DeleteMarker extends Item {
  public DeleteMarker() {
    super();
  }

  public DeleteMarker(String prefix) {
    super(prefix);
  }
}

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;

/** Helper class to denote object and it's version information in {@link ListVersionsResult}. */
@Root(name = "Version", strict = false)
public class Version extends Item {
  public Version() {
    super();
  }

  public Version(String prefix) {
    super(prefix);
  }
}

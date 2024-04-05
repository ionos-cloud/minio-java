

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote Prefix information in {@link ListBucketResultV1}, {@link
 * ListBucketResultV2} and {@link ListVersionsResult}.
 */
@Root(name = "CommonPrefixes", strict = false)
public class Prefix {
  @Element(name = "Prefix")
  private String prefix;

  public Prefix() {}

  public Item toItem() {
    return new Contents(prefix);
  }
}

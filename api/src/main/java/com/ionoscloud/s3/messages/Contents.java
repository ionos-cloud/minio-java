

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;

/**
 * Helper class to denote Object information in {@link ListBucketResultV1} and {@link
 * ListBucketResultV2}
 */
@Root(name = "Contents", strict = false)
public class Contents extends Item {
  public Contents() {
    super();
  }

  public Contents(String prefix) {
    super(prefix);
  }
}

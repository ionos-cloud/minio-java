

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote owner information for {@link ListAllMyBucketsResult}, {@link
 * ListBucketResultV1}, {@link ListBucketResultV2}, {@link ListVersionsResult}, {@link
 * ListMultipartUploadsResult} and {@link ListPartsResult}.
 */
@Root(name = "Owner", strict = false)
public class Owner {
  @Element(name = "ID", required = false)
  private String id;

  @Element(name = "DisplayName", required = false)
  private String displayName;

  public Owner() {}

  /** Returns owner ID. */
  public String id() {
    return id;
  }

  /** Returns owner display name. */
  public String displayName() {
    return displayName;
  }
}

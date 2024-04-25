

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote Initator information of a multipart upload and used in {@link
 * ListMultipartUploadsResult} and {@link ListPartsResult}.
 */
@Root(name = "Initiator", strict = false)
public class Initiator {
  @Element(name = "ID", required = false)
  private String id;

  @Element(name = "DisplayName", required = false)
  private String displayName;

  public Initiator() {}

  /** Returns initiator ID. */
  public String id() {
    return id;
  }

  /** Returns initiator display name. */
  public String displayName() {
    return displayName;
  }
}

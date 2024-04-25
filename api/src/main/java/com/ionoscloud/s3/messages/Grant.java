package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote grant information of {@link AccessControlList}. */
@Root(name = "Grant")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class Grant {
  @Element(name = "Grantee", required = false)
  private Grantee grantee;

  @Element(name = "Permission", required = false)
  private Permission permission;

  public Grant(@Nullable Grantee grantee, @Nullable Permission permission) {
    if (grantee == null && permission == null) {
      throw new IllegalArgumentException("Either Grantee or Permission must be provided");
    }
    this.grantee = grantee;
    this.permission = permission;
  }
}

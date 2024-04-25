package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote Object information for {@link DeleteRequest}. */
@Root(name = "Object")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class DeleteObject {
  @Element(name = "Key")
  private String name;

  @Element(name = "VersionId", required = false)
  private String versionId;

  public DeleteObject(String name) {
    this.name = name;
  }

  public DeleteObject(String name, String versionId) {
    this.name = name;
    this.versionId = versionId;
  }
}

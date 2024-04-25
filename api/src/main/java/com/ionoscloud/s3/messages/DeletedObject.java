

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote deleted object for {@link DeleteResult}. */
@Root(name = "Deleted", strict = false)
public class DeletedObject {
  @Element(name = "Key")
  private String name;

  @Element(name = "VersionId", required = false)
  private String versionId;

  @Element(name = "DeleteMarker", required = false)
  private boolean deleteMarker;

  @Element(name = "DeleteMarkerVersionId", required = false)
  private String deleteMarkerVersionId;

  public DeletedObject() {}

  public String name() {
    return name;
  }

  public String versionId() {
    return versionId;
  }

  public boolean deleteMarker() {
    return deleteMarker;
  }

  public String deleteMarkerVersionId() {
    return deleteMarkerVersionId;
  }
}

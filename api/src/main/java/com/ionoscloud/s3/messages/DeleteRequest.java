

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_DeleteObjects.html">DeleteObjects
 * API</a>.
 */
@Root(name = "Delete")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class DeleteRequest {
  @Element(name = "Quiet", required = false)
  private boolean quiet;

  @ElementList(name = "Object", inline = true)
  private List<DeleteObject> objectList;

  /** Constructs new delete request for given object list and quiet flag. */
  public DeleteRequest(List<DeleteObject> objectList, boolean quiet) {
    this.objectList = Collections.unmodifiableList(objectList);
    this.quiet = quiet;
  }
}

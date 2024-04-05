

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_DeleteObjects.html">DeleteObjects
 * API</a>.
 */
@Root(name = "DeleteResult", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class DeleteResult {
  @ElementList(name = "Deleted", inline = true, required = false)
  private List<DeletedObject> objectList;

  @ElementList(name = "Error", inline = true, required = false)
  private List<DeleteError> errorList;

  public DeleteResult() {}

  /** Constructs new delete result with an error. */
  public DeleteResult(DeleteError error) {
    this.errorList = new LinkedList<DeleteError>();
    this.errorList.add(error);
  }

  /** Returns deleted object list. */
  public List<DeletedObject> objectList() {
    if (objectList == null) {
      return Collections.unmodifiableList(new LinkedList<>());
    }

    return Collections.unmodifiableList(objectList);
  }

  /** Returns delete error list. */
  public List<DeleteError> errorList() {
    if (errorList == null) {
      return Collections.unmodifiableList(new LinkedList<>());
    }

    return Collections.unmodifiableList(errorList);
  }
}

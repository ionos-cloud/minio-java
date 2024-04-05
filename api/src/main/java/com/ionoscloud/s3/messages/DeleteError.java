

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/** Helper class to denote error for {@link DeleteResult}. */
@Root(name = "Error", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class DeleteError extends ErrorResponse {
  private static final long serialVersionUID = 1905162041950251407L; // fix SE_BAD_FIELD
}

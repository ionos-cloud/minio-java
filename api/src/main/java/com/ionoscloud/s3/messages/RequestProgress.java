

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote progress request in select object content request XML for {@link
 * SelectObjectContentRequest}.
 */
@Root(name = "RequestProgress", strict = false)
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class RequestProgress {
  @Element(name = "Enabled")
  private boolean enabled = true;

  /** Constructs a new RequestProgress object. */
  public RequestProgress() {}
}

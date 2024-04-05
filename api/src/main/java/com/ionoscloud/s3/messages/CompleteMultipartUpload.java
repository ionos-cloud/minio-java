

package com.ionoscloud.s3.messages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_CompleteMultipartUpload.html">CompleteMultipartUpload
 * API</a>.
 */
@Root(name = "CompleteMultipartUpload")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class CompleteMultipartUpload {
  @ElementList(name = "Part", inline = true)
  private List<Part> partList;

  /** Constucts a new CompleteMultipartUpload object with given parts. */
  public CompleteMultipartUpload(Part[] parts) throws IllegalArgumentException {
    if (parts == null || parts.length == 0) {
      throw new IllegalArgumentException("null or empty parts");
    }

    this.partList = Collections.unmodifiableList(Arrays.asList(parts));
  }
}

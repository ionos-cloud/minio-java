

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote abort incomplete multipart upload information for {@link LifecycleRule}.
 */
@Root(name = "AbortIncompleteMultipartUpload")
public class AbortIncompleteMultipartUpload {
  @Element(name = "DaysAfterInitiation")
  private int daysAfterInitiation;

  public AbortIncompleteMultipartUpload(
      @Element(name = "DaysAfterInitiation") int daysAfterInitiation) {
    this.daysAfterInitiation = daysAfterInitiation;
  }

  public int daysAfterInitiation() {
    return daysAfterInitiation;
  }
}

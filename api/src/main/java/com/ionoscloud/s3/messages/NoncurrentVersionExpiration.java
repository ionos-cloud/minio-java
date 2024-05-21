package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote noncurrent version expiration information for {@link LifecycleRule}. */
@Root(name = "NoncurrentVersionExpiration")
public class NoncurrentVersionExpiration {
  @Element(name = "NoncurrentDays")
  private int noncurrentDays;

  public NoncurrentVersionExpiration(
      @Element(name = "NoncurrentDays", required = false) int noncurrentDays) {
    this.noncurrentDays = noncurrentDays;
  }

  public int noncurrentDays() {
    return noncurrentDays;
  }
}

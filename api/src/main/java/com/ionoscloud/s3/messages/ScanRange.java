package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote scan range in select object content request XML for {@link
 * SelectObjectContentRequest}.
 */
@Root(name = "ScanRange")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class ScanRange {
  @Element(name = "Start", required = false)
  private Long start;

  @Element(name = "End", required = false)
  private Long end;

  /** Constructs new ScanRange object for given start and end. */
  public ScanRange(Long start, Long end) {
    this.start = start;
    this.end = end;
  }
}

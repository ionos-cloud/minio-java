package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/** Helper class to denote Stats information of S3 select response message. */
@Root(name = "Stats", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class Stats {
  @Element(name = "BytesScanned")
  private long bytesScanned = -1;

  @Element(name = "BytesProcessed")
  private long bytesProcessed = -1;

  @Element(name = "BytesReturned")
  private long bytesReturned = -1;

  /** Constructs a new Stats object. */
  public Stats() {}

  /** Returns bytes scanned. */
  public long bytesScanned() {
    return this.bytesScanned;
  }

  /** Returns bytes processed. */
  public long bytesProcessed() {
    return this.bytesProcessed;
  }

  /** Returns bytes returned. */
  public long bytesReturned() {
    return this.bytesReturned;
  }
}

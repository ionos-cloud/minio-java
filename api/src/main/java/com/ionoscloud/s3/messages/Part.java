package com.ionoscloud.s3.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote Part information of a multipart upload and used in {@link
 * CompleteMultipartUpload} and {@link ListPartsResult}.
 */
@Root(name = "Part", strict = false)
public class Part {
  @Element(name = "PartNumber")
  private int partNumber;

  @Element(name = "ETag")
  private String etag;

  @Element(name = "LastModified", required = false)
  private ResponseDate lastModified;

  @Element(name = "Size", required = false)
  private Long size;

  public Part() {}

  /** Constructs a new Part object with given part number and ETag. */
  public Part(int partNumber, String etag) {

    this.partNumber = partNumber;
    this.etag = etag;
  }

  /** Returns part number. */
  public int partNumber() {
    return partNumber;
  }

  /** Returns ETag. */
  public String etag() {
    return etag.replaceAll("\"", "");
  }

  /** Returns last modified time. */
  public ZonedDateTime lastModified() {
    return lastModified.zonedDateTime();
  }

  /** Returns part size. */
  public long partSize() {
    return size;
  }
}

package com.ionoscloud.s3.messages;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_ListObjectsV2.html">ListObjectsV2
 * API</a>.
 */
@Root(name = "ListBucketResult", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class ListBucketResultV2 extends ListObjectsResult {
  @Element(name = "KeyCount", required = false)
  private int keyCount;

  @Element(name = "StartAfter", required = false)
  private String startAfter;

  @Element(name = "ContinuationToken", required = false)
  private String continuationToken;

  @Element(name = "NextContinuationToken", required = false)
  private String nextContinuationToken;

  @ElementList(name = "Contents", inline = true, required = false)
  private List<Contents> contents;

  /** Returns key count. */
  public int keyCount() {
    return keyCount;
  }

  /** Returns start after. */
  public String startAfter() {
    return decodeIfNeeded(startAfter);
  }

  /** Returns continuation token. */
  public String continuationToken() {
    return continuationToken;
  }

  /** Returns next continuation token. */
  public String nextContinuationToken() {
    return nextContinuationToken;
  }

  /** Returns List of Items. */
  @Override
  public List<Contents> contents() {
    return emptyIfNull(contents);
  }
}

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_ListBuckets.html">ListBuckets API</a>.
 */
@Root(name = "ListAllMyBucketsResult", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class ListAllMyBucketsResult {
  @Element(name = "Owner")
  private Owner owner;

  @ElementList(name = "Buckets")
  private List<Bucket> buckets;

  public ListAllMyBucketsResult() {}

  /** Returns owner. */
  public Owner owner() {
    return owner;
  }

  /** Returns List of buckets. */
  public List<Bucket> buckets() {
    if (buckets == null) {
      return Collections.unmodifiableList(new LinkedList<>());
    }

    return Collections.unmodifiableList(buckets);
  }
}



package com.ionoscloud.s3.messages;

import com.google.common.base.MoreObjects;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/**
 * Base class of {@link ListBucketResultV1}, {@link ListBucketResultV2} and {@link
 * ListVersionsResult}.
 */
public abstract class ListObjectsResult {
  @Element(name = "Name")
  private String name;

  @Element(name = "EncodingType", required = false)
  private String encodingType;

  @Element(name = "Prefix", required = false)
  private String prefix;

  @Element(name = "Delimiter", required = false)
  private String delimiter;

  @Element(name = "IsTruncated", required = false)
  private boolean isTruncated;

  @Element(name = "MaxKeys", required = false)
  private int maxKeys;

  @ElementList(name = "CommonPrefixes", inline = true, required = false)
  private List<Prefix> commonPrefixes;

  private static final List<DeleteMarker> deleteMarkers =
      Collections.unmodifiableList(new LinkedList<>());

  public ListObjectsResult() {}

  protected String decodeIfNeeded(String value) {
    try {
      return (value != null && "url".equals(encodingType))
          ? URLDecoder.decode(value, StandardCharsets.UTF_8.name())
          : value;
    } catch (UnsupportedEncodingException e) {
      // This never happens as 'enc' name comes from JDK's own StandardCharsets.
      throw new RuntimeException(e);
    }
  }

  /** Returns bucket name. */
  public String name() {
    return name;
  }

  public String encodingType() {
    return encodingType;
  }

  /** Returns prefix. */
  public String prefix() {
    return decodeIfNeeded(prefix);
  }

  /** Returns delimiter. */
  public String delimiter() {
    return delimiter;
  }

  /** Returns whether the result is truncated or not. */
  public boolean isTruncated() {
    return isTruncated;
  }

  /** Returns max keys. */
  public int maxKeys() {
    return maxKeys;
  }

  /** Returns List of Prefix. */
  public List<Prefix> commonPrefixes() {
    return Collections.unmodifiableList(
        (commonPrefixes == null) ? new LinkedList<>() : commonPrefixes);
  }

  public List<DeleteMarker> deleteMarkers() {
    return deleteMarkers;
  }

  protected <T extends Item> List<T> emptyIfNull(List<T> lst) {
    return Collections.unmodifiableList(MoreObjects.firstNonNull(lst, new LinkedList<T>()));
  }

  public abstract List<? extends Item> contents();
}

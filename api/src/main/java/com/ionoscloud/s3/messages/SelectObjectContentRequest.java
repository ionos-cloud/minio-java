package com.ionoscloud.s3.messages;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_SelectObjectContent.html">SelectObjectContent
 * API</a>.
 */
@Root(name = "SelectObjectContentRequest")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class SelectObjectContentRequest extends SelectObjectContentRequestBase {
  @Element(name = "RequestProgress", required = false)
  private RequestProgress requestProgress;

  @Element(name = "ScanRange", required = false)
  private ScanRange scanRange;

  /** Constructs new SelectObjectContentRequest object for given parameters. */
  public SelectObjectContentRequest(
      @Nonnull String expression,
      boolean requestProgress,
      @Nonnull InputSerialization is,
      @Nonnull OutputSerialization os,
      @Nullable Long scanStartRange,
      @Nullable Long scanEndRange) {
    super(expression, is, os);
    if (requestProgress) {
      this.requestProgress = new RequestProgress();
    }
    if (scanStartRange != null || scanEndRange != null) {
      this.scanRange = new ScanRange(scanStartRange, scanEndRange);
    }
  }
}

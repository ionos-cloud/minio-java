

package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_RestoreObject.html">RestoreObject
 * API</a>.
 */
@Root(name = "RestoreRequest")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class RestoreRequest {
  @Element(name = "Days", required = false)
  private Integer days;

  @Element(name = "GlacierJobParameters", required = false)
  private GlacierJobParameters glacierJobParameters;

  @Element(name = "Type", required = false)
  private String type;

  @Element(name = "Tier", required = false)
  private Tier tier;

  @Element(name = "Description", required = false)
  private String description;

  @Element(name = "SelectParameters", required = false)
  private SelectParameters selectParameters;

  @Element(name = "OutputLocation", required = false)
  private OutputLocation outputLocation;

  /** Constructs new RestoreRequest object for given parameters. */
  public RestoreRequest(
      @Nullable Integer days,
      @Nullable GlacierJobParameters glacierJobParameters,
      @Nullable Tier tier,
      @Nullable String description,
      @Nullable SelectParameters selectParameters,
      @Nullable OutputLocation outputLocation) {
    this.days = days;
    this.glacierJobParameters = glacierJobParameters;
    if (selectParameters != null) this.type = "SELECT";
    this.tier = tier;
    this.description = description;
    this.selectParameters = selectParameters;
    this.outputLocation = outputLocation;
  }
}

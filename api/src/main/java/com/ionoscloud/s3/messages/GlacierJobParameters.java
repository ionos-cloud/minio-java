

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote S3 Glacier job parameters of {@link RestoreRequest}. */
@Root(name = "GlacierJobParameters")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class GlacierJobParameters {
  @Element(name = "Tier")
  private Tier tier;

  public GlacierJobParameters(@Nonnull Tier tier) {
    this.tier = Objects.requireNonNull(tier, "Tier must not be null");
  }
}

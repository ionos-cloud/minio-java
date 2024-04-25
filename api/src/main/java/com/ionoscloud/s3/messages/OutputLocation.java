

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote output location information of {@link RestoreRequest}. */
@Root(name = "OutputLocation")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class OutputLocation {
  @Element(name = "S3")
  private S3OutputLocation s3OutputLocation;

  public OutputLocation(@Nonnull S3OutputLocation s3OutputLocation) {
    this.s3OutputLocation =
        Objects.requireNonNull(s3OutputLocation, "S3OutputLocation must not be null");
  }
}

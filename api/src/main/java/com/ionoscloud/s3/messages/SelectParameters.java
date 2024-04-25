

package com.ionoscloud.s3.messages;

import javax.annotation.Nonnull;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote the parameters for Select job types information of {@link RestoreRequest}.
 */
@Root(name = "SelectParameters")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class SelectParameters extends SelectObjectContentRequestBase {
  public SelectParameters(
      @Nonnull String expression, @Nonnull InputSerialization is, @Nonnull OutputSerialization os) {
    super(expression, is, os);
  }
}

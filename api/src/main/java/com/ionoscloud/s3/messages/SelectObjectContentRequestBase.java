

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;

/** Base class for {@link SelectObjectContentRequest} and {@link SelectParameters}. */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public abstract class SelectObjectContentRequestBase {
  @Element(name = "Expression")
  private String expression;

  @Element(name = "ExpressionType")
  private String expressionType = "SQL";

  @Element(name = "InputSerialization")
  private InputSerialization inputSerialization;

  @Element(name = "OutputSerialization")
  private OutputSerialization outputSerialization;

  public SelectObjectContentRequestBase(
      @Nonnull String expression, @Nonnull InputSerialization is, @Nonnull OutputSerialization os) {
    this.expression = Objects.requireNonNull(expression, "Expression must not be null");
    this.inputSerialization = Objects.requireNonNull(is, "InputSerialization must not be null");
    this.outputSerialization = Objects.requireNonNull(os, "OutputSerialization must not be null");
  }
}

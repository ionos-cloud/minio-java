

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

/**
 * Helper class to denote filter information for {@link ReplicationRule} and {@link LifecycleRule}.
 */
@Root(name = "Filter")
public class RuleFilter {
  @Element(name = "And", required = false)
  private AndOperator andOperator;

  @Element(name = "Prefix", required = false)
  @Convert(PrefixConverter.class)
  private String prefix;

  @Element(name = "Tag", required = false)
  private Tag tag;

  public RuleFilter(
      @Nullable @Element(name = "And", required = false) AndOperator andOperator,
      @Nullable @Element(name = "Prefix", required = false) String prefix,
      @Nullable @Element(name = "Tag", required = false) Tag tag) {
    if (andOperator != null ^ prefix != null ^ tag != null) {
      this.andOperator = andOperator;
      this.prefix = prefix;
      this.tag = tag;
    } else {
      throw new IllegalArgumentException("Only one of And, Prefix or Tag must be set");
    }
  }

  public RuleFilter(@Nonnull AndOperator andOperator) {
    this.andOperator = Objects.requireNonNull(andOperator, "And operator must not be null");
  }

  public RuleFilter(@Nonnull String prefix) {
    this.prefix = Objects.requireNonNull(prefix, "Prefix must not be null");
  }

  public RuleFilter(@Nonnull Tag tag) {
    this.tag = Objects.requireNonNull(tag, "Tag must not be null");
  }

  public AndOperator andOperator() {
    return this.andOperator;
  }

  public String prefix() {
    return this.prefix;
  }

  public Tag tag() {
    return this.tag;
  }
}

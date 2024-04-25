package com.ionoscloud.s3.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/** Tier representing retrieval tier value. */
@Root(name = "Tier")
@Convert(Tier.TierConverter.class)
public enum Tier {
  STANDARD("Standard"),
  BULK("Bulk"),
  EXPEDITED("Expedited");

  private final String value;

  private Tier(String value) {
    this.value = value;
  }

  public String toString() {
    return this.value;
  }

  /** Returns Tier of given string. */
  @JsonCreator
  public static Tier fromString(String tierString) {
    for (Tier tier : Tier.values()) {
      if (tierString.equals(tier.value)) {
        return tier;
      }
    }

    throw new IllegalArgumentException("Unknown tier '" + tierString + "'");
  }

  /** XML converter class. */
  public static class TierConverter implements Converter<Tier> {
    @Override
    public Tier read(InputNode node) throws Exception {
      return Tier.fromString(node.getValue());
    }

    @Override
    public void write(OutputNode node, Tier tier) throws Exception {
      node.setValue(tier.toString());
    }
  }
}

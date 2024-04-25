

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/** GranteeType represents type of grantee. */
@Root(name = "Type")
@Convert(GranteeType.GranteeTypeConverter.class)
public enum GranteeType {
  CANONICAL_USER("CanonicalUser"),
  AMAZON_CUSTOMER_BY_EMAIL("AmazonCustomerByEmail"),
  GROUP("Group");

  private final String value;

  private GranteeType(String value) {
    this.value = value;
  }

  public String toString() {
    return this.value;
  }

  /** Returns GranteeType of given string. */
  public static GranteeType fromString(String granteeTypeString) {
    for (GranteeType granteeType : GranteeType.values()) {
      if (granteeTypeString.equals(granteeType.value)) {
        return granteeType;
      }
    }

    throw new IllegalArgumentException("Unknown grantee type '" + granteeTypeString + "'");
  }

  /** XML converter class. */
  public static class GranteeTypeConverter implements Converter<GranteeType> {
    @Override
    public GranteeType read(InputNode node) throws Exception {
      return GranteeType.fromString(node.getValue());
    }

    @Override
    public void write(OutputNode node, GranteeType granteeType) throws Exception {
      node.setValue(granteeType.toString());
    }
  }
}

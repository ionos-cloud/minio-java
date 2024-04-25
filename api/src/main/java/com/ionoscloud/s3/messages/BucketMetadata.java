

package com.ionoscloud.s3.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Helper class to denote bucket information for {@link EventMetadata}. */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
    value = "UwF",
    justification = "Everything in this class is initialized by JSON unmarshalling.")
public class BucketMetadata {
  @JsonProperty private String name;
  @JsonProperty private Identity ownerIdentity;
  @JsonProperty private String arn;

  public String name() {
    return name;
  }

  public String owner() {
    if (ownerIdentity == null) {
      return null;
    }

    return ownerIdentity.principalId();
  }

  public String arn() {
    return arn;
  }
}

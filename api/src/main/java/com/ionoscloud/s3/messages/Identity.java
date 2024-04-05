

package com.ionoscloud.s3.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Helper class to denote user or owner identity for {@link Event} and {@link BucketMetadata}. */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
    value = "UwF",
    justification = "Everything in this class is initialized by JSON unmarshalling.")
public class Identity {
  @JsonProperty private String principalId;

  public String principalId() {
    return principalId;
  }
}

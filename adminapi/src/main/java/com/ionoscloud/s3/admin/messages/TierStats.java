

package com.ionoscloud.s3.admin.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Per-tier stats of a remote tier.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TierStats {
  @JsonProperty("TotalSize")
  private long totalSize;

  @JsonProperty("NumVersions")
  private int numVersions;

  @JsonProperty("NumObjects")
  private int numObjects;

  public long totalSize() {
    return totalSize;
  }

  public int numVersions() {
    return numVersions;
  }

  public int numObjects() {
    return numObjects;
  }
}

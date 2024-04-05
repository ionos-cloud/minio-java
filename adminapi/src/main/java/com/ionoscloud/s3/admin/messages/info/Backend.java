

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ErasureBackend contains specific erasure storage information
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Backend {
  @JsonProperty("backendType")
  private String backendType;

  @JsonProperty("onlineDisks")
  private Integer onlineDisks;

  @JsonProperty("offlineDisks")
  private Integer offlineDisks;

  @JsonProperty("standardSCParity")
  private Integer standardSCParity;

  @JsonProperty("rrSCParity")
  private Integer rrSCParity;

  @JsonProperty("totalSets")
  private List<Integer> totalSets;

  @JsonProperty("totalDrivesPerSet")
  private List<Integer> totalDrivesPerSet;

  public String backendType() {
    return backendType;
  }

  public Integer onlineDisks() {
    return onlineDisks;
  }

  public Integer offlineDisks() {
    return offlineDisks;
  }

  public Integer standardSCParity() {
    return standardSCParity;
  }

  public Integer rrSCParity() {
    return rrSCParity;
  }

  public List<Integer> totalSets() {
    return Collections.unmodifiableList(totalSets == null ? new LinkedList<>() : totalSets);
  }

  public List<Integer> totalDrivesPerSet() {
    return Collections.unmodifiableList(
        totalDrivesPerSet == null ? new LinkedList<>() : totalDrivesPerSet);
  }
}

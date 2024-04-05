

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ServerProperties holds server information
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerProperties {
  @JsonProperty("state")
  private String state;

  @JsonProperty("endpoint")
  private String endpoint;

  @JsonProperty("scheme")
  private String scheme;

  @JsonProperty("uptime")
  private Integer uptime;

  @JsonProperty("version")
  private String version;

  @JsonProperty("commitID")
  private String commitID;

  @JsonProperty("network")
  private Map<String, String> network;

  @JsonProperty("drives")
  private List<Disk> disks;

  @JsonProperty("poolNumber")
  private Integer poolNumber;

  @JsonProperty("mem_stats")
  private MemStats memStats;

  @JsonProperty("go_max_procs")
  private Integer goMaxProcs;

  @JsonProperty("num_cpu")
  private Integer numCPU;

  @JsonProperty("runtime_version")
  private String runtimeVersion;

  @JsonProperty("gc_stats")
  private GCStats gCStats;

  @JsonProperty("ionos_env_vars")
  private Map<String, String> ionosEnvVars;

  public String state() {
    return state;
  }

  public String endpoint() {
    return endpoint;
  }

  public String scheme() {
    return scheme;
  }

  public Integer uptime() {
    return uptime;
  }

  public String version() {
    return version;
  }

  public String commitID() {
    return commitID;
  }

  public Map<String, String> network() {
    return Collections.unmodifiableMap(this.network);
  }

  public List<Disk> disks() {
    return Collections.unmodifiableList(disks == null ? new LinkedList<>() : disks);
  }

  public Integer poolNumber() {
    return poolNumber;
  }

  public MemStats memStats() {
    return memStats;
  }

  public Integer goMaxProcs() {
    return goMaxProcs;
  }

  public Integer numCPU() {
    return numCPU;
  }

  public String runtimeVersion() {
    return runtimeVersion;
  }

  public GCStats gCStats() {
    return gCStats;
  }

  public Map<String, String> ionosEnvVars() {
    return Collections.unmodifiableMap(this.ionosEnvVars);
  }
}

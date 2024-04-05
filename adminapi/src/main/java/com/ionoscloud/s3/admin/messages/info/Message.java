

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * InfoMessage container to hold server admin related information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
  @JsonProperty("mode")
  private String mode;

  @JsonProperty("deploymentID")
  private String deploymentID;

  @JsonProperty("buckets")
  private Buckets buckets;

  @JsonProperty("objects")
  private Objects objects;

  @JsonProperty("versions")
  private Versions versions;

  @JsonProperty("usage")
  private Usage usage;

  @JsonProperty("backend")
  private Backend backend;

  @JsonProperty("servers")
  private List<ServerProperties> servers;

  @JsonProperty("pools")
  private Map<Integer, Map<Integer, ErasureSetInfo>> pools;

  public String mode() {
    return mode;
  }

  public String deploymentID() {
    return deploymentID;
  }

  public Buckets buckets() {
    return buckets;
  }

  public Objects objects() {
    return objects;
  }

  public Versions versions() {
    return versions;
  }

  public Usage usage() {
    return usage;
  }

  public Backend backend() {
    return backend;
  }

  public List<ServerProperties> servers() {
    return Collections.unmodifiableList(servers == null ? new LinkedList<>() : servers);
  }

  public Map<Integer, Map<Integer, ErasureSetInfo>> pools() {
    return pools;
  }
}



package com.ionoscloud.s3.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** Represents group information. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupInfo {
  @JsonProperty("name")
  private String name;

  @JsonProperty("status")
  private Status status;

  @JsonProperty("members")
  private List<String> members;

  @JsonProperty("policy")
  private String policy;

  public String name() {
    return name;
  }

  public Status status() {
    return status;
  }

  public List<String> members() {
    return Collections.unmodifiableList(members == null ? new LinkedList<>() : members);
  }

  public String policy() {
    return policy;
  }
}

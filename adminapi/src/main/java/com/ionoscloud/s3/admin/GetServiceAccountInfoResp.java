

package com.ionoscloud.s3.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * service account info.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetServiceAccountInfoResp {
  @JsonProperty("parentUser")
  private String parentUser;

  @JsonProperty("accountStatus")
  private String accountStatus;

  @JsonProperty("impliedPolicy")
  private boolean impliedPolicy;

  @JsonProperty("policy")
  private String policy;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("expiration")
  private String expiration;

  public String parentUser() {
    return parentUser;
  }

  public String accountStatus() {
    return accountStatus;
  }

  public boolean impliedPolicy() {
    return impliedPolicy;
  }

  public String description() {
    return description;
  }

  public String name() {
    return name;
  }

  public String expiration() {
    return expiration;
  }
}

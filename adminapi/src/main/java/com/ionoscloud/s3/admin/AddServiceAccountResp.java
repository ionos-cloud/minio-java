

package com.ionoscloud.s3.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ionoscloud.s3.credentials.Credentials;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AddServiceAccountResp {
  @JsonProperty("credentials")
  private Credentials credentials;

  public Credentials credentials() {
    return credentials;
  }
}

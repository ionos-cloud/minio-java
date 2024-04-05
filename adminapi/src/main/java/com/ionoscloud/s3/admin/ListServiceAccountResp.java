

package com.ionoscloud.s3.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** list service account response. */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListServiceAccountResp {
  @JsonProperty("accounts")
  private List<ListServiceAccountInfo> accounts;

  public List<ListServiceAccountInfo> accounts() {
    return accounts;
  }

  public static class ListServiceAccountInfo {
    @JsonProperty("accessKey")
    private String accessKey;

    @JsonProperty("expiration")
    private String expiration;

    public String expiration() {
      return expiration;
    }

    public String accessKey() {
      return accessKey;
    }
  }
}

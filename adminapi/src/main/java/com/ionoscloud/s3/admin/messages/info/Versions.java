

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Versions contains the number of versions
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Versions {
  @JsonProperty("count")
  private Integer count;

  @JsonProperty("error")
  private String error;

  public Integer count() {
    return count;
  }

  public String error() {
    return error;
  }
}

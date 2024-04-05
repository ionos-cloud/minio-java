

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Objects contains the number of objects
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Objects {
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

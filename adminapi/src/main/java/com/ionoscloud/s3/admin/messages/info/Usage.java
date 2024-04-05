

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Usage contains the total size used
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usage {
  @JsonProperty("size")
  private Long size;

  @JsonProperty("error")
  private String error;

  public Long size() {
    return size;
  }

  public String error() {
    return error;
  }
}

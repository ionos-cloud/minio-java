

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * ErasureSetInfo provides information per erasure set
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErasureSetInfo {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("rawUsage")
  private BigDecimal rawUsage;

  @JsonProperty("rawCapacity")
  private BigDecimal rawCapacity;

  @JsonProperty("usage")
  private BigDecimal usage;

  @JsonProperty("objectsCount")
  private BigDecimal objectsCount;

  @JsonProperty("versionsCount")
  private BigDecimal versionsCount;

  @JsonProperty("healDisks")
  private Integer healDisks;

  public Integer id() {
    return id;
  }

  public BigDecimal rawUsage() {
    return rawUsage;
  }

  public BigDecimal rawCapacity() {
    return rawCapacity;
  }

  public BigDecimal usage() {
    return usage;
  }

  public BigDecimal objectsCount() {
    return objectsCount;
  }

  public BigDecimal versionsCount() {
    return versionsCount;
  }

  public Integer healDisks() {
    return healDisks;
  }
}

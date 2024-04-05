

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * TimedAction contains a number of actions and their accumulated duration in nanoseconds.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimedAction {
  @JsonProperty("count")
  private BigDecimal count;

  @JsonProperty("acc_time_ns")
  private BigDecimal accTime;

  @JsonProperty("bytes")
  private BigDecimal bytes;

  public BigDecimal count() {
    return count;
  }

  public BigDecimal accTime() {
    return accTime;
  }

  public BigDecimal bytes() {
    return bytes;
  }
}

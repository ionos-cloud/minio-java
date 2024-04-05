

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Map;

/**
 * DiskMetrics has the information about XL Storage APIs
 */
public class DiskMetrics {
  @JsonProperty("lastMinute")
  private Map<String, TimedAction> lastMinute;

  @JsonProperty("apiCalls")
  private Map<String, String> apiCalls;

  @JsonProperty("totalErrorsAvailability")
  private Integer totalErrorsAvailability;

  @JsonProperty("totalErrorsTimeout")
  private Integer totalErrorsTimeout;

  public Integer totalErrorsAvailability() {
    return totalErrorsAvailability;
  }

  public Integer totalErrorsTimeout() {
    return totalErrorsTimeout;
  }

  public Map<String, TimedAction> lastMinute() {
    return Collections.unmodifiableMap(lastMinute);
  }

  public Map<String, String> apiCalls() {
    return Collections.unmodifiableMap(apiCalls);
  }
}

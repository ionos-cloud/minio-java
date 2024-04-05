

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * MemStats is strip down version of runtime.MemStats containing memory stats of server.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemStats {
  @JsonProperty("Alloc")
  private BigDecimal alloc;

  @JsonProperty("TotalAlloc")
  private BigDecimal totalAlloc;

  @JsonProperty("Mallocs")
  private BigDecimal mallocs;

  @JsonProperty("Frees")
  private BigDecimal frees;

  @JsonProperty("HeapAlloc")
  private BigDecimal heapAlloc;

  public BigDecimal alloc() {
    return alloc;
  }

  public BigDecimal totalAlloc() {
    return totalAlloc;
  }

  public BigDecimal mallocs() {
    return mallocs;
  }

  public BigDecimal frees() {
    return frees;
  }

  public BigDecimal heapAlloc() {
    return heapAlloc;
  }
}

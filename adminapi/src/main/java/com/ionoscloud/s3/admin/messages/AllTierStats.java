

package com.ionoscloud.s3.admin.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Map;

/**
 * Collection of per-tier stats across all configured remote tiers
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllTierStats {
  @JsonProperty("Tiers")
  private Map<String, TierStats> tiers;

  public Map<String, TierStats> tiers() {
    return Collections.unmodifiableMap(this.tiers);
  }
}

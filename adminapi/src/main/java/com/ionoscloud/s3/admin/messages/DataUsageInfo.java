

package com.ionoscloud.s3.admin.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * Represents data usage stats of the current object APi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataUsageInfo {

  @JsonProperty("lastUpdate")
  private ZonedDateTime lastUpdate;

  @JsonProperty("objectsCount")
  private long objectsCount;

  @JsonProperty("versionsCount")
  private long versionsCount;

  @JsonProperty("objectsTotalSize")
  private long objectsTotalSize;

  @JsonProperty("objectsReplicationInfo")
  private Map<String, BucketTargetUsageInfo> objectsReplicationInfo;

  @JsonProperty("bucketsCount")
  private long bucketsCount;

  @JsonProperty("bucketsUsageInfo")
  private Map<String, BucketUsageInfo> bucketsUsageInfo;

  @JsonProperty("bucketsSizes")
  private Map<String, Long> bucketsSizes;

  @JsonProperty("tierStats")
  private AllTierStats tierStats;

  public ZonedDateTime lastUpdate() {
    return lastUpdate;
  }

  public long objectsCount() {
    return objectsCount;
  }

  public long versionsCount() {
    return versionsCount;
  }

  public long objectsTotalSize() {
    return objectsTotalSize;
  }

  public Map<String, BucketTargetUsageInfo> objectsReplicationInfo() {
    return Collections.unmodifiableMap(this.objectsReplicationInfo);
  }

  public long bucketsCount() {
    return bucketsCount;
  }

  public Map<String, BucketUsageInfo> bucketsUsageInfo() {
    return Collections.unmodifiableMap(this.bucketsUsageInfo);
  }

  public Map<String, Long> bucketsSizes() {
    return Collections.unmodifiableMap(bucketsSizes);
  }

  public AllTierStats tierStats() {
    return tierStats;
  }
}

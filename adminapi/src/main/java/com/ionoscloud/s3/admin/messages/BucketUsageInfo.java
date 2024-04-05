

package com.ionoscloud.s3.admin.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Map;

/**
 * Represents bucket usage stats of the current object APi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BucketUsageInfo {
  @JsonProperty("size")
  private long size;

  @JsonProperty("objectsPendingReplicationTotalSize")
  private long objectsPendingReplicationTotalSize;

  @JsonProperty("objectsFailedReplicationTotalSize")
  private long objectsFailedReplicationTotalSize;

  @JsonProperty("objectsReplicatedTotalSize")
  private long objectsReplicatedTotalSize;

  @JsonProperty("objectsPendingReplicationCount")
  private long objectsPendingReplicationCount;

  @JsonProperty("objectsFailedReplicationCount")
  private long objectsFailedReplicationCount;

  @JsonProperty("objectsCount")
  private long objectsCount;

  @JsonProperty("objectsSizesHistogram")
  private Map<String, Long> objectsSizesHistogram;

  @JsonProperty("versionsCount")
  private long versionsCount;

  @JsonProperty("objectReplicaTotalSize")
  private long objectReplicaTotalSize;

  @JsonProperty("objectsReplicationInfo")
  private Map<String, BucketTargetUsageInfo> objectsReplicationInfo;

  public long size() {
    return size;
  }

  public long objectsPendingReplicationTotalSize() {
    return objectsPendingReplicationTotalSize;
  }

  public long objectsFailedReplicationTotalSize() {
    return objectsFailedReplicationTotalSize;
  }

  public long objectsReplicatedTotalSize() {
    return objectsReplicatedTotalSize;
  }

  public long objectsPendingReplicationCount() {
    return objectsPendingReplicationCount;
  }

  public long objectsFailedReplicationCount() {
    return objectsFailedReplicationCount;
  }

  public long objectsCount() {
    return objectsCount;
  }

  public Map<String, Long> objectsSizesHistogram() {
    return Collections.unmodifiableMap(this.objectsSizesHistogram);
  }

  public long versionsCount() {
    return versionsCount;
  }

  public long objectReplicaTotalSize() {
    return objectReplicaTotalSize;
  }

  public Map<String, BucketTargetUsageInfo> objectsReplicationInfo() {
    return Collections.unmodifiableMap(this.objectsReplicationInfo);
  }
}

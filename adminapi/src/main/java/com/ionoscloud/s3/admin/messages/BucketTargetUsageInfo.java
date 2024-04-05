

package com.ionoscloud.s3.admin.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents bucket replica stats of the current object APi.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BucketTargetUsageInfo {
  @JsonProperty("objectsPendingReplicationTotalSize")
  private long objectsPendingReplicationTotalSize;

  @JsonProperty("objectsFailedReplicationTotalSize")
  private long objectsFailedReplicationTotalSize;

  @JsonProperty("objectsReplicatedTotalSize")
  private long objectsReplicatedTotalSize;

  @JsonProperty("objectReplicaTotalSize")
  private long objectReplicaTotalSize;

  @JsonProperty("objectsPendingReplicationCount")
  private long objectsPendingReplicationCount;

  @JsonProperty("objectsFailedReplicationCount")
  private long objectsFailedReplicationCount;

  public long objectsPendingReplicationTotalSize() {
    return objectsPendingReplicationTotalSize;
  }

  public long objectsFailedReplicationTotalSize() {
    return objectsFailedReplicationTotalSize;
  }

  public long objectsReplicatedTotalSize() {
    return objectsReplicatedTotalSize;
  }

  public long objectReplicaTotalSize() {
    return objectReplicaTotalSize;
  }

  public long objectsPendingReplicationCount() {
    return objectsPendingReplicationCount;
  }

  public long objectsFailedReplicationCount() {
    return objectsFailedReplicationCount;
  }
}

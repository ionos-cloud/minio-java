

package com.ionoscloud.s3.admin.messages.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * GCStats collect information about recent garbage collections.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GCStats {
  @JsonProperty("last_gc")
  private String lastGC;

  @JsonProperty("num_gc")
  private Integer numGC;

  @JsonProperty("pause_total")
  private Long pauseTotal;

  @JsonProperty("pause")
  private List<Integer> pause;

  @JsonProperty("pause_end")
  private List<String> pauseEnd;

  public String lastGC() {
    return lastGC;
  }

  public Integer numGC() {
    return numGC;
  }

  public Long pauseTotal() {
    return pauseTotal;
  }

  public List<Integer> pause() {
    return Collections.unmodifiableList(pause == null ? new LinkedList<>() : pause);
  }

  public List<String> pauseEnd() {
    return Collections.unmodifiableList(pauseEnd == null ? new LinkedList<>() : pauseEnd);
  }
}

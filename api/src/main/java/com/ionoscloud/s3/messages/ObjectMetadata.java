package com.ionoscloud.s3.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** Helper class to denote object information for {@link EventMetadata}. */
public class ObjectMetadata {
  @JsonProperty private String key;
  @JsonProperty private long size;
  @JsonProperty private String eTag;
  @JsonProperty private String versionId;
  @JsonProperty private String sequencer;
  @JsonProperty private Map<String, String> userMetadata;

  public String key() {
    return key;
  }

  public long size() {
    return size;
  }

  public String etag() {
    return eTag;
  }

  public String versionId() {
    return versionId;
  }

  public String sequencer() {
    return sequencer;
  }

  public Map<String, String> userMetadata() {
    return Collections.unmodifiableMap(userMetadata == null ? new HashMap<>() : userMetadata);
  }
}

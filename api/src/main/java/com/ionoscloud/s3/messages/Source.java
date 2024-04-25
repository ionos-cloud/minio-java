

package com.ionoscloud.s3.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Helper class to denote client information causes this event.
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
    value = "UwF",
    justification = "Everything in this class is initialized by JSON unmarshalling.")
public class Source {
  @JsonProperty private String host;
  @JsonProperty private String port;
  @JsonProperty private String userAgent;

  public String host() {
    return host;
  }

  public String port() {
    return port;
  }

  public String userAgent() {
    return userAgent;
  }
}

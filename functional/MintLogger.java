

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MintLogger {

  @JsonProperty("name")
  private String name;

  @JsonProperty("function")
  private String function;

  @JsonProperty("args")
  private String args;

  @JsonProperty("duration")
  private long duration;

  @JsonProperty("status")
  private String status;

  @JsonProperty("alert")
  private String alert;

  @JsonProperty("message")
  private String message;

  @JsonProperty("error")
  private String error;

  /** Constructor. */
  public MintLogger(
      String function,
      String args,
      long duration,
      String status,
      String alert,
      String message,
      String error) {
    this.name = "minio-java";
    this.function = function;
    this.duration = duration;
    this.args = args;
    this.status = status;
    this.alert = alert;
    this.message = message;
    this.error = error;
  }

  /** Return JSON Log Entry. */
  @JsonIgnore
  public String toString() {

    try {
      return new ObjectMapper()
          .setSerializationInclusion(Include.NON_NULL)
          .writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }

  /** Return Alert. */
  @JsonIgnore
  public String alert() {
    return alert;
  }

  /** Return Error. */
  @JsonIgnore
  public String error() {
    return error;
  }

  /** Return Message. */
  @JsonIgnore
  public String message() {
    return message;
  }

  /** Return args. */
  @JsonIgnore
  public String args() {
    return args;
  }

  /** Return status. */
  @JsonIgnore
  public String status() {
    return status;
  }

  /** Return name. */
  @JsonIgnore
  public String name() {
    return name;
  }

  /** Return function. */
  @JsonIgnore
  public String function() {
    return function;
  }

  /** Return duration. */
  @JsonIgnore
  public long duration() {
    return duration;
  }
}

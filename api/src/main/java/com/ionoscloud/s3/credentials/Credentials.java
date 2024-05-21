package com.ionoscloud.s3.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ionoscloud.s3.messages.ResponseDate;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Object representation of credentials access key, secret key and session token. */
@Root(name = "Credentials", strict = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Credentials {
  @Element(name = "AccessKeyId")
  @JsonProperty("accessKey")
  private final String accessKey;

  @Element(name = "SecretAccessKey")
  @JsonProperty("secretKey")
  private final String secretKey;

  @Element(name = "SessionToken")
  @JsonProperty("sessionToken")
  private final String sessionToken;

  @Element(name = "Expiration")
  @JsonProperty("expiration")
  private final ResponseDate expiration;

  public Credentials(
      @Nonnull @Element(name = "AccessKeyId") @JsonProperty("accessKey") String accessKey,
      @Nonnull @Element(name = "SecretAccessKey") @JsonProperty("secretKey") String secretKey,
      @Nullable @Element(name = "SessionToken") @JsonProperty("sessionToken") String sessionToken,
      @Nullable @Element(name = "Expiration") @JsonProperty("expiration") ResponseDate expiration) {
    this.accessKey = Objects.requireNonNull(accessKey, "AccessKey must not be null");
    this.secretKey = Objects.requireNonNull(secretKey, "SecretKey must not be null");
    if (accessKey.isEmpty() || secretKey.isEmpty()) {
      throw new IllegalArgumentException("AccessKey and SecretKey must not be empty");
    }
    this.sessionToken = sessionToken;
    this.expiration = expiration;
  }

  public String accessKey() {
    return accessKey;
  }

  public String secretKey() {
    return secretKey;
  }

  public String sessionToken() {
    return sessionToken;
  }

  public boolean isExpired() {
    if (expiration == null) {
      return false;
    }

    return ZonedDateTime.now().plus(Duration.ofSeconds(10)).isAfter(expiration.zonedDateTime());
  }
}

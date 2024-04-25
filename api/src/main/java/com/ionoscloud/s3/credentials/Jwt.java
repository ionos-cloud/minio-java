

package com.ionoscloud.s3.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.beans.ConstructorProperties;
import java.util.Objects;
import javax.annotation.Nonnull;

/** JSON web token used in WebIdentity and ClientGrants providers. */
public class Jwt {
  @JsonProperty("access_token")
  private final String token;

  @JsonProperty("expires_in")
  private final int expiry;

  @ConstructorProperties({"access_token", "expires_in"})
  public Jwt(@Nonnull String token, int expiry) {
    this.token = Objects.requireNonNull(token);
    this.expiry = expiry;
  }

  public String token() {
    return token;
  }

  public int expiry() {
    return expiry;
  }
}

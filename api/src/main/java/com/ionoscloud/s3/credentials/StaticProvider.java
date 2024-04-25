package com.ionoscloud.s3.credentials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Fixed credential provider. */
public class StaticProvider implements Provider {
  private final Credentials credentials;

  public StaticProvider(
      @Nonnull String accessKey, @Nonnull String secretKey, @Nullable String sessionToken) {
    this.credentials = new Credentials(accessKey, secretKey, sessionToken, null);
  }

  @Override
  public Credentials fetch() {
    return credentials;
  }
}

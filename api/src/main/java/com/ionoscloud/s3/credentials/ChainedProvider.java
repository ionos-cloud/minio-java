package com.ionoscloud.s3.credentials;

import java.security.ProviderException;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;

/** Chained credential provider work with list of credential providers. */
public class ChainedProvider implements Provider {
  private final List<Provider> providers;
  private Provider currentProvider;
  private Credentials credentials;

  public ChainedProvider(@Nonnull Provider... providers) {
    this.providers = Arrays.asList(providers);
  }

  @Override
  public synchronized Credentials fetch() {
    if (credentials != null && !credentials.isExpired()) {
      return credentials;
    }

    if (currentProvider != null) {
      try {
        credentials = currentProvider.fetch();
        return credentials;
      } catch (ProviderException e) {
        // Ignore and fallback to iteration.
      }
    }

    for (Provider provider : providers) {
      try {
        credentials = provider.fetch();
        currentProvider = provider;
        return credentials;
      } catch (ProviderException e) {
        // Ignore and continue to next iteration.
      }
    }

    throw new ProviderException("All providers fail to fetch credentials");
  }
}

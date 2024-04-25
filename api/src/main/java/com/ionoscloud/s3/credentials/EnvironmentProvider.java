

package com.ionoscloud.s3.credentials;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Base class of credential providers using environment variables. */
public abstract class EnvironmentProvider implements Provider {
  /** Get value of a property from system property or environment variable. */
  @Nullable
  protected String getProperty(@Nonnull String name) {
    final String value = System.getProperty(name);
    return (value != null) ? value : System.getenv(name);
  }
}

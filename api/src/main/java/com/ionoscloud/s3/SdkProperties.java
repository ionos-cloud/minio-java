

package com.ionoscloud.s3;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Identifies and stores version information of sdk-java-s3 package at run time. */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "MS_EXPOSE_REP")
public enum SdkProperties {
  INSTANCE;

  private static final Logger LOGGER = Logger.getLogger(SdkProperties.class.getName());

  private final AtomicReference<String> version = new AtomicReference<>(null);

  public String getVersion() {
    String result = version.get();
    if (result != null) {
      return result;
    }
    setVersion();
    return version.get();
  }

  private synchronized void setVersion() {
    if (version.get() != null) {
      return;
    }
    version.set("dev");
    ClassLoader classLoader = getClass().getClassLoader();
    if (classLoader == null) {
      return;
    }

    try {
      Enumeration<URL> resources = classLoader.getResources("META-INF/MANIFEST.MF");
      while (resources.hasMoreElements()) {
        try (InputStream is = resources.nextElement().openStream()) {
          Manifest manifest = new Manifest(is);
          if ("ionos".equals(manifest.getMainAttributes().getValue("Implementation-Title"))) {
            version.set(manifest.getMainAttributes().getValue("Implementation-Version"));
            return;
          }
        }
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "IOException occurred", e);
      version.set("unknown");
    }
  }

  public String getDefaultUserAgent() {
    return "ionos-cloud-sdk-java-s3-ionoscloud/"
        + getVersion();
  }
}

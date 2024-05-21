package com.ionoscloud.s3.credentials;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.ProviderException;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/** Credential provider using ApiClient configuration file. */
public class ApiClientConfigProvider extends EnvironmentProvider {
  private final String filename;
  private final String alias;
  private final ObjectMapper mapper;

  public ApiClientConfigProvider(@Nullable String filename, @Nullable String alias) {
    if (filename != null && filename.isEmpty()) {
      throw new IllegalArgumentException("Filename must not be empty");
    }

    if (alias != null && alias.isEmpty()) {
      throw new IllegalArgumentException("Alias must not be empty");
    }

    this.filename = filename;
    this.alias = alias;
    this.mapper =
        JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .build();
  }

  /**
   * Retrieve credentials in provided alias or IONOS_ALIAS or "s3" alias in configuration file from
   * provided filename or AWS_SHARED_CREDENTIALS_FILE environment variable or file .aws/credentials
   * in user's home directory.
   */
  @Override
  public Credentials fetch() {
    String filename = this.filename;
    if (filename == null) {
      filename = getProperty("IONOS_SHARED_CREDENTIALS_FILE");
    }
    if (filename == null) {
      String mcDir = ".mc";
      if (System.getProperty("os.name").toLowerCase(Locale.US).contains("windows")) {
        mcDir = "mc";
      }

      filename = Paths.get(System.getProperty("user.home"), mcDir, "config.json").toString();
    }

    String alias = this.alias;
    if (alias == null) {
      alias = getProperty("IONOS_ALIAS");
    }
    if (alias == null) {
      alias = "s3";
    }

    try (InputStream is = new FileInputStream(filename)) {
      McConfig config =
          mapper.readValue(new InputStreamReader(is, StandardCharsets.UTF_8), McConfig.class);
      Map<String, String> values = config.get(alias);
      if (values == null) {
        throw new ProviderException(
            "Alias " + alias + " does not exist in ApiClient configuration file");
      }

      String accessKey = values.get("accessKey");
      String secretKey = values.get("secretKey");

      if (accessKey == null) {
        throw new ProviderException(
            "Access key does not exist in alias " + alias + " in ApiClient configuration file");
      }

      if (secretKey == null) {
        throw new ProviderException(
            "Secret key does not exist in alias " + alias + " in ApiClient configuration file");
      }

      return new Credentials(accessKey, secretKey, null, null);
    } catch (IOException e) {
      throw new ProviderException("Unable to read ApiClient configuration file", e);
    }
  }

  @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
      value = {"UwF", "UuF"},
      justification = "All the fields are written at the time of JSON unmarshalling.")
  public static class McConfig {
    private Map<String, Map<String, String>> hosts;

    public Map<String, String> get(String alias) {
      return hosts.get(alias);
    }
  }
}

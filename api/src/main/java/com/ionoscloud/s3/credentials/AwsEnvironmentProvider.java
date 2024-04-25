

package com.ionoscloud.s3.credentials;

/** Credential provider using Amazon AWS specific environment variables. */
public class AwsEnvironmentProvider extends EnvironmentProvider {
  public AwsEnvironmentProvider() {}

  private final String getAccessKey() {
    String value = getProperty("AWS_ACCESS_KEY_ID");
    return (value != null) ? value : getProperty("AWS_ACCESS_KEY");
  }

  private final String getSecretKey() {
    String value = getProperty("AWS_SECRET_ACCESS_KEY");
    return (value != null) ? value : getProperty("AWS_SECRET_KEY");
  }

  @Override
  public Credentials fetch() {
    return new Credentials(getAccessKey(), getSecretKey(), getProperty("AWS_SESSION_TOKEN"), null);
  }
}

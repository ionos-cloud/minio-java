

package com.ionoscloud.s3.credentials;

import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/**
 * Credential provider using <a
 * href="https://docs.aws.amazon.com/STS/latest/APIReference/API_AssumeRoleWithWebIdentity.html">AssumeRoleWithWebIdentity
 * API</a>.
 */
public class WebIdentityProvider extends WebIdentityClientGrantsProvider {
  public WebIdentityProvider(
      @Nonnull Supplier<Jwt> supplier,
      @Nonnull String stsEndpoint,
      @Nullable Integer durationSeconds,
      @Nullable String policy,
      @Nullable String roleArn,
      @Nullable String roleSessionName,
      @Nullable OkHttpClient customHttpClient) {
    super(
        supplier, stsEndpoint, durationSeconds, policy, roleArn, roleSessionName, customHttpClient);
  }

  @Override
  protected HttpUrl.Builder newUrlBuilder(Jwt jwt) {
    HttpUrl.Builder urlBuilder =
        newUrlBuilder(
            stsEndpoint,
            "AssumeRoleWithWebIdentity",
            getDurationSeconds(jwt.expiry()),
            policy,
            roleArn,
            (roleArn != null && roleSessionName == null)
                ? String.valueOf(System.currentTimeMillis())
                : roleSessionName);
    return urlBuilder.addQueryParameter("WebIdentityToken", jwt.token());
  }

  @Override
  protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
    return WebIdentityResponse.class;
  }

  /** Object representation of response XML of AssumeRoleWithWebIdentity API. */
  @Root(name = "AssumeRoleWithWebIdentityResponse", strict = false)
  @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
  public static class WebIdentityResponse implements AssumeRoleBaseProvider.Response {
    @Path(value = "AssumeRoleWithWebIdentityResult")
    @Element(name = "Credentials")
    private Credentials credentials;

    public Credentials getCredentials() {
      return credentials;
    }
  }
}

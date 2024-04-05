

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
 * Credential provider
 */
public class ClientGrantsProvider extends WebIdentityClientGrantsProvider {
  public ClientGrantsProvider(
      @Nonnull Supplier<Jwt> supplier,
      @Nonnull String stsEndpoint,
      @Nullable Integer durationSeconds,
      @Nullable String policy,
      @Nullable OkHttpClient customHttpClient) {
    super(supplier, stsEndpoint, durationSeconds, policy, null, null, customHttpClient);
  }

  @Override
  protected HttpUrl.Builder newUrlBuilder(Jwt jwt) {
    HttpUrl.Builder urlBuilder =
        newUrlBuilder(
            stsEndpoint,
            "AssumeRoleWithClientGrants",
            getDurationSeconds(jwt.expiry()),
            policy,
            null,
            null);
    return urlBuilder.addQueryParameter("Token", jwt.token());
  }

  @Override
  protected Class<? extends AssumeRoleBaseProvider.Response> getResponseClass() {
    return ClientGrantsResponse.class;
  }

  /** Object representation of response XML of AssumeRoleWithClientGrants API. */
  @Root(name = "AssumeRoleWithClientGrantsResponse", strict = false)
  @Namespace(reference = "https://sts.amazonaws.com/doc/2011-06-15/")
  public static class ClientGrantsResponse implements AssumeRoleBaseProvider.Response {
    @Path(value = "AssumeRoleWithClientGrantsResult")
    @Element(name = "Credentials")
    private Credentials credentials;

    public Credentials getCredentials() {
      return credentials;
    }
  }
}

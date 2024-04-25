

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.credentials.ClientGrantsProvider;
import com.ionoscloud.s3.credentials.Jwt;
import com.ionoscloud.s3.credentials.Provider;
import java.io.IOException;
import java.security.ProviderException;
import java.util.Objects;
import javax.annotation.Nonnull;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiClientWithClientGrantsProvider {
  static Jwt getJwt(
      @Nonnull String clientId, @Nonnull String clientSecret, @Nonnull String idpEndpoint) {
    Objects.requireNonNull(clientId, "Client id must not be null");
    Objects.requireNonNull(clientSecret, "ClientSecret must not be null");

    RequestBody requestBody =
        new FormBody.Builder()
            .add("username", clientId)
            .add("password", clientSecret)
            .add("grant_type", "client_credentials")
            .build();

    Request request = new Request.Builder().url(idpEndpoint).post(requestBody).build();

    OkHttpClient client = new OkHttpClient();
    try (Response response = client.newCall(request).execute()) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      mapper.setVisibility(
          VisibilityChecker.Std.defaultInstance()
              .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
      return mapper.readValue(response.body().charStream(), Jwt.class);
    } catch (IOException e) {
      throw new ProviderException(e);
    }
  }

  public static void main(String[] args) throws Exception {
    // IDP endpoint.
    String idpEndpoint =
        "https://IDP-HOST:IDP-PORT/auth/realms/master/protocol/openid-connect/token";

    // Client-ID to fetch JWT.
    String clientId = "IDP-CLIENT-ID";

    // Client secret to fetch JWT.
    String clientSecret = "IDP-CLIENT-SECRET";

    // STS endpoint usually point to server.
    String stsEndpoint = "http://STS-HOST:STS-PORT/";

    // Policy to Credentials. - optional, primary policy
    // is inherited from JWT claims mapping.
    String policy = null;

    //
    // String policy =
    //     new StringBuilder()
    //         .append("{\n")
    //         .append("    \"Statement\": [\n")
    //         .append("       " + " {\n")
    //         .append("            \"Action\": [\n")
    //         .append("                \"s3:GetBucketLocation\",\n")
    //         .append("                \"s3:ListBucket\"\n")
    //         .append("            ],\n")
    //         .append("            \"Effect\": \"Allow\",\n")
    //         .append("            \"Principal\": \"*\",\n")
    //         .append("            \"Resource\": \"arn:aws:s3:::test\"\n")
    //         .append("        }\n")
    //         .append("    ],\n")
    //         .append("    \"Version\": \"2012-10-17\"\n")
    //         .append("}\n")
    //         .toString();

    Provider provider =
        new ClientGrantsProvider(
            () -> getJwt(clientId, clientSecret, idpEndpoint), stsEndpoint, null, policy, null);

    ApiClient apiClient =
        ApiClient.builder()
            .endpoint("https://IONOS-HOST:IONOS-PORT")
            .credentialsProvider(provider)
            .build();

    // Get information of an object.
    StatObjectResponse stat =
        apiClient.statObject(
            StatObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());
    System.out.println(stat);
  }
}

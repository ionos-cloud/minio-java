import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.credentials.LdapIdentityProvider;
import com.ionoscloud.s3.credentials.Provider;

public class ApiClientWithLdapIdentityProvider {
  public static void main(String[] args) throws Exception {
    // STS endpoint usually point to the server.
    String stsEndpoint = "http://STS-HOST:STS-PORT/";

    // LDAP username.
    String ldapUsername = "USERNAME";

    // LDAP password.
    String ldapPassword = "PASSWORD";

    Provider provider = new LdapIdentityProvider(stsEndpoint, ldapUsername, ldapPassword, null);

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

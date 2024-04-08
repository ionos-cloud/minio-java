import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.credentials.IamAwsProvider;
import com.ionoscloud.s3.credentials.Provider;

public class ApiClientWithIamAwsProvider {
  public static void main(String[] args) throws Exception {
    Provider provider = new IamAwsProvider(null, null);

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

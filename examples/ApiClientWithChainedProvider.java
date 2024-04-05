

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.credentials.AwsEnvironmentProvider;
import com.ionoscloud.s3.credentials.ChainedProvider;
import com.ionoscloud.s3.credentials.MinioEnvironmentProvider;
import com.ionoscloud.s3.credentials.Provider;

public class ApiClientWithChainedProvider {
  public static void main(String[] args) throws Exception {
    Provider provider =
        new ChainedProvider(new AwsEnvironmentProvider(), new MinioEnvironmentProvider());

    ApiClient apiClient =
        ApiClient.builder()
            .endpoint("https://MINIO-HOST:MINIO-PORT")
            .credentialsProvider(provider)
            .build();

    // Get information of an object.
    StatObjectResponse stat =
        apiClient.statObject(
            StatObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());
    System.out.println(stat);
  }
}

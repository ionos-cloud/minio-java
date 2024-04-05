

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.credentials.AssumeRoleProvider;
import com.ionoscloud.s3.credentials.Provider;

public class ApiClientWithAssumeRoleProvider {
  public static void main(String[] args) throws Exception {
    Provider provider =
        new AssumeRoleProvider(
            "https://play.minio.io:9000/", // STS endpoint usually point to MinIO server.
            "minio", // Access key.
            "minio123", // Secret key.
            null, // Duration seconds if available.
            null, // Policy if available.
            null, // Region if available.
            null, // Role ARN if available.
            null, // Role session name if available.
            null, // External ID if available.
            null);

    ApiClient apiClient =
        ApiClient.builder()
            .endpoint("https://play.minio.io:9000")
            .credentialsProvider(provider)
            .build();

    // Get information of an object.
    StatObjectResponse stat =
        apiClient.statObject(
            StatObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());
    System.out.println(stat);
  }
}

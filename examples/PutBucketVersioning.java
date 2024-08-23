import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PutBucketVersioningArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.VersioningConfiguration;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PutBucketVersioning {
  /** ApiClient.putBucketVersioning() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {

      ApiClient apiClient =
          ApiClient.builder()
              .endpoint(System.getenv("IONOS_API_URL"))
              .credentials(System.getenv("IONOS_S3_ACCESS_KEY"), System.getenv("IONOS_S3_SECRET_KEY"))
              .build();

      /* Amazon S3: */
      // ApiClient apiClient =
      //     ApiClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      // Enable versioning on 'my-bucketname'.
      apiClient.putBucketVersioning(
          PutBucketVersioningArgs.builder()
              .bucket("my-bucketname")
              .config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, null))
              .build());
      System.out.println("Bucket versioning is enabled successfully");

      // Suspend versioning on 'my-bucketname'.
      apiClient.putBucketVersioning(
          PutBucketVersioningArgs.builder()
              .bucket("my-bucketname")
              .config(new VersioningConfiguration(VersioningConfiguration.Status.SUSPENDED, null))
              .build());
      System.out.println("Bucket versioning is suspended successfully");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

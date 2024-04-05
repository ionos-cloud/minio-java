

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.SetBucketVersioningArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.VersioningConfiguration;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SetBucketVersioning {
  /** ApiClient.setBucketVersioning() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      /* play.min.io for test and development. */
      ApiClient apiClient =
          ApiClient.builder()
              .endpoint("https://play.min.io")
              .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
              .build();

      /* Amazon S3: */
      // ApiClient apiClient =
      //     ApiClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      // Enable versioning on 'my-bucketname'.
      apiClient.setBucketVersioning(
          SetBucketVersioningArgs.builder()
              .bucket("my-bucketname")
              .config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, null))
              .build());
      System.out.println("Bucket versioning is enabled successfully");

      // Suspend versioning on 'my-bucketname'.
      apiClient.setBucketVersioning(
          SetBucketVersioningArgs.builder()
              .bucket("my-bucketname")
              .config(new VersioningConfiguration(VersioningConfiguration.Status.SUSPENDED, null))
              .build());
      System.out.println("Bucket versioning is suspended successfully");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

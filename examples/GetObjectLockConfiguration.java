

import com.ionoscloud.s3.GetObjectLockConfigurationArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.ObjectLockConfiguration;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetObjectLockConfiguration {
  /** ApiClient.getObjectLockConfiguration() example. */
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

      ObjectLockConfiguration config =
          apiClient.getObjectLockConfiguration(
              GetObjectLockConfigurationArgs.builder()
                  .bucket("my-lock-enabled-bucketname")
                  .build());

      System.out.println("Object-lock configuration of bucket");
      System.out.println("Mode: " + config.mode());
      System.out.println("Duration: " + config.duration());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

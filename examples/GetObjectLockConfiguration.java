

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
      
      ApiClient apiClient =
          ApiClient.builder()
              .endpoint(System.getenv("IONOS_API_URL"))
              .credentials(System.getenv("IONOS_ACCESS_KEY"), System.getenv("IONOS_SECRET_KEY"))
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

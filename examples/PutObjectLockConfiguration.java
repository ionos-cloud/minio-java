

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PutObjectLockConfigurationArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.ObjectLockConfiguration;
import com.ionoscloud.s3.messages.RetentionDurationDays;
import com.ionoscloud.s3.messages.RetentionMode;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PutObjectLockConfiguration {
  /** ApiClient.putObjectLockConfiguration() exanple. */
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

      // Declaring config with Retention mode as Compliance and duration as 100 days
      ObjectLockConfiguration config =
          new ObjectLockConfiguration(RetentionMode.COMPLIANCE, new RetentionDurationDays(100));

      apiClient.putObjectLockConfiguration(
          PutObjectLockConfigurationArgs.builder()
              .bucket("my-lock-enabled-bucketname")
              .config(config)
              .build());

      System.out.println("object-lock configuration is set successfully");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}



import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.SetObjectRetentionArgs;
import com.ionoscloud.s3.Time;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Retention;
import com.ionoscloud.s3.messages.RetentionMode;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;

public class SetObjectRetention {
  /** ApiClient.setObjectRetention() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalArgumentException {
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

      // Declaring config with Retention mode as Compliance and
      // retain until 3 days from current date.
      ZonedDateTime retentionUntil = ZonedDateTime.now(Time.UTC).plusDays(3).withNano(0);
      Retention config = new Retention(RetentionMode.COMPLIANCE, retentionUntil);

      // Set object retention
      apiClient.setObjectRetention(
          SetObjectRetentionArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .config(config)
              .bypassGovernanceMode(true)
              .build());

    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}



import com.ionoscloud.s3.GetObjectRetentionArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Retention;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetObjectRetention {
  /** ApiClient.getObjectRetention() example. */
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

      // Get object lock retention
      Retention retention =
          apiClient.getObjectRetention(
              GetObjectRetentionArgs.builder()
                  .bucket("my-bucketname")
                  .object("my-objectname")
                  .build());

      System.out.println("Mode: " + retention.mode());
      System.out.println("Retainuntil Date: " + retention.retainUntilDate());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}


import com.ionoscloud.s3.DisableObjectLegalHoldArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DisableObjectLegalHold {
  /** ApiClient.disableObjectLegalHold() example. */
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

      // Disable object legal hold.
      apiClient.disableObjectLegalHold(
          DisableObjectLegalHoldArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .build());
      System.out.println("Legal hold disabled on object successfully ");

    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

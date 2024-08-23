import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.DisableObjectLegalHoldArgs;
import com.ionoscloud.s3.GetObjectLegalHoldArgs;
import com.ionoscloud.s3.PutObjectLegalHoldArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetObjectLegalHold {
  /** ApiClient.getObjectLegalHold() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalArgumentException {
    try {

      ApiClient apiClient =
          ApiClient.builder()
              .endpoint(System.getenv("IONOS_API_URL"))
              .credentials(
                  System.getenv("IONOS_S3_ACCESS_KEY"), System.getenv("IONOS_S3_SECRET_KEY"))
              .build();

      /* Amazon S3: */
      // ApiClient apiClient =
      //     ApiClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      // Enable object legal hold.
      apiClient.putObjectLegalHold(
          PutObjectLegalHoldArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .versionId("object-versionId")
              .build());
      System.out.println("Legal hold enabled on object successfully ");

      boolean status =
          apiClient.getObjectLegalHold(
              GetObjectLegalHoldArgs.builder()
                  .bucket("my-bucketname")
                  .object("my-objectname")
                  .build());

      if (status) {
        System.out.println("Legal hold is on");
      } else {
        System.out.println("Legal hold is off");
      }

      // Disable object legal hold.
      apiClient.disableObjectLegalHold(
          DisableObjectLegalHoldArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .build());

      status =
          apiClient.getObjectLegalHold(
              GetObjectLegalHoldArgs.builder()
                  .bucket("my-bucketname")
                  .object("my-objectname")
                  .build());
      System.out.println("Legal hold disabled on object successfully ");

      if (status) {
        System.out.println("Legal hold is on");
      } else {
        System.out.println("Legal hold is off");
      }

    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.HeadBucketArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HeadBucket {
  /** ApiClient.bucketExists() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {

      ApiClient apiClient =
          ApiClient.builder()
              .endpoint(System.getenv("IONOS_API_URL"))
              .credentials(System.getenv("IONOS_ACCESS_KEY"), System.getenv("IONOS_S3_SECRET_KEY"))
              .build();

      /* Amazon S3: */
      // ApiClient apiClient =
      //     ApiClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      // Check whether 'my-bucketname' exist or not.
      boolean found =
          apiClient.bucketExists(HeadBucketArgs.builder().bucket("my-bucketname").build());
      if (found) {
        System.out.println("my-bucketname exists");
      } else {
        System.out.println("my-bucketname does not exist");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.DeleteBucketArgs;
import com.ionoscloud.s3.HeadBucketArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DeleteBucket {
  /** ApiClient.deleteBucket() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
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

      // Remove bucket 'my-bucketname' if it exists.
      // This operation will only work if your bucket is empty.
      boolean found =
          apiClient.bucketExists(HeadBucketArgs.builder().bucket("my-bucketname").build());
      if (found) {
        apiClient.deleteBucket(DeleteBucketArgs.builder().bucket("my-bucketname").build());
        System.out.println("my-bucketname is removed successfully");
      } else {
        System.out.println("my-bucketname does not exist");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

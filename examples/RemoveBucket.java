

import com.ionoscloud.s3.BucketExistsArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.RemoveBucketArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RemoveBucket {
  /** ApiClient.removeBucket() example. */
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

      // Remove bucket 'my-bucketname' if it exists.
      // This operation will only work if your bucket is empty.
      boolean found =
          apiClient.bucketExists(BucketExistsArgs.builder().bucket("my-bucketname").build());
      if (found) {
        apiClient.removeBucket(RemoveBucketArgs.builder().bucket("my-bucketname").build());
        System.out.println("my-bucketname is removed successfully");
      } else {
        System.out.println("my-bucketname does not exist");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

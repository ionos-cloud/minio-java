

import com.ionoscloud.s3.DeleteBucketTagsArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DeleteBucketTags {
  /** ApiClient.deleteBucketTags() example. */
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

      apiClient.deleteBucketTags(DeleteBucketTagsArgs.builder().bucket("my-bucketname").build());
      System.out.println("Bucket tags deleted successfully");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

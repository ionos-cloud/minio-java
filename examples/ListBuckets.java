

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Bucket;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ListBuckets {
  /** ApiClient.listBuckets() example. */
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

      // List buckets we have atleast read access.
      List<Bucket> bucketList = apiClient.listBuckets();
      for (Bucket bucket : bucketList) {
        System.out.println(bucket.creationDate() + ", " + bucket.name());
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

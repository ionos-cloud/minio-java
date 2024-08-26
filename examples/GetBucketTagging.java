import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.GetBucketTaggingArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Tags;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetBucketTagging {
  /** ApiClient.getBucketTagging() example. */
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

      Tags tags =
          apiClient.getBucketTagging(
              GetBucketTaggingArgs.builder().bucket("my-bucketname").build());
      System.out.println("Bucket tags: " + tags.get());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

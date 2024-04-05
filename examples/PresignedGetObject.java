

import com.ionoscloud.s3.GetPresignedObjectUrlArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.http.Method;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PresignedGetObject {
  /** ApiClient.presignedGetObject() example. */
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

      // Get presigned URL string to download 'my-objectname' in 'my-bucketname' and its life time
      // is one day.
      String url =
          apiClient.getPresignedObjectUrl(
              GetPresignedObjectUrlArgs.builder()
                  .method(Method.GET)
                  .bucket("my-bucketname")
                  .object("my-objectname")
                  .expiry(60 * 60 * 24)
                  .build());
      System.out.println(url);
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

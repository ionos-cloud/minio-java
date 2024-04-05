

import com.ionoscloud.s3.BucketExistsArgs;
import com.ionoscloud.s3.MakeBucketArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MakeBucket {
  /** ApiClient.makeBucket() example. */
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

      // Create bucket 'my-bucketname' if it doesn`t exist.
      if (!apiClient.bucketExists(BucketExistsArgs.builder().bucket("my-bucketname").build())) {
        apiClient.makeBucket(MakeBucketArgs.builder().bucket("my-bucketname").build());
        System.out.println("my-bucketname is created successfully");
      }

      // Create bucket 'my-bucketname-in-eu' in 'eu-west-1' region if it doesn't exist.
      if (!apiClient.bucketExists(
          BucketExistsArgs.builder().bucket("my-bucketname-in-eu").build())) {
        apiClient.makeBucket(
            MakeBucketArgs.builder().bucket("my-bucketname-in-eu").region("eu-west-1").build());
        System.out.println("my-bucketname-in-eu is created successfully");
      }

      // Create bucket 'my-bucketname-in-eu-with-object-lock' in 'eu-west-1' with object lock
      // functionality enabled.
      if (!apiClient.bucketExists(
          BucketExistsArgs.builder().bucket("my-bucketname-in-eu-with-object-lock").build())) {
        apiClient.makeBucket(
            MakeBucketArgs.builder()
                .bucket("my-bucketname-in-eu-with-object-lock")
                .region("eu-west-1")
                .objectLock(true)
                .build());
        System.out.println("my-bucketname-in-eu-with-object-lock is created successfully");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

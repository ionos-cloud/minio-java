import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.DownloadObjectArgs;
import com.ionoscloud.s3.ServerSideEncryptionCustomerKey;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

public class DownloadObject {
  /** ApiClient.getObject() example. */
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

      {
        // Download 'my-objectname' from 'my-bucketname' to 'my-filename'
        apiClient.downloadObject(
            DownloadObjectArgs.builder()
                .bucket("my-bucketname")
                .object("my-objectname")
                .filename("my-filename")
                .build());
        System.out.println("my-objectname is successfully downloaded to my-filename");
      }

      {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        ServerSideEncryptionCustomerKey ssec =
            new ServerSideEncryptionCustomerKey(keyGen.generateKey());

        // Download SSE-C encrypted 'my-objectname' from 'my-bucketname' to 'my-filename'
        apiClient.downloadObject(
            DownloadObjectArgs.builder()
                .bucket("my-bucketname")
                .object("my-objectname")
                .filename("my-filename")
                .ssec(ssec) // Replace with same SSE-C used at the time of upload.
                .build());
        System.out.println("my-objectname is successfully downloaded to my-filename");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

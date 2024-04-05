

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.ServerSideEncryptionCustomerKey;
import com.ionoscloud.s3.UploadObjectArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

public class UploadObject {
  /** ApiClient.putObject() example. */
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

      {
        // Upload 'my-filename' as object 'my-objectname' in 'my-bucketname'.
        apiClient.uploadObject(
            UploadObjectArgs.builder()
                .bucket("my-bucketname")
                .object("my-objectname")
                .filename("my-filename")
                .build());
        System.out.println("my-filename is uploaded to my-objectname successfully");
      }

      {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        ServerSideEncryptionCustomerKey ssec =
            new ServerSideEncryptionCustomerKey(keyGen.generateKey());

        // Upload 'my-filename' as object encrypted 'my-objectname' in 'my-bucketname'.
        apiClient.uploadObject(
            UploadObjectArgs.builder()
                .bucket("my-bucketname")
                .object("my-objectname")
                .filename("my-filename")
                .sse(ssec)
                .build());
        System.out.println("my-filename is uploaded to my-objectname successfully");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

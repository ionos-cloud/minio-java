

import com.ionoscloud.s3.ComposeObjectArgs;
import com.ionoscloud.s3.ComposeSource;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.ServerSideEncryption;
import com.ionoscloud.s3.ServerSideEncryptionCustomerKey;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;

public class ComposeObject {
  /** ApiClient.composeObject() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      
      ApiClient apiClient =
          ApiClient.builder()
              .endpoint(System.getenv("IONOS_API_URL"))
              .credentials(System.getenv("IONOS_ACCESS_KEY"), System.getenv("IONOS_SECRET_KEY"))
              .build();

      /* Amazon S3: */
      // ApiClient apiClient =
      //     ApiClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      {
        // Create a ComposeSource to compose Object.
        List<ComposeSource> sources = new ArrayList<ComposeSource>();
        sources.add(
            ComposeSource.builder()
                .bucket("my-bucketname-one")
                .object("my-objectname-one")
                .build());
        sources.add(
            ComposeSource.builder()
                .bucket("my-bucketname-two")
                .object("my-objectname-two")
                .build());

        apiClient.composeObject(
            ComposeObjectArgs.builder()
                .bucket("my-destination-bucket")
                .object("my-destination-object")
                .sources(sources)
                .build());
        System.out.println("Object Composed successfully");
      }

      {
        ServerSideEncryptionCustomerKey srcSsec =
            new ServerSideEncryptionCustomerKey(
                new SecretKeySpec(
                    "01234567890123456789012345678901".getBytes(StandardCharsets.UTF_8), "AES"));

        ServerSideEncryption sse =
            new ServerSideEncryptionCustomerKey(
                new SecretKeySpec(
                    "12345678912345678912345678912345".getBytes(StandardCharsets.UTF_8), "AES"));

        List<ComposeSource> sources = new ArrayList<ComposeSource>();
        sources.add(
            ComposeSource.builder()
                .bucket("my-bucketname")
                .object("my-objectname-one")
                .ssec(srcSsec)
                .build());
        sources.add(
            ComposeSource.builder()
                .bucket("my-bucketname")
                .object("my-objectname-two")
                .ssec(srcSsec)
                .build());

        apiClient.composeObject(
            ComposeObjectArgs.builder()
                .bucket("my-destination-bucket")
                .object("my-destination-object")
                .sources(sources)
                .sse(sse)
                .build());
        System.out.println("Object Composed successfully");
      }

    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

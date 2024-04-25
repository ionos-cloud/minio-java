

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.ServerSideEncryptionCustomerKey;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.crypto.KeyGenerator;

public class StatObject {
  /** ApiClient.statObject() example. */
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

      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      ServerSideEncryptionCustomerKey ssec =
          new ServerSideEncryptionCustomerKey(keyGen.generateKey());
      String versionId = "ac38316c-fe14-4f96-9f76-8f675ae5a79e";

      {
        // Get information of an object.
        StatObjectResponse stat =
            apiClient.statObject(
                StatObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build());
        System.out.println(stat);
      }

      {
        // Get information of SSE-C encrypted object.
        StatObjectResponse stat =
            apiClient.statObject(
                StatObjectArgs.builder()
                    .bucket("my-bucketname")
                    .object("my-encrypted-objectname")
                    .ssec(ssec) // Replace with actual key.
                    .build());
        System.out.println(stat);
      }

      {
        // Get information of a versioned object.
        StatObjectResponse stat =
            apiClient.statObject(
                StatObjectArgs.builder()
                    .bucket("my-bucketname")
                    .object("my-versioned-objectname")
                    .versionId(versionId) // Replace with actual version ID.
                    .build());
        System.out.println(stat);
      }

      {
        // Get information of a SSE-C encrypted versioned object.
        StatObjectResponse stat =
            apiClient.statObject(
                StatObjectArgs.builder()
                    .bucket("my-bucketname")
                    .object("my-encrypted-versioned-objectname")
                    .versionId(versionId) // Replace with actual version ID.
                    .ssec(ssec) // Replace with actual key.
                    .build());
        System.out.println(stat);
      }

      {
        // Get information of an object with extra headers and query parameters.
        HashMap<String, String> headers = new HashMap<>();
        headers.put("x-amz-request-payer", "requester");
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("partNumber", "1");

        StatObjectResponse stat =
            apiClient.statObject(
                StatObjectArgs.builder()
                    .bucket("my-bucketname")
                    .object("my-objectname")
                    .extraHeaders(headers) // Replace with actual headers.
                    .extraQueryParams(queryParams) // Replace with actual query parameters.
                    .build());
        System.out.println(stat);
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

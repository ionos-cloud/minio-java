

import com.ionoscloud.s3.ListObjectsArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.Result;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Item;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ListObjects {
  /** ApiClient.listObjects() example. */
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
        // Lists objects information.
        Iterable<Result<Item>> results =
            apiClient.listObjects(ListObjectsArgs.builder().bucket("my-bucketname").build());

        for (Result<Item> result : results) {
          Item item = result.get();
          System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
        }
      }

      {
        // Lists objects information recursively.
        Iterable<Result<Item>> results =
            apiClient.listObjects(
                ListObjectsArgs.builder().bucket("my-bucketname").recursive(true).build());

        for (Result<Item> result : results) {
          Item item = result.get();
          System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
        }
      }

      {
        // Lists maximum 100 objects information those names starts with 'E' and after
        // 'ExampleGuide.pdf'.
        Iterable<Result<Item>> results =
            apiClient.listObjects(
                ListObjectsArgs.builder()
                    .bucket("my-bucketname")
                    .startAfter("ExampleGuide.pdf")
                    .prefix("E")
                    .maxKeys(100)
                    .build());

        for (Result<Item> result : results) {
          Item item = result.get();
          System.out.println(item.lastModified() + "\t" + item.size() + "\t" + item.objectName());
        }
      }

      {
        // Lists maximum 100 objects information with version those names starts with 'E' and after
        // 'ExampleGuide.pdf'.
        Iterable<Result<Item>> results =
            apiClient.listObjects(
                ListObjectsArgs.builder()
                    .bucket("my-bucketname")
                    .startAfter("ExampleGuide.pdf")
                    .prefix("E")
                    .maxKeys(100)
                    .includeVersions(true)
                    .build());

        for (Result<Item> result : results) {
          Item item = result.get();
          System.out.println(
              item.lastModified()
                  + "\t"
                  + item.size()
                  + "\t"
                  + item.objectName()
                  + " ["
                  + item.versionId()
                  + "]");
        }
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

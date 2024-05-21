import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.RemoveObjectsArgs;
import com.ionoscloud.s3.Result;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.DeleteError;
import com.ionoscloud.s3.messages.DeleteObject;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class RemoveObjects {
  /** ApiClient.removeObject() example removing multiple objects. */
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

      List<DeleteObject> objects = new LinkedList<>();
      objects.add(new DeleteObject("my-objectname1"));
      objects.add(new DeleteObject("my-objectname2"));
      objects.add(new DeleteObject("my-objectname3"));
      Iterable<Result<DeleteError>> results =
          apiClient.removeObjects(
              RemoveObjectsArgs.builder().bucket("my-bucketname").objects(objects).build());
      for (Result<DeleteError> result : results) {
        DeleteError error = result.get();
        System.out.println(
            "Error in deleting object " + error.objectName() + "; " + error.message());
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

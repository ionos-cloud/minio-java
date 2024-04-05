

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.SetObjectTagsArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SetObjectTags {
  /** ApiClient.setObjectTags() example. */
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

      Map<String, String> map = new HashMap<>();
      map.put("Project", "Project One");
      map.put("User", "jsmith");
      apiClient.setObjectTags(
          SetObjectTagsArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .tags(map)
              .build());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

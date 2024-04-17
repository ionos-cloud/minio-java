

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PutBucketLifecycleArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Expiration;
import com.ionoscloud.s3.messages.LifecycleConfiguration;
import com.ionoscloud.s3.messages.LifecycleRule;
import com.ionoscloud.s3.messages.RuleFilter;
import com.ionoscloud.s3.messages.Status;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class PutBucketLifecycle {
  /** ApiClient.PutBucketLifecycle() example. */
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

      List<LifecycleRule> rules = new LinkedList<>();
      rules.add(
          new LifecycleRule(
              Status.ENABLED,
              null,
              new Expiration((ZonedDateTime) null, 365, null),
              new RuleFilter("logs/"),
              "rule2",
              null,
              null,
              null));
      LifecycleConfiguration config = new LifecycleConfiguration(rules);

      apiClient.putBucketLifecycle(
          PutBucketLifecycleArgs.builder().bucket("my-bucketname").config(config).build());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

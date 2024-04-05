

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.SetBucketReplicationArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.AndOperator;
import com.ionoscloud.s3.messages.DeleteMarkerReplication;
import com.ionoscloud.s3.messages.ReplicationConfiguration;
import com.ionoscloud.s3.messages.ReplicationDestination;
import com.ionoscloud.s3.messages.ReplicationRule;
import com.ionoscloud.s3.messages.RuleFilter;
import com.ionoscloud.s3.messages.Status;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SetBucketReplication {
  /** ApiClient.setBucketReplication() example. */
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

      Map<String, String> tags = new HashMap<>();
      tags.put("key1", "value1");
      tags.put("key2", "value2");

      ReplicationRule rule =
          new ReplicationRule(
              new DeleteMarkerReplication(Status.DISABLED),
              new ReplicationDestination(
                  null, null, "REPLACE-WITH-ACTUAL-DESTINATION-BUCKET-ARN", null, null, null, null),
              null,
              new RuleFilter(new AndOperator("TaxDocs", tags)),
              "rule1",
              null,
              1,
              null,
              Status.ENABLED);

      List<ReplicationRule> rules = new LinkedList<>();
      rules.add(rule);

      ReplicationConfiguration config =
          new ReplicationConfiguration("REPLACE-WITH-ACTUAL-ROLE", rules);

      apiClient.setBucketReplication(
          SetBucketReplicationArgs.builder().bucket("my-bucketname").config(config).build());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

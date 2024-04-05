

import com.ionoscloud.s3.GetBucketEncryptionArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.SseAlgorithm;
import com.ionoscloud.s3.messages.SseConfiguration;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetBucketEncryption {
  /** ApiClient.getBucketEncryption() example. */
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

      SseConfiguration config =
          apiClient.getBucketEncryption(
              GetBucketEncryptionArgs.builder().bucket("my-bucketname").build());
      if (config.rule() != null) {
        System.out.println("Rule SSE algorithm: " + config.rule().sseAlgorithm());
        if (config.rule().sseAlgorithm() == SseAlgorithm.AWS_KMS) {
          System.out.println("Rule KMS master key ID: " + config.rule().kmsMasterKeyId());
        }
      } else {
        System.out.println("No rule is set in SSE configuration.");
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

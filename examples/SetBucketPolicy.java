

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PutBucketPolicyArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PutBucketPolicy {
  /** ApiClient.putBucketPolicy() example. */
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

      StringBuilder builder = new StringBuilder();
      builder.append("{\n");
      builder.append("    \"Statement\": [\n");
      builder.append("        {\n");
      builder.append("            \"Action\": [\n");
      builder.append("                \"s3:GetBucketLocation\",\n");
      builder.append("                \"s3:ListBucket\"\n");
      builder.append("            ],\n");
      builder.append("            \"Effect\": \"Allow\",\n");
      builder.append("            \"Principal\": \"*\",\n");
      builder.append("            \"Resource\": \"arn:aws:s3:::my-bucketname\"\n");
      builder.append("        },\n");
      builder.append("        {\n");
      builder.append("            \"Action\": \"s3:GetObject\",\n");
      builder.append("            \"Effect\": \"Allow\",\n");
      builder.append("            \"Principal\": \"*\",\n");
      builder.append("            \"Resource\": \"arn:aws:s3:::my-bucketname/myobject*\"\n");
      builder.append("        }\n");
      builder.append("    ],\n");
      builder.append("    \"Version\": \"2012-10-17\"\n");
      builder.append("}\n");
      apiClient.putBucketPolicy(
          PutBucketPolicyArgs.builder().bucket("my-bucketname").config(builder.toString()).build());
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

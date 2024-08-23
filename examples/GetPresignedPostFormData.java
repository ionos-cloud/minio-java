import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PostPolicy;
import com.ionoscloud.s3.errors.ApiException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetPresignedPostFormData {
  /** ApiClient.presignedPostPolicy() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {

      ApiClient apiClient =
          ApiClient.builder()
              .endpoint(System.getenv("IONOS_API_URL"))
              .credentials(
                  System.getenv("IONOS_S3_ACCESS_KEY"), System.getenv("IONOS_S3_SECRET_KEY"))
              .build();

      /* Amazon S3: */
      // ApiClient apiClient =
      //     ApiClient.builder()
      //         .endpoint("https://s3.amazonaws.com")
      //         .credentials("YOUR-ACCESSKEY", "YOUR-SECRETACCESSKEY")
      //         .build();

      // Create new post policy for 'my-bucketname' with 7 days expiry from now.
      PostPolicy policy = new PostPolicy("my-bucketname", ZonedDateTime.now().plusDays(7));

      // Add condition that 'key' (object name) equals to 'my-objectname'.
      policy.addEqualsCondition("key", "my-objectname");

      // Add condition that 'Content-Type' starts with 'image/'.
      policy.addStartsWithCondition("Content-Type", "image/");

      // Add condition that 'content-length-range' is between 64kiB to 10MiB.
      policy.addContentLengthRangeCondition(64 * 1024, 10 * 1024 * 1024);

      Map<String, String> formData = apiClient.getPresignedPostFormData(policy);

      // Upload an image using POST object with form-data.
      MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
      multipartBuilder.setType(MultipartBody.FORM);
      for (Map.Entry<String, String> entry : formData.entrySet()) {
        multipartBuilder.addFormDataPart(entry.getKey(), entry.getValue());
      }
      multipartBuilder.addFormDataPart("key", "my-objectname");
      multipartBuilder.addFormDataPart("Content-Type", "image/png");

      // "file" must be added at last.
      multipartBuilder.addFormDataPart(
          "file", "my-objectname", RequestBody.create(new File("Pictures/avatar.png"), null));

      Request request = new Request.Builder().url("<url>").post(multipartBuilder.build()).build();
      OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
      Response response = httpClient.newCall(request).execute();
      if (response.isSuccessful()) {
        System.out.println("Pictures/avatar.png is uploaded successfully using POST object");
      } else {
        System.out.println("Failed to upload Pictures/avatar.png");
      }

      // Print curl command usage to upload file /tmp/userpic.jpg.
      System.out.print("curl -X POST ");
      for (Map.Entry<String, String> entry : formData.entrySet()) {
        System.out.print(" -F " + entry.getKey() + "=" + entry.getValue());
      }
      System.out.print(" -F key=my-objectname -F Content-Type=image/jpg");
      System.out.println(" -F file=@/tmp/userpic.jpg <url>");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

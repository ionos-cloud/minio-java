

import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PutObjectArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PutObjectProgressBar {
  /** ApiClient.putObjectProgressBar() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException, ApiException {
    
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

    String objectName = "my-objectname";
    String bucketName = "my-bucketname";

    File file = new File("my-filename");
    InputStream pis =
        new BufferedInputStream(new ProgressStream("Uploading... ", new FileInputStream(file)));
    apiClient.putObject(
        PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                pis, pis.available(), -1)
            .build());
    pis.close();
    System.out.println("my-objectname is uploaded successfully");
  }
}

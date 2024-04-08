

import com.google.common.io.ByteStreams;
import com.ionoscloud.s3.GetObjectArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GetObjectProgressBar {
  /** ApiClient.getObjectProgressBar() example. */
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

      // Check whether the object exists using statObject(). If the object is not found,
      // statObject() throws an exception. It means that the object exists when statObject()
      // execution is successful.

      // Get object stat information.
      StatObjectResponse stat =
          apiClient.statObject(
              StatObjectArgs.builder()
                  .bucket("testbucket")
                  .object("resumes/4.original.pdf")
                  .build());

      // Get input stream to have content of 'my-objectname' from 'my-bucketname'
      InputStream is =
          new ProgressStream(
              "Downloading .. ",
              stat.size(),
              apiClient.getObject(
                  GetObjectArgs.builder().bucket("my-bucketname").object("my-objectname").build()));

      Path path = Paths.get("my-filename");
      OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE);

      long bytesWritten = ByteStreams.copy(is, os);
      is.close();
      os.close();

      if (bytesWritten != stat.size()) {
        throw new IOException(
            path
                + ": unexpected data written.  expected = "
                + stat.size()
                + ", written = "
                + bytesWritten);
      }

    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

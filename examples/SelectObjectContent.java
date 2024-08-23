import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PutObjectArgs;
import com.ionoscloud.s3.SelectObjectContentArgs;
import com.ionoscloud.s3.SelectResponseStream;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.FileHeaderInfo;
import com.ionoscloud.s3.messages.InputSerialization;
import com.ionoscloud.s3.messages.OutputSerialization;
import com.ionoscloud.s3.messages.QuoteFields;
import com.ionoscloud.s3.messages.Stats;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SelectObjectContent {
  /** ApiClient.getObject() example. */
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

      byte[] data =
          ("Year,Make,Model,Description,Price\n"
                  + "1997,Ford,E350,\"ac, abs, moon\",3000.00\n"
                  + "1999,Chevy,\"Venture \"\"Extended Edition\"\"\",\"\",4900.00\n"
                  + "1999,Chevy,\"Venture \"\"Extended Edition, Very Large\"\"\",,5000.00\n"
                  + "1996,Jeep,Grand Cherokee,\"MUST SELL!\n"
                  + "air, moon roof, loaded\",4799.00\n")
              .getBytes(StandardCharsets.UTF_8);
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      apiClient.putObject(
          PutObjectArgs.builder().bucket("my-bucketname").object("my-objectname").stream(
                  bais, data.length, -1)
              .build());

      String sqlExpression = "select * from S3Object";
      InputSerialization is =
          new InputSerialization(null, false, null, null, FileHeaderInfo.USE, null, null, null);
      OutputSerialization os =
          new OutputSerialization(null, null, null, QuoteFields.ASNEEDED, null);

      SelectResponseStream stream =
          apiClient.selectObjectContent(
              SelectObjectContentArgs.builder()
                  .bucket("my-bucketname")
                  .object("my-objectName")
                  .sqlExpression(sqlExpression)
                  .inputSerialization(is)
                  .outputSerialization(os)
                  .requestProgress(true)
                  .build());

      byte[] buf = new byte[512];
      int bytesRead = stream.read(buf, 0, buf.length);
      System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
      Stats stats = stream.stats();
      System.out.println("bytes scanned: " + stats.bytesScanned());
      System.out.println("bytes processed: " + stats.bytesProcessed());
      System.out.println("bytes returned: " + stats.bytesReturned());
      stream.close();
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

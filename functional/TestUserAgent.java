

import com.ionoscloud.s3.BucketExistsArgs;
import com.ionoscloud.s3.ApiClient;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestUserAgent {
  public static void main(String[] args) throws Exception {
    ApiClient client = ApiClient.builder().endpoint("http://example.org").build();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    client.traceOn(baos);
    client.bucketExists(BucketExistsArgs.builder().bucket("any-bucket-name-works").build());
    client.traceOff();

    String expectedVersion = System.getProperty("version");
    String version = null;
    try (Scanner scanner = new Scanner(new String(baos.toByteArray(), StandardCharsets.UTF_8))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.startsWith("User-Agent:")) {
          version = line.split("/")[1];
          break;
        }
      }
    }

    if (!expectedVersion.equals(version)) {
      throw new Exception(
          "version does not match; expected=" + expectedVersion + ", got=" + version);
    }
  }
}

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import com.ionoscloud.s3.*;
import java.nio.file.*;

@SuppressFBWarnings(
    value = "REC",
    justification = "Allow catching super class Exception since it's tests")
class PutObjectRunnable implements Runnable {
  ApiClient client;
  String bucketName;
  String filename;

  public PutObjectRunnable(ApiClient client, String bucketName, String filename) {
    this.client = client;
    this.bucketName = bucketName;
    this.filename = filename;
  }

  public void run() {
    StringBuffer traceBuffer = new StringBuffer();

    try {
      traceBuffer.append("[" + filename + "]: threaded put object\n");
      client.postObject(
          PostObjectArgs.builder()
              .bucket(bucketName)
              .object(filename)
              .filename(filename)
              .build());
      traceBuffer.append("[" + filename + "]: delete file\n");
      Files.delete(Paths.get(filename));
      traceBuffer.append("[" + filename + "]: delete object\n");
      client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(filename).build());
    } catch (Exception e) {
      System.err.print(traceBuffer.toString());
      e.printStackTrace();
    }
  }
}

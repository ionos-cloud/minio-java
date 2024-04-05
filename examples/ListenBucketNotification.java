

import com.ionoscloud.s3.CloseableIterator;
import com.ionoscloud.s3.ListenBucketNotificationArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.Result;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.Event;
import com.ionoscloud.s3.messages.NotificationRecords;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ListenBucketNotification {
  /** ApiClient.listenBucketNotification() example. */
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

      String[] events = {"s3:ObjectCreated:*", "s3:ObjectAccessed:*"};
      try (CloseableIterator<Result<NotificationRecords>> ci =
          apiClient.listenBucketNotification(
              ListenBucketNotificationArgs.builder()
                  .bucket("bucketName")
                  .prefix("")
                  .suffix("")
                  .events(events)
                  .build())) {
        while (ci.hasNext()) {
          NotificationRecords records = ci.next().get();
          Event event = records.events().get(0);
          System.out.println(event.bucketName() + "/" + event.objectName() + " has been created");
        }
      } catch (IOException e) {
        System.out.println("Error occurred: " + e);
      }
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

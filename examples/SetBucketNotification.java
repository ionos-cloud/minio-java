import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.SetBucketNotificationArgs;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.messages.EventType;
import com.ionoscloud.s3.messages.NotificationConfiguration;
import com.ionoscloud.s3.messages.QueueConfiguration;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class SetBucketNotification {
  /** ApiClient.setBucketNotification() example. */
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

      NotificationConfiguration config = new NotificationConfiguration();

      // Add a new SQS configuration.
      List<QueueConfiguration> queueConfigurationList = new LinkedList<>();
      QueueConfiguration queueConfiguration = new QueueConfiguration();
      queueConfiguration.setQueue("arn:ionos:sqs::1:webhook");

      List<EventType> eventList = new LinkedList<>();
      eventList.add(EventType.OBJECT_CREATED_PUT);
      eventList.add(EventType.OBJECT_CREATED_COPY);
      queueConfiguration.setEvents(eventList);
      queueConfiguration.setPrefixRule("images");
      queueConfiguration.setSuffixRule("pg");

      queueConfigurationList.add(queueConfiguration);
      config.setQueueConfigurationList(queueConfigurationList);

      // Set updated notification configuration.
      apiClient.setBucketNotification(
          SetBucketNotificationArgs.builder().bucket("my-bucketname").config(config).build());
      System.out.println("Bucket notification is set successfully");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

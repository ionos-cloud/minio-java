

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import com.google.common.io.BaseEncoding;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import com.ionoscloud.s3.HeadBucketArgs;
import com.ionoscloud.s3.CloseableIterator;
import com.ionoscloud.s3.ComposeObjectArgs;
import com.ionoscloud.s3.ComposeSource;
import com.ionoscloud.s3.CopyObjectArgs;
import com.ionoscloud.s3.CopySource;
import com.ionoscloud.s3.DeleteBucketEncryptionArgs;
import com.ionoscloud.s3.DeleteBucketLifecycleArgs;
import com.ionoscloud.s3.DeleteBucketNotificationArgs;
import com.ionoscloud.s3.DeleteBucketPolicyArgs;
import com.ionoscloud.s3.DeleteBucketReplicationArgs;
import com.ionoscloud.s3.DeleteBucketTaggingArgs;
import com.ionoscloud.s3.DeleteObjectLockConfigurationArgs;
import com.ionoscloud.s3.DeleteObjectTaggingArgs;
import com.ionoscloud.s3.Directive;
import com.ionoscloud.s3.DisableObjectLegalHoldArgs;
import com.ionoscloud.s3.DownloadObjectArgs;
import com.ionoscloud.s3.PutObjectLegalHoldArgs;
import com.ionoscloud.s3.GetBucketEncryptionArgs;
import com.ionoscloud.s3.GetBucketLifecycleArgs;
import com.ionoscloud.s3.GetBucketNotificationArgs;
import com.ionoscloud.s3.GetBucketPolicyArgs;
import com.ionoscloud.s3.GetBucketReplicationArgs;
import com.ionoscloud.s3.GetBucketTaggingArgs;
import com.ionoscloud.s3.GetBucketVersioningArgs;
import com.ionoscloud.s3.GetObjectArgs;
import com.ionoscloud.s3.GetObjectLockConfigurationArgs;
import com.ionoscloud.s3.GetObjectRetentionArgs;
import com.ionoscloud.s3.GetObjectTaggingArgs;
import com.ionoscloud.s3.GetPresignedObjectUrlArgs;
import com.ionoscloud.s3.GetObjectLegalHoldArgs;
import com.ionoscloud.s3.ListObjectsArgs;
import com.ionoscloud.s3.ListenBucketNotificationArgs;
import com.ionoscloud.s3.MakeBucketArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.ObjectWriteResponse;
import com.ionoscloud.s3.PostPolicy;
import com.ionoscloud.s3.PutObjectArgs;
import com.ionoscloud.s3.DeleteBucketArgs;
import com.ionoscloud.s3.RemoveObjectArgs;
import com.ionoscloud.s3.RemoveObjectsArgs;
import com.ionoscloud.s3.Result;
import com.ionoscloud.s3.SelectObjectContentArgs;
import com.ionoscloud.s3.SelectResponseStream;
import com.ionoscloud.s3.ServerSideEncryption;
import com.ionoscloud.s3.ServerSideEncryptionCustomerKey;
import com.ionoscloud.s3.ServerSideEncryptionKms;
import com.ionoscloud.s3.ServerSideEncryptionS3;
import com.ionoscloud.s3.PutBucketEncryptionArgs;
import com.ionoscloud.s3.PutBucketLifecycleArgs;
import com.ionoscloud.s3.SetBucketNotificationArgs;
import com.ionoscloud.s3.PutBucketPolicyArgs;
import com.ionoscloud.s3.SetBucketReplicationArgs;
import com.ionoscloud.s3.PutBucketTaggingArgs;
import com.ionoscloud.s3.PutBucketVersioningArgs;
import com.ionoscloud.s3.PutObjectLockConfigurationArgs;
import com.ionoscloud.s3.PutObjectRetentionArgs;
import com.ionoscloud.s3.PutObjectTaggingArgs;
import com.ionoscloud.s3.SnowballObject;
import com.ionoscloud.s3.StatObjectArgs;
import com.ionoscloud.s3.StatObjectResponse;
import com.ionoscloud.s3.Time;
import com.ionoscloud.s3.PostObjectArgs;
import com.ionoscloud.s3.UploadSnowballObjectsArgs;
import com.ionoscloud.s3.Xml;
import com.ionoscloud.s3.errors.ErrorResponseException;
import com.ionoscloud.s3.http.HttpUtils;
import com.ionoscloud.s3.http.Method;
import com.ionoscloud.s3.messages.AndOperator;
import com.ionoscloud.s3.messages.Bucket;
import com.ionoscloud.s3.messages.DeleteMarkerReplication;
import com.ionoscloud.s3.messages.DeleteObject;
import com.ionoscloud.s3.messages.Event;
import com.ionoscloud.s3.messages.EventType;
import com.ionoscloud.s3.messages.Expiration;
import com.ionoscloud.s3.messages.FileHeaderInfo;
import com.ionoscloud.s3.messages.InputSerialization;
import com.ionoscloud.s3.messages.LifecycleConfiguration;
import com.ionoscloud.s3.messages.LifecycleRule;
import com.ionoscloud.s3.messages.NotificationConfiguration;
import com.ionoscloud.s3.messages.NotificationRecords;
import com.ionoscloud.s3.messages.ObjectLockConfiguration;
import com.ionoscloud.s3.messages.OutputSerialization;
import com.ionoscloud.s3.messages.QueueConfiguration;
import com.ionoscloud.s3.messages.QuoteFields;
import com.ionoscloud.s3.messages.ReplicationConfiguration;
import com.ionoscloud.s3.messages.ReplicationDestination;
import com.ionoscloud.s3.messages.ReplicationRule;
import com.ionoscloud.s3.messages.Retention;
import com.ionoscloud.s3.messages.RetentionDuration;
import com.ionoscloud.s3.messages.RetentionDurationDays;
import com.ionoscloud.s3.messages.RetentionDurationYears;
import com.ionoscloud.s3.messages.RetentionMode;
import com.ionoscloud.s3.messages.RuleFilter;
import com.ionoscloud.s3.messages.SseAlgorithm;
import com.ionoscloud.s3.messages.SseConfiguration;
import com.ionoscloud.s3.messages.Stats;
import com.ionoscloud.s3.messages.Status;
import com.ionoscloud.s3.messages.Tags;
import com.ionoscloud.s3.messages.VersioningConfiguration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.crypto.KeyGenerator;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Assert;

@SuppressFBWarnings(
    value = "REC",
    justification = "Allow catching super class Exception since it's tests")
public class FunctionalTest {
  private static final String PASS = "PASS";
  private static final String FAILED = "FAIL";
  private static final String IGNORED = "NA";
  private static final int KB = 1024;
  private static final int MB = 1024 * 1024;
  private static final Random random = new Random(new SecureRandom().nextLong());
  private static final String customContentType = "application/javascript";
  private static String bucketName = getRandomName();
  private static String bucketNameWithLock = getRandomName();
  private static boolean mintEnv = false;
  private static boolean isQuickTest = false;
  private static boolean isRunOnFail = false;
  private static Path dataFile1Kb;
  private static Path dataFile6Mb;
  private static String endpoint;
  private static String endpointTLS;
  private static String accessKey;
  private static String secretKey;
  private static String region;
  private static boolean isSecureEndpoint = false;
  private static String sqsArn = null;
  private static String replicationSrcBucket = null;
  private static String replicationRole = null;
  private static String replicationBucketArn = null;
  private static ApiClient client = null;

  private static ServerSideEncryptionCustomerKey ssec = null;
  private static ServerSideEncryption sseS3 = new ServerSideEncryptionS3();
  private static ServerSideEncryption sseKms = null;

  static {
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256);
      ssec = new ServerSideEncryptionCustomerKey(keyGen.generateKey());
    } catch (InvalidKeyException | NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static OkHttpClient newHttpClient() {
    try {
      return HttpUtils.disableCertCheck(
          HttpUtils.newDefaultHttpClient(
              TimeUnit.MINUTES.toMillis(5),
              TimeUnit.MINUTES.toMillis(5),
              TimeUnit.MINUTES.toMillis(5)));
    } catch (KeyManagementException | NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  /** Do no-op. */
  public static void ignore(Object... args) {}

  /** Create given sized file and returns its name. */
  public static String createFile(int size) throws IOException {
    String filename = getRandomName();

    try (OutputStream os = Files.newOutputStream(Paths.get(filename), CREATE, APPEND)) {
      int totalBytesWritten = 0;
      int bytesToWrite = 0;
      byte[] buf = new byte[1 * MB];
      while (totalBytesWritten < size) {
        random.nextBytes(buf);
        bytesToWrite = size - totalBytesWritten;
        if (bytesToWrite > buf.length) {
          bytesToWrite = buf.length;
        }

        os.write(buf, 0, bytesToWrite);
        totalBytesWritten += bytesToWrite;
      }
    }

    return filename;
  }

  /** Create 1 KB temporary file. */
  public static String createFile1Kb() throws IOException {
    if (mintEnv) {
      String filename = getRandomName();
      Files.createSymbolicLink(Paths.get(filename).toAbsolutePath(), dataFile1Kb);
      return filename;
    }

    return createFile(1 * KB);
  }

  /** Create 6 MB temporary file. */
  public static String createFile6Mb() throws IOException {
    if (mintEnv) {
      String filename = getRandomName();
      Files.createSymbolicLink(Paths.get(filename).toAbsolutePath(), dataFile6Mb);
      return filename;
    }

    return createFile(6 * MB);
  }

  /** Generate random name. */
  public static String getRandomName() {
    return "ionoscloud-java-test-" + new BigInteger(32, random).toString(32);
  }

  /** Returns byte array contains all data in given InputStream. */
  public static byte[] readAllBytes(InputStream is) throws IOException {
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    int nRead;
    byte[] data = new byte[16384];
    while ((nRead = is.read(data, 0, data.length)) != -1) {
      buffer.write(data, 0, nRead);
    }
    return buffer.toByteArray();
  }

  /** Prints a success log entry in JSON format. */
  public static void mintSuccessLog(String function, String args, long startTime) {
    if (mintEnv) {
      System.out.println(
          new MintLogger(
              function, args, System.currentTimeMillis() - startTime, PASS, null, null, null));
    }
  }

  /** Prints a failure log entry in JSON format. */
  public static void mintFailedLog(
      String function, String args, long startTime, String message, String error) {
    if (mintEnv) {
      System.out.println(
          new MintLogger(
              function,
              args,
              System.currentTimeMillis() - startTime,
              FAILED,
              null,
              message,
              error));
    }
  }

  /** Prints a ignore log entry in JSON format. */
  public static void mintIgnoredLog(String function, String args, long startTime) {
    if (mintEnv) {
      System.out.println(
          new MintLogger(
              function, args, System.currentTimeMillis() - startTime, IGNORED, null, null, null));
    }
  }

  /** Read object content of the given url. */
  public static byte[] readObject(String urlString) throws Exception {
    Request.Builder requestBuilder = new Request.Builder();
    Request request = requestBuilder.url(HttpUrl.parse(urlString)).method("GET", null).build();
    OkHttpClient transport = newHttpClient();
    Response response = transport.newCall(request).execute();

    try {
      if (response.isSuccessful()) {
        return response.body().bytes();
      }

      String errorXml = response.body().string();
      throw new Exception(
          "failed to create object. Response: " + response + ", Response body: " + errorXml);
    } finally {
      response.close();
    }
  }

  /** Write data to given object url. */
  public static void writeObject(String urlString, byte[] dataBytes) throws Exception {
    Request.Builder requestBuilder = new Request.Builder();
    // Set header 'x-amz-acl' to 'bucket-owner-full-control', so objects created
    // anonymously, can be downloaded by bucket owner in AWS S3.
    Request request =
        requestBuilder
            .url(HttpUrl.parse(urlString))
            .method("PUT", RequestBody.create(dataBytes, null))
            .addHeader("x-amz-acl", "bucket-owner-full-control")
            .build();
    OkHttpClient transport = newHttpClient();
    Response response = transport.newCall(request).execute();

    try {
      if (!response.isSuccessful()) {
        String errorXml = response.body().string();
        throw new Exception(
            "failed to create object. Response: " + response + ", Response body: " + errorXml);
      }
    } finally {
      response.close();
    }
  }

  public static String getSha256Sum(InputStream stream, int len) throws Exception {
    MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

    // 16KiB buffer for optimization
    byte[] buf = new byte[16384];
    int bytesToRead = buf.length;
    int bytesRead = 0;
    int totalBytesRead = 0;
    while (totalBytesRead < len) {
      if ((len - totalBytesRead) < bytesToRead) {
        bytesToRead = len - totalBytesRead;
      }

      bytesRead = stream.read(buf, 0, bytesToRead);
      Assert.assertFalse(
          "data length mismatch. expected: " + len + ", got: " + totalBytesRead, bytesRead < 0);

      if (bytesRead > 0) {
        sha256Digest.update(buf, 0, bytesRead);
        totalBytesRead += bytesRead;
      }
    }

    return BaseEncoding.base16().encode(sha256Digest.digest()).toLowerCase(Locale.US);
  }

  public static void skipStream(InputStream stream, int len) throws Exception {
    // 16KiB buffer for optimization
    byte[] buf = new byte[16384];
    int bytesToRead = buf.length;
    int bytesRead = 0;
    int totalBytesRead = 0;
    while (totalBytesRead < len) {
      if ((len - totalBytesRead) < bytesToRead) {
        bytesToRead = len - totalBytesRead;
      }

      bytesRead = stream.read(buf, 0, bytesToRead);
      Assert.assertFalse(
          "insufficient data. expected: " + len + ", got: " + totalBytesRead, bytesRead < 0);
      if (bytesRead > 0) {
        totalBytesRead += bytesRead;
      }
    }
  }

  public static void handleException(String methodName, String args, long startTime, Exception e)
      throws Exception {
    if (e instanceof ErrorResponseException) {
      if (((ErrorResponseException) e).errorResponse().code().equals("NotImplemented")) {
        mintIgnoredLog(methodName, args, startTime);
        return;
      }
    }

    if (mintEnv) {
      mintFailedLog(
          methodName,
          args,
          startTime,
          null,
          e.toString() + " >>> " + Arrays.toString(e.getStackTrace()));
      if (isRunOnFail) {
        return;
      }
    } else {
      System.out.println("<FAILED> " + methodName + " " + ((args == null) ? "" : args));
    }

    throw e;
  }

  public static void testBucketApi(
      String methodName,
      String testTags,
      MakeBucketArgs args,
      boolean existCheck,
      boolean removeCheck)
      throws Exception {
    long startTime = System.currentTimeMillis();
    try {
      client.makeBucket(args);
      try {
        Assert.assertFalse(
            methodName + " failed after bucket creation",
            existCheck
                && !client.bucketExists(
                    HeadBucketArgs.builder()
                        .bucket(args.bucket())
                        .region(args.region())
                        .build()));
        if (removeCheck) {
          client.deleteBucket(
              DeleteBucketArgs.builder().bucket(args.bucket()).region(args.region()).build());
        }
        mintSuccessLog(methodName, null, startTime);
      } finally {
        if (!removeCheck) {
          client.deleteBucket(
              DeleteBucketArgs.builder().bucket(args.bucket()).region(args.region()).build());
        }
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testBucketApiCases(String methodName, boolean existCheck, boolean removeCheck)
      throws Exception {
    testBucketApi(
        methodName,
        "[basic check]",
        MakeBucketArgs.builder().bucket(getRandomName()).build(),
        existCheck,
        removeCheck);

    if (isQuickTest) {
      return;
    }

    testBucketApi(
        methodName,
        "[object lock]",
        MakeBucketArgs.builder().bucket(getRandomName()).objectLock(true).build(),
        existCheck,
        removeCheck);
    testBucketApi(
        methodName,
        "[name contains period]",
        MakeBucketArgs.builder().bucket(getRandomName() + ".withperiod").build(),
        existCheck,
        removeCheck);
  }

  public static void makeBucket() throws Exception {
    String methodName = "makeBucket()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testBucketApiCases(methodName, false, false);

    if (isQuickTest) {
      return;
    }

    if (!endpoint.contains(".amazonaws.com")) {
      mintIgnoredLog(methodName, "[region]", System.currentTimeMillis());
      mintIgnoredLog(methodName, "[region, object lock]", System.currentTimeMillis());
      return;
    }

    testBucketApi(
        methodName,
        "[region]",
        MakeBucketArgs.builder().bucket(getRandomName()).region("eu-west-1").build(),
        false,
        false);
    testBucketApi(
        methodName,
        "[region, object lock]",
        MakeBucketArgs.builder()
            .bucket(getRandomName())
            .region("eu-central-1")
            .objectLock(true)
            .build(),
        false,
        false);
  }

  public static void listBuckets() throws Exception {
    String methodName = "listBuckets()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    List<String> expectedBucketNames = new LinkedList<>();
    try {
      try {
        String bucketName = getRandomName();
        client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        expectedBucketNames.add(bucketName);

        bucketName = getRandomName();
        client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(true).build());
        expectedBucketNames.add(bucketName);

        bucketName = getRandomName() + ".withperiod";
        client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        expectedBucketNames.add(bucketName);

        List<String> bucketNames = new LinkedList<>();
        for (Bucket bucket : client.listBuckets()) {
          if (expectedBucketNames.contains(bucket.name())) {
            bucketNames.add(bucket.name());
          }
        }

        Assert.assertTrue(
            "bucket names differ; expected = " + expectedBucketNames + ", got = " + bucketNames,
            expectedBucketNames.containsAll(bucketNames));

        mintSuccessLog(methodName, null, startTime);
      } finally {
        for (String bucketName : expectedBucketNames) {
          client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
        }
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void bucketExists() throws Exception {
    String methodName = "bucketExists()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testBucketApiCases(methodName, true, false);
  }

  public static void deleteBucket() throws Exception {
    String methodName = "deleteBucket()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testBucketApiCases(methodName, false, true);
  }

  public static void putBucketVersioning() throws Exception {
    String methodName = "putBucketVersioning()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String name = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(name).build());
      try {
        client.putBucketVersioning(
            PutBucketVersioningArgs.builder()
                .bucket(name)
                .config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, null))
                .build());
        client.putBucketVersioning(
            PutBucketVersioningArgs.builder()
                .bucket(name)
                .config(new VersioningConfiguration(VersioningConfiguration.Status.SUSPENDED, null))
                .build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(name).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketVersioning() throws Exception {
    String methodName = "getBucketVersioning()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String name = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(name).build());
      try {
        VersioningConfiguration config =
            client.getBucketVersioning(GetBucketVersioningArgs.builder().bucket(name).build());
        Assert.assertEquals(
            "getBucketVersioning(); expected = \"\", got = " + config.status(),
            config.status(),
            VersioningConfiguration.Status.OFF);
        client.putBucketVersioning(
            PutBucketVersioningArgs.builder()
                .bucket(name)
                .config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, null))
                .build());
        config = client.getBucketVersioning(GetBucketVersioningArgs.builder().bucket(name).build());
        Assert.assertEquals(
            "getBucketVersioning(); expected = "
                + VersioningConfiguration.Status.ENABLED
                + ", got = "
                + config.status(),
            config.status(),
            VersioningConfiguration.Status.ENABLED);

        client.putBucketVersioning(
            PutBucketVersioningArgs.builder()
                .bucket(name)
                .config(new VersioningConfiguration(VersioningConfiguration.Status.SUSPENDED, null))
                .build());
        config = client.getBucketVersioning(GetBucketVersioningArgs.builder().bucket(name).build());
        Assert.assertEquals(
            "getBucketVersioning(); expected = "
                + VersioningConfiguration.Status.SUSPENDED
                + ", got = "
                + config.status(),
            config.status(),
            VersioningConfiguration.Status.SUSPENDED);
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(name).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void setup() throws Exception {
    long startTime = System.currentTimeMillis();

    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    } catch (Exception e) {
      handleException("makeBucket()", null, startTime, e);
    }

    try {
      client.makeBucket(
          MakeBucketArgs.builder().bucket(bucketNameWithLock).objectLock(true).build());
    } catch (Exception e) {
      if (e instanceof ErrorResponseException) {
        if (((ErrorResponseException) e).errorResponse().code().equals("NotImplemented")) {
          bucketNameWithLock = null;
          return;
        }
      }

      handleException("makeBucket()", "[object lock]", startTime, e);
    }
  }

  public static void teardown() throws Exception {
    long startTime = System.currentTimeMillis();
    try {
      if (bucketName != null) {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }

      if (bucketNameWithLock != null) {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketNameWithLock).build());
      }
    } catch (Exception e) {
      handleException("deleteBucket()", null, startTime, e);
    }
  }

  public static void testPostObject(String testTags, String filename, String contentType)
      throws Exception {
    String methodName = "postObject()";
    long startTime = System.currentTimeMillis();
    try {
      try {
        PostObjectArgs.Builder builder =
            PostObjectArgs.builder().bucket(bucketName).object(filename).filename(filename);
        if (contentType != null) {
          builder.contentType(contentType);
        }
        client.postObject(builder.build());
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        Files.delete(Paths.get(filename));
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(filename).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void postObject() throws Exception {
    String methodName = "postObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testPostObject("[single upload]", createFile1Kb(), null);

    if (isQuickTest) {
      return;
    }

    testPostObject("[multi-part upload]", createFile6Mb(), null);
    testPostObject("[custom content-type]", createFile1Kb(), customContentType);
  }

  public static void testPutObject(String testTags, PutObjectArgs args, String errorCode)
      throws Exception {
    String methodName = "putObject()";
    long startTime = System.currentTimeMillis();
    try {
      ObjectWriteResponse objectInfo = null;
      try {
        objectInfo = client.putObject(args);
      } catch (ErrorResponseException e) {
        if (errorCode == null || !e.errorResponse().code().equals(errorCode)) {
          throw e;
        }
      }
      if (args.retention() != null) {
        client.putObjectRetention(
            PutObjectRetentionArgs.builder()
                .bucket(args.bucket())
                .object(args.object())
                .config(new Retention())
                .bypassGovernanceMode(true)
                .build());
      }
      client.removeObject(
          RemoveObjectArgs.builder()
              .bucket(args.bucket())
              .object(args.object())
              .versionId(objectInfo != null ? objectInfo.versionId() : null)
              .build());

      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testThreadedPutObject() throws Exception {
    String methodName = "putObject()";
    String testTags = "[threaded]";
    long startTime = System.currentTimeMillis();
    try {
      int count = 7;
      Thread[] threads = new Thread[count];

      for (int i = 0; i < count; i++) {
        threads[i] = new Thread(new PutObjectRunnable(client, bucketName, createFile6Mb()));
      }

      for (int i = 0; i < count; i++) {
        threads[i].start();
      }

      // Waiting for threads to complete.
      for (int i = 0; i < count; i++) {
        threads[i].join();
      }

      // All threads are completed.
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void putObject() throws Exception {
    String methodName = "putObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testPutObject(
        "[single upload]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .contentType(customContentType)
            .build(),
        null);

    if (isQuickTest) {
      return;
    }

    testPutObject(
        "[multi-part upload]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(11 * MB), 11 * MB, -1)
            .contentType(customContentType)
            .build(),
        null);

    testPutObject(
        "[object name with path segments]",
        PutObjectArgs.builder().bucket(bucketName).object("path/to/" + getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .contentType(customContentType)
            .build(),
        null);

    testPutObject(
        "[zero sized object]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(0), 0, -1)
            .build(),
        null);

    testPutObject(
        "[object name ends with '/']",
        PutObjectArgs.builder().bucket(bucketName).object("path/to/" + getRandomName() + "/")
            .stream(new ContentInputStream(0), 0, -1)
            .contentType(customContentType)
            .build(),
        null);

    testPutObject(
        "[unknown stream size, single upload]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), -1, PutObjectArgs.MIN_MULTIPART_SIZE)
            .contentType(customContentType)
            .build(),
        null);

    testPutObject(
        "[unknown stream size, multi-part upload]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(11 * MB), -1, PutObjectArgs.MIN_MULTIPART_SIZE)
            .contentType(customContentType)
            .build(),
        null);

    Map<String, String> userMetadata = new HashMap<>();
    userMetadata.put("My-Project", "Project One");
    userMetadata.put("My-header1", "    a   b   c  ");
    userMetadata.put("My-Header2", "\"a   b   c\"");
    userMetadata.put("My-Unicode-Tag", "商品");

    testPutObject(
        "[user metadata]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .userMetadata(userMetadata)
            .build(),
        null);

    Map<String, String> headers = new HashMap<>();

    headers.put("X-Amz-Storage-Class", "REDUCED_REDUNDANCY");
    testPutObject(
        "[storage-class=REDUCED_REDUNDANCY]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .headers(headers)
            .build(),
        null);

    headers.put("X-Amz-Storage-Class", "STANDARD");
    testPutObject(
        "[storage-class=STANDARD]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .headers(headers)
            .build(),
        null);

    headers.put("X-Amz-Storage-Class", "INVALID");
    testPutObject(
        "[storage-class=INVALID negative case]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .headers(headers)
            .build(),
        "InvalidStorageClass");

    testPutObject(
        "[SSE-S3]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .contentType(customContentType)
            .sse(sseS3)
            .build(),
        null);

    if (bucketNameWithLock != null) {
      testPutObject(
          "[with retention]",
          PutObjectArgs.builder().bucket(bucketNameWithLock).object(getRandomName()).stream(
                  new ContentInputStream(1 * KB), 1 * KB, -1)
              .retention(
                  new Retention(RetentionMode.GOVERNANCE, ZonedDateTime.now(Time.UTC).plusDays(1)))
              .build(),
          null);
    }

    testThreadedPutObject();

    if (!isSecureEndpoint) {
      return;
    }

    testPutObject(
        "[SSE-C single upload]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .contentType(customContentType)
            .sse(ssec)
            .build(),
        null);

    testPutObject(
        "[SSE-C multi-part upload]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(11 * MB), 11 * MB, -1)
            .contentType(customContentType)
            .sse(ssec)
            .build(),
        null);

    if (sseKms == null) {
      mintIgnoredLog(methodName, null, System.currentTimeMillis());
      return;
    }

    testPutObject(
        "[SSE-KMS]",
        PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                new ContentInputStream(1 * KB), 1 * KB, -1)
            .contentType(customContentType)
            .sse(sseKms)
            .build(),
        null);
  }

  public static void testStatObject(
      String testTags, PutObjectArgs args, StatObjectResponse expectedStat) throws Exception {
    String methodName = "statObject()";
    long startTime = System.currentTimeMillis();
    try {
      client.putObject(args);
      try {
        ServerSideEncryptionCustomerKey ssec = null;
        if (args.sse() instanceof ServerSideEncryptionCustomerKey) {
          ssec = (ServerSideEncryptionCustomerKey) args.sse();
        }
        StatObjectResponse stat =
            client.statObject(
                StatObjectArgs.builder()
                    .bucket(args.bucket())
                    .object(args.object())
                    .ssec(ssec)
                    .build());

        Assert.assertEquals(
            "bucket name: expected = " + expectedStat.bucket() + ", got = " + stat.bucket(),
            expectedStat.bucket(),
            stat.bucket());

        Assert.assertEquals(
            "object name: expected = " + expectedStat.object() + ", got = " + stat.object(),
            expectedStat.object(),
            stat.object());

        Assert.assertEquals(
            "length: expected = " + expectedStat.size() + ", got = " + stat.size(),
            expectedStat.size(),
            stat.size());

        Assert.assertEquals(
            "content-type: expected = "
                + expectedStat.contentType()
                + ", got = "
                + stat.contentType(),
            expectedStat.contentType(),
            stat.contentType());

        for (String key : expectedStat.userMetadata().keySet()) {
          Assert.assertTrue("metadata " + key + " not found", stat.userMetadata().containsKey(key));
          Assert.assertEquals(
              "metadata "
                  + key
                  + " value: expected: "
                  + expectedStat.userMetadata().get(key)
                  + ", got: "
                  + stat.userMetadata().get(key),
              expectedStat.userMetadata().get(key),
              stat.userMetadata().get(key));
        }

        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void statObject() throws Exception {
    String methodName = "statObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    String objectName = getRandomName();

    PutObjectArgs.Builder builder =
        PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
            new ContentInputStream(1024), 1024, -1);
    Headers.Builder headersBuilder =
        new Headers.Builder()
            .add("Content-Type: application/octet-stream")
            .add("Content-Length: 1024")
            .add("Last-Modified", ZonedDateTime.now().format(Time.HTTP_HEADER_DATE_FORMAT));

    testStatObject(
        "[basic check]",
        builder.build(),
        new StatObjectResponse(headersBuilder.build(), bucketName, null, objectName));

    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", customContentType);
    Map<String, String> userMetadata = new HashMap<>();
    userMetadata.put("My-Project", "Project One");
    builder = builder.headers(headers).userMetadata(userMetadata);
    builder = builder.stream(new ContentInputStream(1024), 1024, -1);

    StatObjectResponse stat =
        new StatObjectResponse(
            headersBuilder
                .set("Content-Type", customContentType)
                .add("X-Amz-Meta-My-Project: Project One")
                .build(),
            bucketName,
            null,
            objectName);

    testStatObject("[user metadata]", builder.build(), stat);

    if (isQuickTest) {
      return;
    }

    builder = builder.stream(new ContentInputStream(1024), 1024, -1);
    testStatObject("[SSE-S3]", builder.sse(sseS3).build(), stat);

    if (!isSecureEndpoint) {
      mintIgnoredLog(methodName, "[SSE-C]", System.currentTimeMillis());
      return;
    }

    builder = builder.stream(new ContentInputStream(1024), 1024, -1);
    testStatObject("[SSE-C]", builder.sse(ssec).build(), stat);

    if (sseKms == null) {
      mintIgnoredLog(methodName, "[SSE-KMS]", System.currentTimeMillis());
      return;
    }

    builder = builder.stream(new ContentInputStream(1024), 1024, -1);
    testStatObject("[SSE-KMS]", builder.sse(sseKms).build(), stat);
  }

  public static void testGetObject(
      String testTags,
      long objectSize,
      ServerSideEncryption sse,
      GetObjectArgs args,
      int length,
      String sha256sum)
      throws Exception {
    String methodName = "getObject()";
    long startTime = System.currentTimeMillis();
    try {
      PutObjectArgs.Builder builder =
          PutObjectArgs.builder().bucket(args.bucket()).object(args.object()).stream(
              new ContentInputStream(objectSize), objectSize, -1);
      if (sse != null) {
        builder.sse(sse);
      }
      client.putObject(builder.build());

      try (InputStream is = client.getObject(args)) {
        String checksum = getSha256Sum(is, length);
        Assert.assertEquals(
            "checksum mismatch. expected: " + sha256sum + ", got: " + checksum,
            checksum,
            sha256sum);
      }
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    } finally {
      client.removeObject(
          RemoveObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
    }
  }

  public static void getObject() throws Exception {
    String methodName = "getObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testGetObject(
        "[single upload]",
        1 * KB,
        null,
        GetObjectArgs.builder().bucket(bucketName).object(getRandomName()).build(),
        1 * KB,
        getSha256Sum(new ContentInputStream(1 * KB), 1 * KB));

    if (isQuickTest) {
      return;
    }

    InputStream cis = new ContentInputStream(1 * KB);
    skipStream(cis, 1000);
    testGetObject(
        "[single upload, offset]",
        1 * KB,
        null,
        GetObjectArgs.builder().bucket(bucketName).object(getRandomName()).offset(1000L).build(),
        1 * KB - 1000,
        getSha256Sum(cis, 1 * KB - 1000));

    testGetObject(
        "[single upload, length]",
        1 * KB,
        null,
        GetObjectArgs.builder().bucket(bucketName).object(getRandomName()).length(256L).build(),
        256,
        getSha256Sum(new ContentInputStream(1 * KB), 256));

    cis = new ContentInputStream(1 * KB);
    skipStream(cis, 1000);
    testGetObject(
        "[single upload, offset, length]",
        1 * KB,
        null,
        GetObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .offset(1000L)
            .length(24L)
            .build(),
        24,
        getSha256Sum(cis, 24));

    cis = new ContentInputStream(1 * KB);
    skipStream(cis, 1000);
    testGetObject(
        "[single upload, offset, length beyond available]",
        1 * KB,
        null,
        GetObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .offset(1000L)
            .length(30L)
            .build(),
        24,
        getSha256Sum(cis, 24));

    testGetObject(
        "[multi-part upload]",
        6 * MB,
        null,
        GetObjectArgs.builder().bucket(bucketName).object(getRandomName()).build(),
        6 * MB,
        getSha256Sum(new ContentInputStream(6 * MB), 6 * MB));

    cis = new ContentInputStream(6 * MB);
    skipStream(cis, 1000);
    testGetObject(
        "[multi-part upload, offset, length]",
        6 * MB,
        null,
        GetObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .offset(1000L)
            .length(64 * 1024L)
            .build(),
        64 * KB,
        getSha256Sum(cis, 64 * 1024));

    cis = new ContentInputStream(0);
    testGetObject(
        "[zero sized object]",
        0,
        null,
        GetObjectArgs.builder().bucket(bucketName).object(getRandomName()).build(),
        0,
        getSha256Sum(cis, 0));

    if (!isSecureEndpoint) {
      return;
    }

    testGetObject(
        "[single upload, SSE-C]",
        1 * KB,
        ssec,
        GetObjectArgs.builder().bucket(bucketName).object(getRandomName()).ssec(ssec).build(),
        1 * KB,
        getSha256Sum(new ContentInputStream(1 * KB), 1 * KB));
  }

  public static void testDownloadObject(
      String testTags, int objectSize, ServerSideEncryption sse, DownloadObjectArgs args)
      throws Exception {
    String methodName = "downloadObject()";
    long startTime = System.currentTimeMillis();
    try {
      PutObjectArgs.Builder builder =
          PutObjectArgs.builder().bucket(args.bucket()).object(args.object()).stream(
              new ContentInputStream(objectSize), objectSize, -1);
      if (sse != null) {
        builder.sse(sse);
      }
      client.putObject(builder.build());
      client.downloadObject(args);
      Files.delete(Paths.get(args.filename()));
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    } finally {
      client.removeObject(
          RemoveObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
    }
  }

  public static void downloadObject() throws Exception {
    String methodName = "downloadObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    String objectName = getRandomName();
    testDownloadObject(
        "[single upload]",
        1 * KB,
        null,
        DownloadObjectArgs.builder()
            .bucket(bucketName)
            .object(objectName)
            .filename(objectName + ".downloaded")
            .build());

    if (isQuickTest) {
      return;
    }

    String baseName = getRandomName();
    objectName = "path/to/" + baseName;
    testDownloadObject(
        "[single upload with multiple path segments]",
        1 * KB,
        null,
        DownloadObjectArgs.builder()
            .bucket(bucketName)
            .object(objectName)
            .filename(baseName + ".downloaded")
            .build());

    if (!isSecureEndpoint) {
      return;
    }

    objectName = getRandomName();
    testDownloadObject(
        "[single upload, SSE-C]",
        1 * KB,
        ssec,
        DownloadObjectArgs.builder()
            .bucket(bucketName)
            .object(objectName)
            .ssec(ssec)
            .filename(objectName + ".downloaded")
            .build());
  }

  public static List<ObjectWriteResponse> createObjects(String bucketName, int count, int versions)
      throws Exception {
    List<ObjectWriteResponse> results = new LinkedList<>();
    for (int i = 0; i < count; i++) {
      String objectName = getRandomName();
      results.add(
          client.putObject(
              PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                      new ContentInputStream(1), 1, -1)
                  .build()));
      if (versions > 1) {
        for (int j = 0; j < versions - 1; j++) {
          results.add(
              client.putObject(
                  PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                          new ContentInputStream(1), 1, -1)
                      .build()));
        }
      }
    }

    return results;
  }

  public static void removeObjects(String bucketName, List<ObjectWriteResponse> results)
      throws Exception {
    List<DeleteObject> objects =
        results.stream()
            .map(
                result -> {
                  return new DeleteObject(result.object(), result.versionId());
                })
            .collect(Collectors.toList());
    for (Result<?> r :
        client.removeObjects(
            RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build())) {
      ignore(r.get());
    }
  }

  public static void testListObjects(
      String testTags, ListObjectsArgs args, int objCount, int versions) throws Exception {
    String methodName = "listObjects()";
    long startTime = System.currentTimeMillis();
    String bucketName = args.bucket();
    List<ObjectWriteResponse> results = null;
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        if (versions > 0) {
          client.putBucketVersioning(
              PutBucketVersioningArgs.builder()
                  .bucket(bucketName)
                  .config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, null))
                  .build());
        }

        results = createObjects(bucketName, objCount, versions);

        int i = 0;
        for (Result<?> r : client.listObjects(args)) {
          r.get();
          i++;
        }

        if (versions > 0) {
          objCount *= versions;
        }

        Assert.assertEquals("object count; expected=" + objCount + ", got=" + i, i, objCount);
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        if (results != null) {
          removeObjects(bucketName, results);
        }
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void listObjects() throws Exception {
    if (!mintEnv) {
      System.out.println("listObjects()");
    }

    testListObjects("[bucket]", ListObjectsArgs.builder().bucket(getRandomName()).build(), 3, 0);

    testListObjects(
        "[bucket, prefix]",
        ListObjectsArgs.builder().bucket(getRandomName()).prefix("ionoscloud").build(),
        3,
        0);

    testListObjects(
        "[bucket, prefix, recursive]",
        ListObjectsArgs.builder().bucket(getRandomName()).prefix("ionoscloud").recursive(true).build(),
        3,
        0);

    testListObjects(
        "[bucket, versions]",
        ListObjectsArgs.builder().bucket(getRandomName()).includeVersions(true).build(),
        3,
        2);

    if (isQuickTest) {
      return;
    }

    testListObjects(
        "[empty bucket]", ListObjectsArgs.builder().bucket(getRandomName()).build(), 0, 0);

    testListObjects(
        "[bucket, prefix, recursive, 1050 objects]",
        ListObjectsArgs.builder().bucket(getRandomName()).prefix("ionoscloud").recursive(true).build(),
        1050,
        0);

    testListObjects(
        "[bucket, apiVersion1]",
        ListObjectsArgs.builder().bucket(getRandomName()).useApiVersion1(true).build(),
        3,
        0);
  }

  public static void testRemoveObject(
      String testTags, ServerSideEncryption sse, RemoveObjectArgs args) throws Exception {
    String methodName = "removeObject()";
    long startTime = System.currentTimeMillis();
    try {
      PutObjectArgs.Builder builder =
          PutObjectArgs.builder().bucket(args.bucket()).object(args.object()).stream(
              new ContentInputStream(1), 1, -1);
      if (sse != null) {
        builder.sse(sse);
      }
      client.putObject(builder.build());
      client.removeObject(args);
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void removeObject() throws Exception {
    String methodName = "removeObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testRemoveObject(
        "[base check]",
        null,
        RemoveObjectArgs.builder().bucket(bucketName).object(getRandomName()).build());
    testRemoveObject(
        "[multiple path segments]",
        null,
        RemoveObjectArgs.builder().bucket(bucketName).object("path/to/" + getRandomName()).build());

    if (isQuickTest) {
      return;
    }

    testRemoveObject(
        "[SSE-S3]",
        sseS3,
        RemoveObjectArgs.builder().bucket(bucketName).object(getRandomName()).build());

    if (!isSecureEndpoint) {
      mintIgnoredLog(methodName, "[SSE-C]", System.currentTimeMillis());
      mintIgnoredLog(methodName, "[SSE-KMS]", System.currentTimeMillis());
      return;
    }

    testRemoveObject(
        "[SSE-C]",
        ssec,
        RemoveObjectArgs.builder().bucket(bucketName).object(getRandomName()).build());

    if (sseKms == null) {
      mintIgnoredLog(methodName, "[SSE-KMS]", System.currentTimeMillis());
      return;
    }

    testRemoveObject(
        "[SSE-KMS]",
        sseKms,
        RemoveObjectArgs.builder().bucket(bucketName).object(getRandomName()).build());
  }

  public static void testRemoveObjects(String testTags, List<ObjectWriteResponse> results)
      throws Exception {
    String methodName = "removeObjects()";
    long startTime = System.currentTimeMillis();
    try {
      removeObjects(bucketName, results);
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    } finally {
      removeObjects(bucketName, results);
    }
  }

  public static void removeObjects() throws Exception {
    String methodName = "removeObjects()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testRemoveObjects("[basic]", createObjects(bucketName, 3, 0));

    String testTags = "[3005 objects]";
    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    List<ObjectWriteResponse> results = new LinkedList<>();
    for (int i = 0; i < 3004; i++) {
      results.add(
          new ObjectWriteResponse(null, bucketName, null, objectName + "-" + i, null, null));
    }
    List<ObjectWriteResponse> existingObject = createObjects(bucketName, 1, 0);
    results.addAll(existingObject);
    testRemoveObjects(testTags, results);
    try {
      client.statObject(
          StatObjectArgs.builder()
              .bucket(bucketName)
              .object(existingObject.get(0).object())
              .build());
      handleException(
          methodName,
          testTags,
          startTime,
          new Exception("object " + existingObject.get(0).object() + " still exist"));
    } catch (ErrorResponseException e) {
      if (!e.errorResponse().code().equals("NoSuchKey")) {
        throw e;
      }
    }
  }

  public static void testGetPresignedUrl(GetPresignedObjectUrlArgs args, String expectedChecksum)
      throws Exception {
    String urlString = client.getPresignedObjectUrl(args);
    byte[] data = readObject(urlString);
    String checksum = getSha256Sum(new ByteArrayInputStream(data), data.length);
    Assert.assertEquals(
        "content checksum differs; expected = " + expectedChecksum + ", got = " + checksum,
        expectedChecksum,
        checksum);
  }

  public static void testGetPresignedObjectUrlForGet() throws Exception {
    String methodName = "getPresignedObjectUrl()";
    String testTags = null;
    long startTime = System.currentTimeMillis();
    try {
      String expectedChecksum = getSha256Sum(new ContentInputStream(1 * KB), 1 * KB);
      String objectName = getRandomName();
      client.putObject(
          PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                  new ContentInputStream(1 * KB), 1 * KB, -1)
              .build());

      try {
        testTags = "[GET]";
        testGetPresignedUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .build(),
            expectedChecksum);

        testTags = "[GET, expiry]";
        testGetPresignedUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(1, TimeUnit.DAYS)
                .build(),
            expectedChecksum);

        testTags = "[GET, expiry, query params]";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("response-content-type", "application/json");
        testGetPresignedUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(objectName)
                .expiry(1, TimeUnit.DAYS)
                .extraQueryParams(queryParams)
                .build(),
            expectedChecksum);

        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testPutPresignedUrl(
      String testTags, byte[] data, String expectedChecksum, GetPresignedObjectUrlArgs args)
      throws Exception {
    String methodName = "getPresignedObjectUrl()";
    long startTime = System.currentTimeMillis();
    try {
      String urlString = client.getPresignedObjectUrl(args);
      try {
        writeObject(urlString, data);
        InputStream is =
            client.getObject(
                GetObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
        data = readAllBytes(is);
        String checksum = getSha256Sum(new ByteArrayInputStream(data), data.length);
        Assert.assertEquals(
            "content checksum differs; expected = " + expectedChecksum + ", got = " + checksum,
            expectedChecksum,
            checksum);
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testGetPresignedObjectUrlForPut() throws Exception {
    byte[] data = "hello, world".getBytes(StandardCharsets.UTF_8);
    String expectedChecksum = getSha256Sum(new ByteArrayInputStream(data), data.length);
    String objectName = getRandomName();

    testPutPresignedUrl(
        "[PUT]",
        data,
        expectedChecksum,
        GetPresignedObjectUrlArgs.builder()
            .method(Method.PUT)
            .bucket(bucketName)
            .object(objectName)
            .build());

    testPutPresignedUrl(
        "[PUT, expiry]",
        data,
        expectedChecksum,
        GetPresignedObjectUrlArgs.builder()
            .method(Method.PUT)
            .bucket(bucketName)
            .object(objectName)
            .expiry(1, TimeUnit.DAYS)
            .build());
  }

  public static void getPresignedObjectUrl() throws Exception {
    if (!mintEnv) {
      System.out.println("getPresignedObjectUrl()");
    }

    testGetPresignedObjectUrlForGet();
    testGetPresignedObjectUrlForPut();
  }

  public static void getPresignedPostFormData() throws Exception {
    String methodName = "getPresignedPostFormData()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    try {
      String objectName = getRandomName();

      PostPolicy policy = new PostPolicy(bucketName, ZonedDateTime.now().plusDays(7));
      policy.addEqualsCondition("key", objectName);
      policy.addEqualsCondition("content-type", "image/png");
      policy.addContentLengthRangeCondition(1 * MB, 4 * MB);
      Map<String, String> formData = client.getPresignedPostFormData(policy);

      MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
      multipartBuilder.setType(MultipartBody.FORM);
      for (Map.Entry<String, String> entry : formData.entrySet()) {
        multipartBuilder.addFormDataPart(entry.getKey(), entry.getValue());
      }
      multipartBuilder.addFormDataPart("key", objectName);
      multipartBuilder.addFormDataPart("Content-Type", "image/png");
      multipartBuilder.addFormDataPart(
          "file",
          objectName,
          RequestBody.create(readAllBytes(new ContentInputStream(1 * MB)), null));

      Request.Builder requestBuilder = new Request.Builder();
      String urlString =
          client.getPresignedObjectUrl(
              GetPresignedObjectUrlArgs.builder()
                  .method(Method.GET)
                  .bucket(bucketName)
                  .object("x")
                  .build());
      urlString = urlString.split("\\?")[0]; // Remove query parameters.
      // remove last two characters to get clean url string of bucket.
      urlString = urlString.substring(0, urlString.length() - 2);
      Request request = requestBuilder.url(urlString).post(multipartBuilder.build()).build();
      OkHttpClient transport = newHttpClient();
      Response response = transport.newCall(request).execute();
      Assert.assertNotNull("no response from server", response);

      try {
        if (!response.isSuccessful()) {
          String errorXml = response.body().string();
          throw new Exception(
              "failed to upload object. Response: " + response + ", Error: " + errorXml);
        }
      } finally {
        response.close();
      }
      client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void testCopyObject(
      String testTags, ServerSideEncryption sse, CopyObjectArgs args, boolean negativeCase)
      throws Exception {
    String methodName = "copyObject()";
    long startTime = System.currentTimeMillis();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(args.source().bucket()).build());
      try {
        PutObjectArgs.Builder builder =
            PutObjectArgs.builder().bucket(args.source().bucket()).object(args.source().object())
                .stream(new ContentInputStream(1 * KB), 1 * KB, -1);
        if (sse != null) {
          builder.sse(sse);
        }
        client.putObject(builder.build());

        if (negativeCase) {
          try {
            client.copyObject(args);
          } catch (ErrorResponseException e) {
            if (!e.errorResponse().code().equals("PreconditionFailed")) {
              throw e;
            }
          }
        } else {
          client.copyObject(args);

          ServerSideEncryptionCustomerKey ssec = null;
          if (sse instanceof ServerSideEncryptionCustomerKey) {
            ssec = (ServerSideEncryptionCustomerKey) sse;
          }
          client.statObject(
              StatObjectArgs.builder()
                  .bucket(args.bucket())
                  .object(args.object())
                  .ssec(ssec)
                  .build());
        }
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder()
                .bucket(args.source().bucket())
                .object(args.source().object())
                .build());
        client.removeObject(
            RemoveObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
        client.deleteBucket(DeleteBucketArgs.builder().bucket(args.source().bucket()).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testCopyObjectMatchETag() throws Exception {
    String methodName = "copyObject()";
    String testTags = "[match etag]";
    long startTime = System.currentTimeMillis();
    String srcBucketName = getRandomName();
    String srcObjectName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(srcBucketName).build());
      try {
        ObjectWriteResponse result =
            client.putObject(
                PutObjectArgs.builder().bucket(srcBucketName).object(srcObjectName).stream(
                        new ContentInputStream(1 * KB), 1 * KB, -1)
                    .build());

        client.copyObject(
            CopyObjectArgs.builder()
                .bucket(bucketName)
                .object(srcObjectName + "-copy")
                .source(
                    CopySource.builder()
                        .bucket(srcBucketName)
                        .object(srcObjectName)
                        .matchETag(result.etag())
                        .build())
                .build());

        client.statObject(
            StatObjectArgs.builder().bucket(bucketName).object(srcObjectName + "-copy").build());

        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(srcBucketName).object(srcObjectName).build());
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(srcObjectName + "-copy").build());
        client.deleteBucket(DeleteBucketArgs.builder().bucket(srcBucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testCopyObjectMetadataReplace() throws Exception {
    String methodName = "copyObject()";
    String testTags = "[metadata replace]";
    long startTime = System.currentTimeMillis();
    String srcBucketName = getRandomName();
    String srcObjectName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(srcBucketName).build());
      try {
        client.putObject(
            PutObjectArgs.builder().bucket(srcBucketName).object(srcObjectName).stream(
                    new ContentInputStream(1 * KB), 1 * KB, -1)
                .build());

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", customContentType);
        headers.put("X-Amz-Meta-My-Project", "Project One");
        client.copyObject(
            CopyObjectArgs.builder()
                .bucket(bucketName)
                .object(srcObjectName + "-copy")
                .source(CopySource.builder().bucket(srcBucketName).object(srcObjectName).build())
                .headers(headers)
                .metadataDirective(Directive.REPLACE)
                .build());

        StatObjectResponse stat =
            client.statObject(
                StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(srcObjectName + "-copy")
                    .build());
        Assert.assertEquals(
            "content type differs. expected: " + customContentType + ", got: " + stat.contentType(),
            customContentType,
            stat.contentType());
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(srcBucketName).object(srcObjectName).build());
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(srcObjectName + "-copy").build());
        client.deleteBucket(DeleteBucketArgs.builder().bucket(srcBucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testCopyObjectEmptyMetadataReplace() throws Exception {
    String methodName = "copyObject()";
    String testTags = "[empty metadata replace]";
    long startTime = System.currentTimeMillis();
    String srcBucketName = getRandomName();
    String srcObjectName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(srcBucketName).build());
      try {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", customContentType);
        headers.put("X-Amz-Meta-My-Project", "Project One");
        client.putObject(
            PutObjectArgs.builder().bucket(srcBucketName).object(srcObjectName).stream(
                    new ContentInputStream(1 * KB), 1 * KB, -1)
                .headers(headers)
                .build());

        client.copyObject(
            CopyObjectArgs.builder()
                .bucket(bucketName)
                .object(srcObjectName + "-copy")
                .source(CopySource.builder().bucket(srcBucketName).object(srcObjectName).build())
                .metadataDirective(Directive.REPLACE)
                .build());

        StatObjectResponse stat =
            client.statObject(
                StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(srcObjectName + "-copy")
                    .build());
        Assert.assertFalse(
            "expected user metadata to be removed in new object",
            stat.userMetadata().containsKey("My-Project"));
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(srcBucketName).object(srcObjectName).build());
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(srcObjectName + "-copy").build());
        client.deleteBucket(DeleteBucketArgs.builder().bucket(srcBucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void copyObject() throws Exception {
    String methodName = "copyObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    String objectName = getRandomName();
    testCopyObject(
        "[basic check]",
        null,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .source(CopySource.builder().bucket(getRandomName()).object(objectName).build())
            .build(),
        false);

    if (isQuickTest) {
      return;
    }

    testCopyObject(
        "[negative match etag]",
        null,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .source(
                CopySource.builder()
                    .bucket(getRandomName())
                    .object(getRandomName())
                    .matchETag("invalid-etag")
                    .build())
            .build(),
        true);

    testCopyObjectMatchETag();

    testCopyObject(
        "[not match etag]",
        null,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .source(
                CopySource.builder()
                    .bucket(getRandomName())
                    .object(getRandomName())
                    .notMatchETag("not-etag-of-source-object")
                    .build())
            .build(),
        false);

    testCopyObject(
        "[modified since]",
        null,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .source(
                CopySource.builder()
                    .bucket(getRandomName())
                    .object(getRandomName())
                    .modifiedSince(ZonedDateTime.of(2015, 05, 3, 3, 10, 10, 0, Time.UTC))
                    .build())
            .build(),
        false);

    testCopyObject(
        "[negative unmodified since]",
        null,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .source(
                CopySource.builder()
                    .bucket(getRandomName())
                    .object(getRandomName())
                    .unmodifiedSince(ZonedDateTime.of(2015, 05, 3, 3, 10, 10, 0, Time.UTC))
                    .build())
            .build(),
        true);

    testCopyObjectMetadataReplace();
    testCopyObjectEmptyMetadataReplace();

    testCopyObject(
        "[SSE-S3]",
        sseS3,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sse(sseS3)
            .source(CopySource.builder().bucket(getRandomName()).object(getRandomName()).build())
            .build(),
        false);

    if (!isSecureEndpoint) {
      mintIgnoredLog(methodName, "[SSE-C]", System.currentTimeMillis());
      mintIgnoredLog(methodName, "[SSE-KMS]", System.currentTimeMillis());
      return;
    }

    testCopyObject(
        "[SSE-C]",
        ssec,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sse(ssec)
            .source(
                CopySource.builder()
                    .bucket(getRandomName())
                    .object(getRandomName())
                    .ssec(ssec)
                    .build())
            .build(),
        false);

    if (sseKms == null) {
      mintIgnoredLog(methodName, "[SSE-KMS]", System.currentTimeMillis());
      return;
    }

    testCopyObject(
        "[SSE-KMS]",
        sseKms,
        CopyObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sse(sseKms)
            .source(CopySource.builder().bucket(getRandomName()).object(getRandomName()).build())
            .build(),
        false);
  }

  public static void testComposeObject(String testTags, ComposeObjectArgs args) throws Exception {
    String methodName = "composeObject()";
    long startTime = System.currentTimeMillis();
    try {
      client.composeObject(args);
      client.removeObject(
          RemoveObjectArgs.builder().bucket(args.bucket()).object(args.object()).build());
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static List<ComposeSource> createComposeSourceList(ComposeSource... sources) {
    return Arrays.asList(sources);
  }

  public static void composeObjectTests(String object1Mb, String object6Mb, String object6MbSsec)
      throws Exception {
    testComposeObject(
        "[single source]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder().bucket(bucketName).object(object1Mb).build()))
            .build());

    testComposeObject(
        "[single source with offset]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder()
                        .bucket(bucketName)
                        .object(object1Mb)
                        .offset(2L * KB)
                        .build()))
            .build());

    testComposeObject(
        "[single source with offset and length]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder()
                        .bucket(bucketName)
                        .object(object1Mb)
                        .offset(2L * KB)
                        .length(5L * KB)
                        .build()))
            .build());

    testComposeObject(
        "[single multipart source]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder().bucket(bucketName).object(object6Mb).build()))
            .build());

    testComposeObject(
        "[two multipart source]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder().bucket(bucketName).object(object6Mb).build(),
                    ComposeSource.builder().bucket(bucketName).object(object6Mb).build()))
            .build());

    testComposeObject(
        "[two multipart sources with offset and length]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder()
                        .bucket(bucketName)
                        .object(object6Mb)
                        .offset(10L)
                        .length(6291436L)
                        .build(),
                    ComposeSource.builder().bucket(bucketName).object(object6Mb).build()))
            .build());

    if (isQuickTest) {
      return;
    }

    if (!isSecureEndpoint) {
      return;
    }

    testComposeObject(
        "[two SSE-C multipart sources]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sse(ssec)
            .sources(
                createComposeSourceList(
                    ComposeSource.builder()
                        .bucket(bucketName)
                        .object(object6MbSsec)
                        .ssec(ssec)
                        .build(),
                    ComposeSource.builder()
                        .bucket(bucketName)
                        .object(object6MbSsec)
                        .ssec(ssec)
                        .build()))
            .build());

    testComposeObject(
        "[two multipart sources with one SSE-C]",
        ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(getRandomName())
            .sources(
                createComposeSourceList(
                    ComposeSource.builder()
                        .bucket(bucketName)
                        .object(object6MbSsec)
                        .ssec(ssec)
                        .build(),
                    ComposeSource.builder().bucket(bucketName).object(object6Mb).build()))
            .build());
  }

  public static void composeObject() throws Exception {
    String methodName = "composeObject()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    List<ObjectWriteResponse> createdObjects = new LinkedList<>();

    try {
      String object1Mb = null;
      String object6Mb = null;
      String object6MbSsec = null;
      try {
        ObjectWriteResponse response;
        response =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                        new ContentInputStream(1 * MB), 1 * MB, -1)
                    .build());
        createdObjects.add(response);
        object1Mb = response.object();

        response =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                        new ContentInputStream(6 * MB), 6 * MB, -1)
                    .build());
        createdObjects.add(response);
        object6Mb = response.object();

        if (isSecureEndpoint) {
          response =
              client.putObject(
                  PutObjectArgs.builder().bucket(bucketName).object(getRandomName()).stream(
                          new ContentInputStream(6 * MB), 6 * MB, -1)
                      .sse(ssec)
                      .build());
          createdObjects.add(response);
          object6MbSsec = response.object();
        }
      } catch (Exception e) {
        handleException(methodName, null, startTime, e);
      }

      composeObjectTests(object1Mb, object6Mb, object6MbSsec);
    } finally {
      removeObjects(bucketName, createdObjects);
    }
  }

  public static void checkObjectLegalHold(String bucketName, String objectName, boolean enableCheck)
      throws Exception {
    if (enableCheck) {
      client.putObjectLegalHold(
          PutObjectLegalHoldArgs.builder().bucket(bucketName).object(objectName).build());
    } else {
      client.disableObjectLegalHold(
          DisableObjectLegalHoldArgs.builder().bucket(bucketName).object(objectName).build());
    }

    boolean result =
        client.getObjectLegalHold(
            GetObjectLegalHoldArgs.builder().bucket(bucketName).object(objectName).build());
    Assert.assertEquals(
        "object legal hold: expected: " + enableCheck + ", got: " + result, result, enableCheck);
  }

  public static void putObjectLegalHold() throws Exception {
    if (bucketNameWithLock == null) return;

    String methodName = "putObjectLegalHold()";
    if (!mintEnv) {
      System.out.println(methodName);
    }
    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    ObjectWriteResponse objectInfo = null;
    try {
      try {
        objectInfo =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketNameWithLock).object(objectName).stream(
                        new ContentInputStream(1 * KB), 1 * KB, -1)
                    .build());

        checkObjectLegalHold(bucketNameWithLock, objectName, true);
        client.disableObjectLegalHold(
            DisableObjectLegalHoldArgs.builder()
                .bucket(bucketNameWithLock)
                .object(objectName)
                .build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        if (objectInfo != null) {
          client.removeObject(
              RemoveObjectArgs.builder()
                  .bucket(bucketNameWithLock)
                  .object(objectName)
                  .versionId(objectInfo.versionId())
                  .build());
        }
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void disableObjectLegalHold() throws Exception {
    if (bucketNameWithLock == null) return;

    String methodName = "disableObjectLegalHold()";
    if (!mintEnv) {
      System.out.println(methodName);
    }
    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    ObjectWriteResponse objectInfo = null;
    try {
      try {
        objectInfo =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketNameWithLock).object(objectName).stream(
                        new ContentInputStream(1 * KB), 1 * KB, -1)
                    .build());

        checkObjectLegalHold(bucketNameWithLock, objectName, false);
        client.putObjectLegalHold(
            PutObjectLegalHoldArgs.builder()
                .bucket(bucketNameWithLock)
                .object(objectName)
                .build());
        checkObjectLegalHold(bucketNameWithLock, objectName, false);
        mintSuccessLog(methodName, null, startTime);
      } finally {
        if (objectInfo != null) {
          client.removeObject(
              RemoveObjectArgs.builder()
                  .bucket(bucketNameWithLock)
                  .object(objectName)
                  .versionId(objectInfo.versionId())
                  .build());
        }
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getObjectLegalHold() throws Exception {
    if (bucketNameWithLock == null) return;

    String methodName = "getObjectLegalHold()";
    if (!mintEnv) {
      System.out.println(methodName);
    }
    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    ObjectWriteResponse objectInfo = null;
    try {
      try {
        objectInfo =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketNameWithLock).object(objectName).stream(
                        new ContentInputStream(1 * KB), 1 * KB, -1)
                    .build());

        boolean result =
            client.getObjectLegalHold(
                GetObjectLegalHoldArgs.builder()
                    .bucket(bucketNameWithLock)
                    .object(objectName)
                    .build());
        Assert.assertFalse("object legal hold: expected: false, got: " + result, result);
        checkObjectLegalHold(bucketNameWithLock, objectName, true);
        checkObjectLegalHold(bucketNameWithLock, objectName, false);
        mintSuccessLog(methodName, null, startTime);
      } finally {
        if (objectInfo != null) {
          client.removeObject(
              RemoveObjectArgs.builder()
                  .bucket(bucketNameWithLock)
                  .object(objectName)
                  .versionId(objectInfo.versionId())
                  .build());
        }
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void putObjectLockConfiguration() throws Exception {
    String methodName = "putObjectLockConfiguration()";
    String testTags = "[COMPLIANCE, 10 days]";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(true).build());
      try {
        ObjectLockConfiguration config =
            new ObjectLockConfiguration(RetentionMode.COMPLIANCE, new RetentionDurationDays(10));
        client.putObjectLockConfiguration(
            PutObjectLockConfigurationArgs.builder().bucket(bucketName).config(config).build());
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
      mintSuccessLog(methodName, testTags, startTime);
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void testGetObjectLockConfiguration(
      String bucketName, RetentionMode mode, RetentionDuration duration) throws Exception {
    ObjectLockConfiguration expectedConfig = new ObjectLockConfiguration(mode, duration);
    client.putObjectLockConfiguration(
        PutObjectLockConfigurationArgs.builder().bucket(bucketName).config(expectedConfig).build());
    ObjectLockConfiguration config =
        client.getObjectLockConfiguration(
            GetObjectLockConfigurationArgs.builder().bucket(bucketName).build());
    Assert.assertEquals(
        "retention mode: expected: " + expectedConfig.mode() + ", got: " + config.mode(),
        config.mode(),
        expectedConfig.mode());
    Assert.assertFalse(
        "retention duration: " + expectedConfig.duration() + ", got: " + config.duration(),
        config.duration().unit() != expectedConfig.duration().unit()
            || config.duration().duration() != expectedConfig.duration().duration());
  }

  public static void getObjectLockConfiguration() throws Exception {
    String methodName = "getObjectLockConfiguration()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(true).build());
      try {
        testGetObjectLockConfiguration(
            bucketName, RetentionMode.COMPLIANCE, new RetentionDurationDays(10));
        testGetObjectLockConfiguration(
            bucketName, RetentionMode.GOVERNANCE, new RetentionDurationYears(1));
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }

      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteObjectLockConfiguration() throws Exception {
    String methodName = "deleteObjectLockConfiguration()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).objectLock(true).build());
      try {
        client.deleteObjectLockConfiguration(
            DeleteObjectLockConfigurationArgs.builder().bucket(bucketName).build());
        ObjectLockConfiguration config =
            new ObjectLockConfiguration(RetentionMode.COMPLIANCE, new RetentionDurationDays(10));
        client.putObjectLockConfiguration(
            PutObjectLockConfigurationArgs.builder().bucket(bucketName).config(config).build());
        client.deleteObjectLockConfiguration(
            DeleteObjectLockConfigurationArgs.builder().bucket(bucketName).build());
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void putObjectRetention() throws Exception {
    if (bucketNameWithLock == null) return;

    String methodName = "putObjectRetention()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    ObjectWriteResponse objectInfo = null;
    try {
      try {
        objectInfo =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketNameWithLock).object(objectName).stream(
                        new ContentInputStream(1 * KB), 1 * KB, -1)
                    .build());

        client.putObjectRetention(
            PutObjectRetentionArgs.builder()
                .bucket(bucketNameWithLock)
                .object(objectName)
                .config(
                    new Retention(
                        RetentionMode.GOVERNANCE, ZonedDateTime.now(Time.UTC).plusDays(1)))
                .build());

        client.putObjectRetention(
            PutObjectRetentionArgs.builder()
                .bucket(bucketNameWithLock)
                .object(objectName)
                .config(new Retention())
                .bypassGovernanceMode(true)
                .build());
      } finally {
        if (objectInfo != null) {
          client.removeObject(
              RemoveObjectArgs.builder()
                  .bucket(bucketNameWithLock)
                  .object(objectName)
                  .versionId(objectInfo.versionId())
                  .build());
        }
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void testGetObjectRetention(PutObjectRetentionArgs args) throws Exception {
    client.putObjectRetention(args);
    Retention config =
        client.getObjectRetention(
            GetObjectRetentionArgs.builder().bucket(args.bucket()).object(args.object()).build());

    if (args.config().mode() == null) {
      Assert.assertFalse(
          "retention mode: expected: <null>, got: " + config.mode(),
          config != null && config.mode() != null);
    } else {
      Assert.assertEquals(
          "retention mode: expected: " + args.config().mode() + ", got: " + config.mode(),
          args.config().mode(),
          config.mode());
    }

    ZonedDateTime expectedDate = args.config().retainUntilDate();
    ZonedDateTime date = (config == null) ? null : config.retainUntilDate();

    if (expectedDate == null) {
      Assert.assertNull("retention retain-until-date: expected: <null>, got: " + date, date);
    } else {
      Assert.assertEquals(
          "retention retain-until-date: expected: "
              + expectedDate.withNano(0)
              + ", got: "
              + date.withNano(0),
          date.withNano(0),
          expectedDate.withNano(0));
    }
  }

  public static void getObjectRetention() throws Exception {
    if (bucketNameWithLock == null) return;

    String methodName = "getObjectRetention()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    ObjectWriteResponse objectInfo = null;
    try {
      try {
        objectInfo =
            client.putObject(
                PutObjectArgs.builder().bucket(bucketNameWithLock).object(objectName).stream(
                        new ContentInputStream(1 * KB), 1 * KB, -1)
                    .build());

        testGetObjectRetention(
            PutObjectRetentionArgs.builder()
                .bucket(bucketNameWithLock)
                .object(objectName)
                .config(
                    new Retention(
                        RetentionMode.GOVERNANCE, ZonedDateTime.now(Time.UTC).plusDays(3)))
                .build());

        // Check shortening retention until period
        testGetObjectRetention(
            PutObjectRetentionArgs.builder()
                .bucket(bucketNameWithLock)
                .object(objectName)
                .config(
                    new Retention(
                        RetentionMode.GOVERNANCE, ZonedDateTime.now(Time.UTC).plusDays(1)))
                .bypassGovernanceMode(true)
                .build());

        // Check empty retention.
        // Enable below test when the server release has a fix.
        // testGetObjectRetention(
        //     PutObjectRetentionArgs.builder()
        //         .bucket(bucketNameWithLock)
        //         .object(objectName)
        //         .config(new Retention())
        //         .bypassGovernanceMode(true)
        //         .build());
      } finally {
        if (objectInfo != null) {
          client.removeObject(
              RemoveObjectArgs.builder()
                  .bucket(bucketNameWithLock)
                  .object(objectName)
                  .versionId(objectInfo.versionId())
                  .bypassGovernanceMode(true)
                  .build());
        }
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketPolicy() throws Exception {
    String methodName = "getBucketPolicy()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        String config =
            client.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
        Assert.assertTrue("policy: expected: \"\", got: " + config, config.isEmpty());
        String policy =
            "{'Version':'2012-10-17','Statement':[{'Action':['s3:GetObject'],'Effect':'Allow',"
                + "'Principal':{'AWS':['*']},'Resource':['arn:aws:s3:::"
                + bucketName
                + "/myobject*'],'Sid':''}]}";
        policy = policy.replaceAll("'", "\"");
        client.putBucketPolicy(
            PutBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
        client.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void putBucketPolicy() throws Exception {
    String methodName = "putBucketPolicy()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        String policy =
            "{'Version':'2012-10-17','Statement':[{'Action':['s3:GetObject'],'Effect':'Allow',"
                + "'Principal':{'AWS':['*']},'Resource':['arn:aws:s3:::"
                + bucketName
                + "/myobject*'],'Sid':''}]}";
        policy = policy.replaceAll("'", "\"");
        client.putBucketPolicy(
            PutBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteBucketPolicy() throws Exception {
    String methodName = "deleteBucketPolicy()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        client.deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucket(bucketName).build());

        String policy =
            "{'Version':'2012-10-17','Statement':[{'Action':['s3:GetObject'],'Effect':'Allow',"
                + "'Principal':{'AWS':['*']},'Resource':['arn:aws:s3:::"
                + bucketName
                + "/myobject*'],'Sid':''}]}";
        policy = policy.replaceAll("'", "\"");
        client.putBucketPolicy(
            PutBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
        client.deleteBucketPolicy(DeleteBucketPolicyArgs.builder().bucket(bucketName).build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void testPutBucketLifecycle(String bucketName, LifecycleRule... rules)
      throws Exception {
    LifecycleConfiguration config = new LifecycleConfiguration(Arrays.asList(rules));
    client.putBucketLifecycle(
        PutBucketLifecycleArgs.builder().bucket(bucketName).config(config).build());
  }

  public static void putBucketLifecycle() throws Exception {
    String methodName = "putBucketLifecycle()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        testPutBucketLifecycle(
            bucketName,
            new LifecycleRule(
                Status.ENABLED,
                null,
                new Expiration((ZonedDateTime) null, 365, null),
                new RuleFilter("logs/"),
                "rule2",
                null,
                null,
                null));
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteBucketLifecycle() throws Exception {
    String methodName = "deleteBucketLifecycle()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        client.deleteBucketLifecycle(
            DeleteBucketLifecycleArgs.builder().bucket(bucketName).build());
        testPutBucketLifecycle(
            bucketName,
            new LifecycleRule(
                Status.ENABLED,
                null,
                new Expiration((ZonedDateTime) null, 365, null),
                new RuleFilter("logs/"),
                "rule2",
                null,
                null,
                null));
        client.deleteBucketLifecycle(
            DeleteBucketLifecycleArgs.builder().bucket(bucketName).build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketLifecycle() throws Exception {
    String methodName = "getBucketLifecycle()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        LifecycleConfiguration config =
            client.getBucketLifecycle(GetBucketLifecycleArgs.builder().bucket(bucketName).build());
        Assert.assertNull("config: expected: <null>, got: <non-null>", config);
        testPutBucketLifecycle(
            bucketName,
            new LifecycleRule(
                Status.ENABLED,
                null,
                new Expiration((ZonedDateTime) null, 365, null),
                new RuleFilter("logs/"),
                "rule2",
                null,
                null,
                null));
        config =
            client.getBucketLifecycle(GetBucketLifecycleArgs.builder().bucket(bucketName).build());
        Assert.assertNotNull("config: expected: <non-null>, got: <null>", config);
        List<LifecycleRule> rules = config.rules();
        Assert.assertEquals(
            "config.rules().size(): expected: 1, got: " + config.rules().size(),
            1,
            config.rules().size());
        LifecycleRule rule = rules.get(0);
        Assert.assertEquals(
            "rule.status(): expected: " + Status.ENABLED + ", got: " + rule.status(),
            rule.status(),
            Status.ENABLED);
        Assert.assertNotNull(
            "rule.expiration(): expected: <non-null>, got: <null>", rule.expiration());
        Assert.assertEquals(
            "rule.expiration().days(): expected: 365, got: " + rule.expiration().days(),
            rule.expiration().days(),
            Integer.valueOf(365));
        Assert.assertNotNull("rule.filter(): expected: <non-null>, got: <null>", rule.filter());
        Assert.assertEquals(
            "rule.filter().prefix(): expected: logs/, got: " + rule.filter().prefix(),
            "logs/",
            rule.filter().prefix());
        Assert.assertEquals("rule.id(): expected: rule2, got: " + rule.id(), "rule2", rule.id());

        testPutBucketLifecycle(
            bucketName,
            new LifecycleRule(
                Status.ENABLED,
                null,
                new Expiration((ZonedDateTime) null, 365, null),
                new RuleFilter(""),
                null,
                null,
                null,
                null));
        config =
            client.getBucketLifecycle(GetBucketLifecycleArgs.builder().bucket(bucketName).build());
        Assert.assertNotNull("config: expected: <non-null>, got: <null>", config);
        Assert.assertEquals(
            "config.rules().size(): expected: 1, got: " + config.rules().size(),
            config.rules().size(),
            1);
        Assert.assertNotNull(
            "rule.filter(): expected: <non-null>, got: <null>", config.rules().get(0).filter());
        Assert.assertEquals(
            "rule.filter().prefix(): expected: <empty>, got: "
                + config.rules().get(0).filter().prefix(),
            "",
            config.rules().get(0).filter().prefix());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void setBucketNotification() throws Exception {
    String methodName = "setBucketNotification()";
    long startTime = System.currentTimeMillis();
    if (sqsArn == null) {
      mintIgnoredLog(methodName, null, startTime);
      return;
    }

    if (!mintEnv) {
      System.out.println(methodName);
    }

    try {
      String bucketName = getRandomName();
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).region(region).build());
      try {
        List<EventType> eventList = new LinkedList<>();
        eventList.add(EventType.OBJECT_CREATED_PUT);
        eventList.add(EventType.OBJECT_CREATED_COPY);
        QueueConfiguration queueConfig = new QueueConfiguration();
        queueConfig.setQueue(sqsArn);
        queueConfig.setEvents(eventList);
        queueConfig.setPrefixRule("images");
        queueConfig.setSuffixRule("pg");

        List<QueueConfiguration> queueConfigList = new LinkedList<>();
        queueConfigList.add(queueConfig);

        NotificationConfiguration config = new NotificationConfiguration();
        config.setQueueConfigurationList(queueConfigList);

        client.setBucketNotification(
            SetBucketNotificationArgs.builder().bucket(bucketName).config(config).build());
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketNotification() throws Exception {
    String methodName = "getBucketNotification()";
    long startTime = System.currentTimeMillis();
    if (sqsArn == null) {
      mintIgnoredLog(methodName, null, startTime);
      return;
    }

    if (!mintEnv) {
      System.out.println(methodName);
    }

    try {
      String bucketName = getRandomName();
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).region(region).build());
      try {
        List<EventType> eventList = new LinkedList<>();
        eventList.add(EventType.OBJECT_CREATED_PUT);
        QueueConfiguration queueConfig = new QueueConfiguration();
        queueConfig.setQueue(sqsArn);
        queueConfig.setEvents(eventList);

        List<QueueConfiguration> queueConfigList = new LinkedList<>();
        queueConfigList.add(queueConfig);

        NotificationConfiguration expectedConfig = new NotificationConfiguration();
        expectedConfig.setQueueConfigurationList(queueConfigList);

        client.setBucketNotification(
            SetBucketNotificationArgs.builder().bucket(bucketName).config(expectedConfig).build());

        NotificationConfiguration config =
            client.getBucketNotification(
                GetBucketNotificationArgs.builder().bucket(bucketName).build());

        if (config.queueConfigurationList().size() != 1
            || !sqsArn.equals(config.queueConfigurationList().get(0).queue())
            || config.queueConfigurationList().get(0).events().size() != 1
            || config.queueConfigurationList().get(0).events().get(0)
                != EventType.OBJECT_CREATED_PUT) {
          System.out.println(
              "config: expected: " + Xml.marshal(expectedConfig) + ", got: " + Xml.marshal(config));
        }
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteBucketNotification() throws Exception {
    String methodName = "deleteBucketNotification()";
    long startTime = System.currentTimeMillis();
    if (sqsArn == null) {
      mintIgnoredLog(methodName, null, startTime);
      return;
    }

    if (!mintEnv) {
      System.out.println(methodName);
    }

    try {
      String bucketName = getRandomName();
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).region(region).build());
      try {
        List<EventType> eventList = new LinkedList<>();
        eventList.add(EventType.OBJECT_CREATED_PUT);
        eventList.add(EventType.OBJECT_CREATED_COPY);
        QueueConfiguration queueConfig = new QueueConfiguration();
        queueConfig.setQueue(sqsArn);
        queueConfig.setEvents(eventList);
        queueConfig.setPrefixRule("images");
        queueConfig.setSuffixRule("pg");

        List<QueueConfiguration> queueConfigList = new LinkedList<>();
        queueConfigList.add(queueConfig);

        NotificationConfiguration config = new NotificationConfiguration();
        config.setQueueConfigurationList(queueConfigList);

        client.setBucketNotification(
            SetBucketNotificationArgs.builder().bucket(bucketName).config(config).build());

        client.deleteBucketNotification(
            DeleteBucketNotificationArgs.builder().bucket(bucketName).build());

        config =
            client.getBucketNotification(
                GetBucketNotificationArgs.builder().bucket(bucketName).build());
        if (config.queueConfigurationList().size() != 0) {
          System.out.println("config: expected: <empty>, got: " + Xml.marshal(config));
        }
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void listenBucketNotification() throws Exception {
    String methodName = "listenBucketNotification()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String file = createFile1Kb();
    String bucketName = getRandomName();
    CloseableIterator<Result<NotificationRecords>> ci = null;
    String mintArgs =
        "prefix=prefix, suffix=suffix, events={\"s3:ObjectCreated:*\", \"s3:ObjectAccessed:*\"}";
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).region(region).build());

      String[] events = {"s3:ObjectCreated:*", "s3:ObjectAccessed:*"};
      ci =
          client.listenBucketNotification(
              ListenBucketNotificationArgs.builder()
                  .bucket(bucketName)
                  .prefix("prefix")
                  .suffix("suffix")
                  .events(events)
                  .build());

      client.putObject(
          PutObjectArgs.builder().bucket(bucketName).object("prefix-random-suffix").stream(
                  new ContentInputStream(1 * KB), 1 * KB, -1)
              .build());

      while (ci.hasNext()) {
        NotificationRecords records = ci.next().get();
        if (records.events().size() == 0) {
          continue;
        }

        boolean found = false;
        for (Event event : records.events()) {
          if (event.objectName().equals("prefix-random-suffix")) {
            found = true;
            break;
          }
        }

        if (found) {
          break;
        }
      }

      mintSuccessLog(methodName, mintArgs, startTime);
    } catch (Exception e) {
      handleException(methodName, mintArgs, startTime, e);
    } finally {
      if (ci != null) {
        ci.close();
      }

      Files.delete(Paths.get(file));
      client.removeObject(
          RemoveObjectArgs.builder().bucket(bucketName).object("prefix-random-suffix").build());
      client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
    }
  }

  public static void selectObjectContent() throws Exception {
    String methodName = "selectObjectContent()";
    String sqlExpression = "select * from S3Object";
    String testArgs = "[sqlExpression: " + sqlExpression + ", requestProgress: true]";

    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    SelectResponseStream responseStream = null;
    try {
      String expectedResult =
          "1997,Ford,E350,\"ac, abs, moon\",3000.00\n"
              + "1999,Chevy,\"Venture \"\"Extended Edition\"\"\",,4900.00\n"
              + "1999,Chevy,\"Venture \"\"Extended Edition, Very Large\"\"\",,5000.00\n"
              + "1996,Jeep,Grand Cherokee,\"MUST SELL!\n"
              + "air, moon roof, loaded\",4799.00\n";
      byte[] data =
          ("Year,Make,Model,Description,Price\n" + expectedResult).getBytes(StandardCharsets.UTF_8);
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      client.putObject(
          PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                  bais, data.length, -1)
              .build());

      InputSerialization is =
          new InputSerialization(null, false, null, null, FileHeaderInfo.USE, null, null, null);
      OutputSerialization os =
          new OutputSerialization(null, null, null, QuoteFields.ASNEEDED, null);

      responseStream =
          client.selectObjectContent(
              SelectObjectContentArgs.builder()
                  .bucket(bucketName)
                  .object(objectName)
                  .sqlExpression(sqlExpression)
                  .inputSerialization(is)
                  .outputSerialization(os)
                  .requestProgress(true)
                  .build());

      String result = new String(readAllBytes(responseStream), StandardCharsets.UTF_8);
      Assert.assertEquals(
          "result mismatch; expected: " + expectedResult + ", got: " + result,
          result,
          expectedResult);

      Stats stats = responseStream.stats();
      Assert.assertNotNull("stats is null", stats);
      Assert.assertEquals(
          "stats.bytesScanned mismatch; expected: 258, got: " + stats.bytesScanned(),
          stats.bytesScanned(),
          256);
      Assert.assertEquals(
          "stats.bytesProcessed mismatch; expected: 258, got: " + stats.bytesProcessed(),
          stats.bytesProcessed(),
          256);
      Assert.assertEquals(
          "stats.bytesReturned mismatch; expected: 222, got: " + stats.bytesReturned(),
          stats.bytesReturned(),
          222);
      mintSuccessLog(methodName, testArgs, startTime);
    } catch (Exception e) {
      handleException(methodName, testArgs, startTime, e);
    } finally {
      if (responseStream != null) {
        responseStream.close();
      }
      client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
  }

  public static void putBucketEncryption() throws Exception {
    String methodName = "putBucketEncryption()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        client.putBucketEncryption(
            PutBucketEncryptionArgs.builder()
                .bucket(bucketName)
                .config(SseConfiguration.newConfigWithSseS3Rule())
                .build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketEncryption() throws Exception {
    String methodName = "getBucketEncryption()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        SseConfiguration config =
            client.getBucketEncryption(
                GetBucketEncryptionArgs.builder().bucket(bucketName).build());
        Assert.assertNull("rule: expected: <null>, got: <non-null>", config.rule());
        client.putBucketEncryption(
            PutBucketEncryptionArgs.builder()
                .bucket(bucketName)
                .config(SseConfiguration.newConfigWithSseS3Rule())
                .build());
        config =
            client.getBucketEncryption(
                GetBucketEncryptionArgs.builder().bucket(bucketName).build());
        Assert.assertNotNull("rule: expected: <non-null>, got: <null>", config.rule());
        Assert.assertEquals(
            "sse algorithm: expected: "
                + SseAlgorithm.AES256
                + ", got: "
                + config.rule().sseAlgorithm(),
            config.rule().sseAlgorithm(),
            SseAlgorithm.AES256);
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteBucketEncryption() throws Exception {
    String methodName = "deleteBucketEncryption()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        client.deleteBucketEncryption(
            DeleteBucketEncryptionArgs.builder().bucket(bucketName).build());

        client.putBucketEncryption(
            PutBucketEncryptionArgs.builder()
                .bucket(bucketName)
                .config(SseConfiguration.newConfigWithSseS3Rule())
                .build());
        client.deleteBucketEncryption(
            DeleteBucketEncryptionArgs.builder().bucket(bucketName).build());
        SseConfiguration config =
            client.getBucketEncryption(
                GetBucketEncryptionArgs.builder().bucket(bucketName).build());
        Assert.assertNull("rule: expected: <null>, got: <non-null>", config.rule());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void putBucketTagging() throws Exception {
    String methodName = "putBucketTagging()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        Map<String, String> map = new HashMap<>();
        map.put("Project", "Project One");
        map.put("User", "jsmith");
        client.putBucketTagging(PutBucketTaggingArgs.builder().bucket(bucketName).tags(map).build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketTagging() throws Exception {
    String methodName = "getBucketTagging()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        Map<String, String> map = new HashMap<>();
        Tags tags = client.getBucketTagging(GetBucketTaggingArgs.builder().bucket(bucketName).build());
        Assert.assertEquals("tags: expected: " + map + ", got: " + tags.get(), map, tags.get());

        map.put("Project", "Project One");
        map.put("User", "jsmith");
        client.putBucketTagging(PutBucketTaggingArgs.builder().bucket(bucketName).tags(map).build());
        tags = client.getBucketTagging(GetBucketTaggingArgs.builder().bucket(bucketName).build());
        Assert.assertEquals("tags: expected: " + map + ", got: " + tags.get(), map, tags.get());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteBucketTagging() throws Exception {
    String methodName = "deleteBucketTagging()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String bucketName = getRandomName();
    try {
      client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      try {
        client.deleteBucketTagging(DeleteBucketTaggingArgs.builder().bucket(bucketName).build());

        Map<String, String> map = new HashMap<>();
        map.put("Project", "Project One");
        map.put("User", "jsmith");
        client.putBucketTagging(PutBucketTaggingArgs.builder().bucket(bucketName).tags(map).build());
        client.deleteBucketTagging(DeleteBucketTaggingArgs.builder().bucket(bucketName).build());
        Tags tags = client.getBucketTagging(GetBucketTaggingArgs.builder().bucket(bucketName).build());
        Assert.assertTrue("tags: expected: <empty>" + ", got: " + tags.get(), tags.get().isEmpty());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.deleteBucket(DeleteBucketArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void putObjectTagging() throws Exception {
    String methodName = "putObjectTagging()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    try {
      try {
        client.putObject(
            PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                    new ContentInputStream(1 * KB), 1 * KB, -1)
                .build());
        Map<String, String> map = new HashMap<>();
        map.put("Project", "Project One");
        map.put("User", "jsmith");
        client.putObjectTagging(
            PutObjectTaggingArgs.builder().bucket(bucketName).object(objectName).tags(map).build());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getObjectTagging() throws Exception {
    String methodName = "getObjectTagging()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    try {
      try {
        client.putObject(
            PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                    new ContentInputStream(1 * KB), 1 * KB, -1)
                .build());
        Map<String, String> map = new HashMap<>();
        Tags tags =
            client.getObjectTagging(
                GetObjectTaggingArgs.builder().bucket(bucketName).object(objectName).build());
        Assert.assertEquals("tags: expected: " + map + ", got: " + tags.get(), map, tags.get());

        map.put("Project", "Project One");
        map.put("User", "jsmith");
        client.putObjectTagging(
            PutObjectTaggingArgs.builder().bucket(bucketName).object(objectName).tags(map).build());
        tags =
            client.getObjectTagging(
                GetObjectTaggingArgs.builder().bucket(bucketName).object(objectName).build());
        Assert.assertEquals("tags: expected: " + map + ", got: " + tags.get(), map, tags.get());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteObjectTags() throws Exception {
    String methodName = "deleteObjectTags()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    long startTime = System.currentTimeMillis();
    String objectName = getRandomName();
    try {
      try {
        client.putObject(
            PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                    new ContentInputStream(1 * KB), 1 * KB, -1)
                .build());
        client.deleteObjectTags(
            DeleteObjectTaggingArgs.builder().bucket(bucketName).object(objectName).build());

        Map<String, String> map = new HashMap<>();
        map.put("Project", "Project One");
        map.put("User", "jsmith");
        client.putObjectTagging(
            PutObjectTaggingArgs.builder().bucket(bucketName).object(objectName).tags(map).build());
        client.deleteObjectTags(
            DeleteObjectTaggingArgs.builder().bucket(bucketName).object(objectName).build());
        Tags tags =
            client.getObjectTagging(
                GetObjectTaggingArgs.builder().bucket(bucketName).object(objectName).build());
        Assert.assertTrue("tags: expected: <empty>, got: " + tags.get(), tags.get().isEmpty());
        mintSuccessLog(methodName, null, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
      }
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void setBucketReplication() throws Exception {
    String methodName = "setBucketReplication()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    if (replicationSrcBucket == null || replicationRole == null || replicationBucketArn == null) {
      mintIgnoredLog(methodName, "", System.currentTimeMillis());
      return;
    }

    long startTime = System.currentTimeMillis();
    try {
      Map<String, String> tags = new HashMap<>();
      tags.put("key1", "value1");
      tags.put("key2", "value2");

      ReplicationRule rule =
          new ReplicationRule(
              new DeleteMarkerReplication(Status.DISABLED),
              new ReplicationDestination(null, null, replicationBucketArn, null, null, null, null),
              null,
              new RuleFilter(new AndOperator("TaxDocs", tags)),
              "rule1",
              null,
              1,
              null,
              Status.ENABLED);

      List<ReplicationRule> rules = new LinkedList<>();
      rules.add(rule);

      ReplicationConfiguration config = new ReplicationConfiguration(replicationRole, rules);

      client.setBucketReplication(
          SetBucketReplicationArgs.builder().bucket(replicationSrcBucket).config(config).build());
      client.deleteBucketReplication(
          DeleteBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void getBucketReplication() throws Exception {
    String methodName = "getBucketReplication()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    if (replicationSrcBucket == null || replicationRole == null || replicationBucketArn == null) {
      mintIgnoredLog(methodName, "", System.currentTimeMillis());
      return;
    }

    long startTime = System.currentTimeMillis();
    try {
      ReplicationConfiguration config =
          client.getBucketReplication(
              GetBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());
      Assert.assertNull("config: expected: <null>, got: <non-null>", config);

      Map<String, String> tags = new HashMap<>();
      tags.put("key1", "value1");
      tags.put("key2", "value2");

      ReplicationRule rule =
          new ReplicationRule(
              new DeleteMarkerReplication(Status.DISABLED),
              new ReplicationDestination(null, null, replicationBucketArn, null, null, null, null),
              null,
              new RuleFilter(new AndOperator("TaxDocs", tags)),
              "rule1",
              null,
              1,
              null,
              Status.ENABLED);

      List<ReplicationRule> rules = new LinkedList<>();
      rules.add(rule);

      config = new ReplicationConfiguration(replicationRole, rules);
      client.setBucketReplication(
          SetBucketReplicationArgs.builder().bucket(replicationSrcBucket).config(config).build());
      config =
          client.getBucketReplication(
              GetBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());
      Assert.assertNotNull("config: expected: <non-null>, got: <null>", config);
      client.deleteBucketReplication(
          DeleteBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void deleteBucketReplication() throws Exception {
    String methodName = "deleteBucketReplication()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    if (replicationSrcBucket == null || replicationRole == null || replicationBucketArn == null) {
      mintIgnoredLog(methodName, "", System.currentTimeMillis());
      return;
    }

    long startTime = System.currentTimeMillis();
    try {
      client.deleteBucketReplication(
          DeleteBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());

      Map<String, String> tags = new HashMap<>();
      tags.put("key1", "value1");
      tags.put("key2", "value2");

      ReplicationRule rule =
          new ReplicationRule(
              new DeleteMarkerReplication(Status.DISABLED),
              new ReplicationDestination(null, null, replicationBucketArn, null, null, null, null),
              null,
              new RuleFilter(new AndOperator("TaxDocs", tags)),
              "rule1",
              null,
              1,
              null,
              Status.ENABLED);

      List<ReplicationRule> rules = new LinkedList<>();
      rules.add(rule);

      ReplicationConfiguration config = new ReplicationConfiguration(replicationRole, rules);
      client.setBucketReplication(
          SetBucketReplicationArgs.builder().bucket(replicationSrcBucket).config(config).build());
      client.deleteBucketReplication(
          DeleteBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());
      config =
          client.getBucketReplication(
              GetBucketReplicationArgs.builder().bucket(replicationSrcBucket).build());
      Assert.assertNull("config: expected: <null>, got: <non-null>", config);
      mintSuccessLog(methodName, null, startTime);
    } catch (Exception e) {
      handleException(methodName, null, startTime, e);
    }
  }

  public static void testUploadSnowballObjects(String testTags, boolean compression)
      throws Exception {
    String methodName = "uploadSnowballObjects()";

    long startTime = System.currentTimeMillis();
    String objectName1 = getRandomName();
    String objectName2 = getRandomName();
    try {
      try {
        List<SnowballObject> objects = new LinkedList<SnowballObject>();
        objects.add(
            new SnowballObject(
                objectName1,
                new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8)),
                5,
                null));
        objects.add(new SnowballObject(objectName2, createFile1Kb()));
        client.uploadSnowballObjects(
            UploadSnowballObjectsArgs.builder()
                .bucket(bucketName)
                .objects(objects)
                .compression(compression)
                .build());

        StatObjectResponse stat =
            client.statObject(
                StatObjectArgs.builder().bucket(bucketName).object(objectName1).build());
        Assert.assertEquals("object size: expected: 5, got: " + stat.size(), 5, stat.size());
        stat =
            client.statObject(
                StatObjectArgs.builder().bucket(bucketName).object(objectName2).build());
        Assert.assertEquals(
            "object size: expected: " + KB + ", got: " + stat.size(), 1 * KB, stat.size());
        mintSuccessLog(methodName, testTags, startTime);
      } finally {
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(objectName1).build());
        client.removeObject(
            RemoveObjectArgs.builder().bucket(bucketName).object(objectName2).build());
      }
    } catch (Exception e) {
      handleException(methodName, testTags, startTime, e);
    }
  }

  public static void uploadSnowballObjects() throws Exception {
    String methodName = "uploadSnowballObjects()";
    if (!mintEnv) {
      System.out.println(methodName);
    }

    testUploadSnowballObjects("[no compression]", false);
    testUploadSnowballObjects("[compression]", true);
  }

  public static void runBucketTests() throws Exception {
    makeBucket();
    bucketExists();
    deleteBucket();
    listBuckets();

    putBucketVersioning();
    getBucketVersioning();

    putObjectLockConfiguration();
    getObjectLockConfiguration();

    putBucketEncryption();
    getBucketEncryption();
    deleteBucketEncryption();

    putBucketTagging();
    getBucketTagging();
    deleteBucketTagging();

    putBucketPolicy();
    getBucketPolicy();
    deleteBucketPolicy();

    putBucketLifecycle();
    getBucketLifecycle();
    deleteBucketLifecycle();

    setBucketNotification();
    getBucketNotification();
    deleteBucketNotification();

    setBucketReplication();
    getBucketReplication();
    deleteBucketReplication();

    listenBucketNotification();
  }

  public static void runObjectTests() throws Exception {
    listObjects();

    setup();

    putObject();
    getObject();
    removeObject();
    removeObjects();
    statObject();

    copyObject();
    composeObject();
    postObject();
    downloadObject();

    putObjectRetention();
    getObjectRetention();

    getPresignedObjectUrl();
    getPresignedPostFormData();

    putObjectLegalHold();
    disableObjectLegalHold();
    getObjectLegalHold();

    selectObjectContent();

    putObjectTagging();
    getObjectTagging();
    deleteObjectTags();

    uploadSnowballObjects();

    teardown();
  }

  public static void runTests() throws Exception {
    runBucketTests();
    runObjectTests();
  }

  public static void runEndpointTests(boolean automated) throws Exception {
    client = ApiClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    // Enable trace for debugging.
    // client.traceOn(System.out);
    if (!mintEnv) System.out.println(">>> Running tests:");
    FunctionalTest.runTests();

    if (automated) {
      // Run tests on TLS endpoint
      client =
          ApiClient.builder().endpoint(endpointTLS).credentials(accessKey, secretKey).build();
      client.ignoreCertCheck();
      // Enable trace for debugging.
      // client.traceOn(System.out);
      if (!mintEnv) System.out.println(">>> Running tests on TLS endpoint:");
      isSecureEndpoint = true;
      FunctionalTest.runTests();
    }

    if (!mintEnv) {
      System.out.println();
      System.out.println(">>> Running tests for region:");
      isQuickTest = true;
      isSecureEndpoint = endpoint.toLowerCase(Locale.US).contains("https://");
      // Get new bucket name to avoid gateway failure.
      bucketName = getRandomName();
      bucketNameWithLock = getRandomName();
      client =
          ApiClient.builder()
              .endpoint(endpoint)
              .credentials(accessKey, secretKey)
              .region(region)
              .build();
      FunctionalTest.runTests();
    }
  }

  /** main(). */
  public static void main(String[] args) throws Exception {
    String mintMode = System.getenv("MINT_MODE");
    mintEnv = (mintMode != null);
    if (mintEnv) {
      isQuickTest = !mintMode.equals("full");
      isRunOnFail = "1".equals(System.getenv("RUN_ON_FAIL"));
      String dataDir = System.getenv("MINT_DATA_DIR");
      if (dataDir != null && !dataDir.equals("")) {
        dataFile1Kb = Paths.get(dataDir, "datafile-1-kB");
        dataFile6Mb = Paths.get(dataDir, "datafile-6-MB");
      }
    }
    replicationSrcBucket = System.getenv("IONOS_JAVA_TEST_REPLICATION_SRC_BUCKET");
    replicationRole = System.getenv("IONOS_JAVA_TEST_REPLICATION_ROLE");
    replicationBucketArn = System.getenv("IONOS_JAVA_TEST_REPLICATION_BUCKET_ARN");

    Process ionosProcess = null;
    Process ionosProcessTLS = null;

    boolean automated = true;
    String kmsKeyName = "my-ionos-key";
    if (args.length != 4) {
      System.out.println("usage: FunctionalTest <ENDPOINT> <ACCESSKEY> <SECRETKEY> <REGION>");
      System.exit(-1);
    } else {
      kmsKeyName = System.getenv("IONOS_JAVA_TEST_KMS_KEY_NAME");
      if (kmsKeyName == null) {
        kmsKeyName = System.getenv("MINT_KEY_ID");
      }
      sqsArn = System.getenv("IONOS_JAVA_TEST_SQS_ARN");
      endpoint = args[0];
      accessKey = args[1];
      secretKey = args[2];
      region = args[3];
      automated = false;
    }

    isSecureEndpoint = endpoint.toLowerCase(Locale.US).contains("https://");
    if (kmsKeyName != null) {
      Map<String, String> myContext = new HashMap<>();
      myContext.put("key1", "value1");
      sseKms = new ServerSideEncryptionKms(kmsKeyName, myContext);
    }

    int exitValue = 0;
    try {
      runEndpointTests(automated);
    } catch (Exception e) {
      if (!mintEnv) {
        e.printStackTrace();
      }
      exitValue = -1;
    } finally {
      if (ionosProcess != null) {
        ionosProcess.destroy();
      }
      if (ionosProcessTLS != null) {
        ionosProcessTLS.destroy();
      }
    }

    System.exit(exitValue);
  }
}

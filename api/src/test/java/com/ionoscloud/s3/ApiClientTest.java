/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage,
 * (C) 2015, 2016, 2017 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ionoscloud.s3;

import com.ionoscloud.s3.errors.InvalidResponseException;
import com.ionoscloud.s3.errors.ApiException;
import com.ionoscloud.s3.http.Method;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import org.junit.Assert;
import org.junit.Test;

public class ApiClientTest {
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String CONTENT_LENGTH = "Content-Length";

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint1() throws ApiException {
    ApiClient.builder().endpoint((String) null).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint2() throws ApiException {
    ApiClient.builder().endpoint("http://play.min.io/mybucket").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint3() throws ApiException {
    ApiClient.builder().endpoint("minio-.example.com").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint4() throws ApiException {
    ApiClient.builder().endpoint("-minio.example.com").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint5() throws ApiException {
    ApiClient.builder().endpoint("minio..example.com").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint6() throws ApiException {
    ApiClient.builder().endpoint("minio._.com").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndpoint7() throws ApiException {
    ApiClient.builder().endpoint("https://s3.amazonaws.com.cn").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPort1() throws ApiException {
    ApiClient.builder().endpoint("play.min.io", 0, false).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPort2() throws ApiException {
    ApiClient.builder().endpoint("play.min.io", 70000, false).build();
    Assert.fail("exception should be thrown");
  }

  @Test
  public void testAwsEndpoints()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    ApiClient client = null;
    String url = null;

    // virtual-style checks.
    client = ApiClient.builder().endpoint("https://s3.amazonaws.com").build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3.us-east-1.amazonaws.com/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3.us-east-2.amazonaws.com")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3.us-east-2.amazonaws.com/myobject", url.split("\\?")[0]);

    client = ApiClient.builder().endpoint("https://s3-accelerate.amazonaws.com").build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3-accelerate.amazonaws.com/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3.dualstack.ca-central-1.amazonaws.com")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3.dualstack.ca-central-1.amazonaws.com/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder().endpoint("https://s3-accelerate.dualstack.amazonaws.com").build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3-accelerate.dualstack.amazonaws.com/myobject", url.split("\\?")[0]);

    // path-style checks.
    client = ApiClient.builder().endpoint("https://s3.amazonaws.com").build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3.us-east-1.amazonaws.com/mybucket/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3.us-east-2.amazonaws.com")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3.us-east-2.amazonaws.com/mybucket/myobject", url.split("\\?")[0]);

    client = ApiClient.builder().endpoint("https://s3-accelerate.amazonaws.com").build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3-accelerate.amazonaws.com/mybucket/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3.dualstack.ca-central-1.amazonaws.com")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3.dualstack.ca-central-1.amazonaws.com/mybucket/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder().endpoint("https://s3-accelerate.dualstack.amazonaws.com").build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3-accelerate.dualstack.amazonaws.com/mybucket/myobject", url.split("\\?")[0]);

    // China region.
    // virtual-style checks.
    client =
        ApiClient.builder()
            .endpoint("https://s3.cn-north-1.amazonaws.com.cn")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3.cn-north-1.amazonaws.com.cn/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3-accelerate.amazonaws.com.cn")
            .region("cn-north-1")
            .build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3-accelerate.amazonaws.com.cn/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3.dualstack.cn-northwest-1.amazonaws.com.cn")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3.dualstack.cn-northwest-1.amazonaws.com.cn/myobject",
        url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3-accelerate.dualstack.amazonaws.com.cn")
            .region("cn-north-1")
            .build();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://mybucket.s3-accelerate.dualstack.amazonaws.com.cn/myobject", url.split("\\?")[0]);

    // path-style checks.
    client =
        ApiClient.builder()
            .endpoint("https://s3.cn-north-1.amazonaws.com.cn")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3.cn-north-1.amazonaws.com.cn/mybucket/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3-accelerate.amazonaws.com.cn")
            .region("cn-north-1")
            .build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3-accelerate.amazonaws.com.cn/mybucket/myobject", url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3.dualstack.cn-northwest-1.amazonaws.com.cn")
            .credentials("myaccesskey", "mysecretkey")
            .build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3.dualstack.cn-northwest-1.amazonaws.com.cn/mybucket/myobject",
        url.split("\\?")[0]);

    client =
        ApiClient.builder()
            .endpoint("https://s3-accelerate.dualstack.amazonaws.com.cn")
            .region("cn-north-1")
            .build();
    client.disableVirtualStyleEndpoint();
    url =
        client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("mybucket")
                .object("myobject")
                .build());
    Assert.assertEquals(
        "https://s3-accelerate.dualstack.amazonaws.com.cn/mybucket/myobject", url.split("\\?")[0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName1()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().bucket(null);
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName2()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().bucket("");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName3()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().bucket("a");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName4()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder()
        .bucket("abcdefghijklmnopqrstuvwxyz0123456789abcdefghijklmnopqrstuvwxyz0123456789");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName5()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().bucket("a..b");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName6()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().bucket("a_b");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBucketName7()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().bucket("a#b");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectName1()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().object(null);
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectName2()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().object("");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectName3()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().object("a/./b");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testObjectName4()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    StatObjectArgs.builder().object("a/../b");
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadSse1()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(256);
    ApiClient client = ApiClient.builder().endpoint("http://play.min.io:9000").build();
    client.statObject(
        StatObjectArgs.builder()
            .bucket("mybucket")
            .object("myobject")
            .ssec(new ServerSideEncryptionCustomerKey(keyGen.generateKey()))
            .build());
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWriteSse1()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    ApiClient client = ApiClient.builder().endpoint("http://play.min.io:9000").build();
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(256);
    client.putObject(
        PutObjectArgs.builder().bucket("mybucket").object("myobject").stream(
                new ByteArrayInputStream(new byte[] {}), 0, -1)
            .sse(new ServerSideEncryptionCustomerKey(keyGen.generateKey()))
            .build());
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWriteSse2()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    ApiClient client = ApiClient.builder().endpoint("http://play.min.io:9000").build();
    Map<String, String> myContext = new HashMap<>();
    myContext.put("key1", "value1");
    client.putObject(
        PutObjectArgs.builder().bucket("mybucket").object("myobject").stream(
                new ByteArrayInputStream(new byte[] {}), 0, -1)
            .sse(new ServerSideEncryptionKms("keyId", myContext))
            .build());
    Assert.fail("exception should be thrown");
  }

  @Test(expected = InvalidResponseException.class)
  public void testInvalidResponse1()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    MockWebServer server = new MockWebServer();
    MockResponse response = new MockResponse();
    response.setResponseCode(403);
    response.setHeader(CONTENT_LENGTH, "13");
    response.setBody(new Buffer().writeUtf8("<html></html>"));
    server.enqueue(response);
    server.start();

    ApiClient client = ApiClient.builder().endpoint(server.url("")).build();
    client.listBuckets();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = InvalidResponseException.class)
  public void testInvalidResponse2()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    MockWebServer server = new MockWebServer();
    MockResponse response = new MockResponse();
    response.setResponseCode(403);
    response.setHeader(CONTENT_LENGTH, "13");
    response.setHeader(CONTENT_TYPE, "application/html");
    response.setBody(new Buffer().writeUtf8("<html></html>"));
    server.enqueue(response);
    server.start();

    ApiClient client = ApiClient.builder().endpoint(server.url("")).build();
    client.listBuckets();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = InvalidResponseException.class)
  public void testInvalidResponse3()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    MockWebServer server = new MockWebServer();
    MockResponse response = new MockResponse();
    response.setResponseCode(403);
    response.setHeader(CONTENT_LENGTH, "13");
    response.setHeader(CONTENT_TYPE, "application/html;utf-8");
    response.setBody(new Buffer().writeUtf8("<html></html>"));
    server.enqueue(response);
    server.start();

    ApiClient client = ApiClient.builder().endpoint(server.url("")).build();
    client.listBuckets();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = InvalidResponseException.class)
  public void testInvalidResponse4()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    MockWebServer server = new MockWebServer();
    MockResponse response = new MockResponse();
    response.setResponseCode(403);
    response.setHeader(CONTENT_LENGTH, "0");
    response.setHeader(CONTENT_TYPE, "application/xml;utf-8");
    response.setBody(new Buffer().writeUtf8(""));
    server.enqueue(response);
    server.start();

    ApiClient client = ApiClient.builder().endpoint(server.url("")).build();
    client.listBuckets();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeBucketRegionConflicts()
      throws NoSuchAlgorithmException, IOException, InvalidKeyException, ApiException {
    ApiClient client =
        ApiClient.builder()
            .endpoint("http://play.min.io:9000")
            .credentials("foo", "bar")
            .region("us-east-1")
            .build();
    client.makeBucket(MakeBucketArgs.builder().bucket("mybucket").region("us-west-2").build());
    Assert.fail("exception should be thrown");
  }
}

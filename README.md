# IONOS Java SDK for Amazon S3 Compatible Cloud Storage

IONOS Java SDK is Simple Storage Service (aka S3) client to perform bucket and object operations to any Amazon S3 compatible object storage service.


## Minimum Requirements
Java 1.8 or above.

## Maven usage
```xml
<dependency>
    <groupId>com.ionoscloud.s3</groupId>
    <artifactId>ionos-cloud-sdk-s3</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Gradle usage
```
dependencies {
    implementation("com.ionoscloud.s3:ionos-cloud-sdk-s3:1.0.0")
}
```

## JAR download
The latest JAR can be downloaded from [here](https://repo1.maven.org/maven2/com/ionoscloud/s3ionos-cloud-sdk-s3/1.0.0/)

## Quick Start Example - File Uploader
This example program connects to an object storage server, makes a bucket on the server and then uploads a file to the bucket.

You need three items in order to connect to an object storage server.

| Parameters | Description                                                |
|------------|------------------------------------------------------------|
| Endpoint   | URL to S3 service.                                         |
| Access Key | Access key (aka user ID) of an account in the S3 service.  |
| Secret Key | Secret key (aka password) of an account in the S3 service. |


### FileUploader.java
```java
import com.ionoscloud.s3.HeadBucketArgs;
import com.ionoscloud.s3.MakeBucketArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.PostObjectArgs;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      ApiClient apiClient =
          ApiClient.builder()
              .endpoint("<Endpoint>>")
              .credentials("<Access Key>", "<Secret Key>")
              .build();

      // Make 'asiatrip' bucket if not exist.
      boolean found =
          apiClient.bucketExists(HeadBucketArgs.builder().bucket("asiatrip").build());
      if (!found) {
        // Make a new bucket called 'asiatrip'.
        apiClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
      } else {
        System.out.println("Bucket 'asiatrip' already exists.");
      }

      // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
      // 'asiatrip'.
      apiClient.postObject(
          PostObjectArgs.builder()
              .bucket("asiatrip")
              .object("asiaphotos-2015.zip")
              .filename("/home/user/Photos/asiaphotos.zip")
              .build());
      System.out.println(
          "'/home/user/Photos/asiaphotos.zip' is successfully uploaded as "
              + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'.");
    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
      System.out.println("HTTP trace: " + e.httpTrace());
    }
  }
}
```

#### Compile FileUploader
```sh
$ javac -cp ionos-cloud-sdk-s3-1.0.0-all.jar FileUploader.java
```

#### Run FileUploader
```sh
$ java -cp ionos-cloud-sdk-s3-1.0.0-all.jar:. FileUploader
'/home/user/Photos/asiaphotos.zip' is successfully uploaded as object 'asiaphotos-2015.zip' to bucket 'asiatrip'.

$ mc ls play/asiatrip/
[2016-06-02 18:10:29 PDT]  82KiB asiaphotos-2015.zip
```

## More References
* [Examples](https://github.com/ionos-cloud/sdk-java-s3/tree/release/examples)

## Feature Reference

The ionos-cloud-sdk-dbaas-mariadb SDK for JAVA aims to offer access to all resources in the IONOS Container Registry API and also offers some additional features that make the integration easier:
 - authentication for API calls
 - handling of asynchronous requests

## FAQ

 - How can I open a bug/feature request?
	Bugs & feature requests can be open on the repository issues: https://github.com/ionos-cloud/sdk-java-s3/issues/new/choose

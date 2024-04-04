/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2017 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

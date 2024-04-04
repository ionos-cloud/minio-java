/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2020 MinIO, Inc.
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
import com.ionoscloud.s3.DisableObjectLegalHoldArgs;
import com.ionoscloud.s3.EnableObjectLegalHoldArgs;
import com.ionoscloud.s3.IsObjectLegalHoldEnabledArgs;
import com.ionoscloud.s3.ApiClient;
import com.ionoscloud.s3.errors.ApiException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class IsObjectLegalHoldEnabled {
  /** ApiClient.isObjectLegalHoldEnabled() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalArgumentException {
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

      // Enable object legal hold.
      apiClient.enableObjectLegalHold(
          EnableObjectLegalHoldArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .versionId("object-versionId")
              .build());
      System.out.println("Legal hold enabled on object successfully ");

      boolean status =
          apiClient.isObjectLegalHoldEnabled(
              IsObjectLegalHoldEnabledArgs.builder()
                  .bucket("my-bucketname")
                  .object("my-objectname")
                  .build());

      if (status) {
        System.out.println("Legal hold is on");
      } else {
        System.out.println("Legal hold is off");
      }

      // Disable object legal hold.
      apiClient.disableObjectLegalHold(
          DisableObjectLegalHoldArgs.builder()
              .bucket("my-bucketname")
              .object("my-objectname")
              .build());

      status =
          apiClient.isObjectLegalHoldEnabled(
              IsObjectLegalHoldEnabledArgs.builder()
                  .bucket("my-bucketname")
                  .object("my-objectname")
                  .build());
      System.out.println("Legal hold disabled on object successfully ");

      if (status) {
        System.out.println("Legal hold is on");
      } else {
        System.out.println("Legal hold is off");
      }

    } catch (ApiException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}

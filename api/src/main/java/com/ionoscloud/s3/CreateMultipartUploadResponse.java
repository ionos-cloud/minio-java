

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.InitiateMultipartUploadResult;
import okhttp3.Headers;

/** Response class of {@link S3Base#createMultipartUploadAsync}. */
public class CreateMultipartUploadResponse extends GenericResponse {
  private InitiateMultipartUploadResult result;

  public CreateMultipartUploadResponse(
      Headers headers,
      String bucket,
      String region,
      String object,
      InitiateMultipartUploadResult result) {
    super(headers, bucket, region, object);
    this.result = result;
  }

  public InitiateMultipartUploadResult result() {
    return result;
  }
}

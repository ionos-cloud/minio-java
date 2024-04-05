

package com.ionoscloud.s3;

import okhttp3.Headers;

/** Response class of {@link S3Base#abortMultipartUploadAsync}. */
public class AbortMultipartUploadResponse extends GenericResponse {
  private String uploadId;

  public AbortMultipartUploadResponse(
      Headers headers, String bucket, String region, String object, String uploadId) {
    super(headers, bucket, region, object);
    this.uploadId = uploadId;
  }

  public String uploadId() {
    return uploadId;
  }
}

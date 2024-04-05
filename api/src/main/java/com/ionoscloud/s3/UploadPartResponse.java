

package com.ionoscloud.s3;

import okhttp3.Headers;

/** Response class of {@link S3Base#uploadPartAsync}. */
public class UploadPartResponse extends GenericResponse {
  private String uploadId;
  private int partNumber;
  private String etag;

  public UploadPartResponse(
      Headers headers,
      String bucket,
      String region,
      String object,
      String uploadId,
      int partNumber,
      String etag) {
    super(headers, bucket, region, object);
    this.uploadId = uploadId;
    this.partNumber = partNumber;
    this.etag = etag;
  }

  public String uploadId() {
    return uploadId;
  }

  public int partNumber() {
    return partNumber;
  }

  public String etag() {
    return etag;
  }
}

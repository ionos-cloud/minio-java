package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.CopyPartResult;
import okhttp3.Headers;

/** Response class of {@link S3Base#uploadPartCopyAsync}. */
public class UploadPartCopyResponse extends GenericResponse {
  private String uploadId;
  private int partNumber;
  private CopyPartResult result;

  public UploadPartCopyResponse(
      Headers headers,
      String bucket,
      String region,
      String object,
      String uploadId,
      int partNumber,
      CopyPartResult result) {
    super(headers, bucket, region, object);
    this.uploadId = uploadId;
    this.partNumber = partNumber;
    this.result = result;
  }

  public String uploadId() {
    return uploadId;
  }

  public int partNumber() {
    return partNumber;
  }

  public CopyPartResult result() {
    return result;
  }
}

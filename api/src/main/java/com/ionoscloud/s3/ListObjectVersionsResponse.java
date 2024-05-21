package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ListVersionsResult;
import okhttp3.Headers;

/** Response class of {@link S3Base#listObjectVersionsAsync}. */
public class ListObjectVersionsResponse extends GenericResponse {
  private ListVersionsResult result;

  public ListObjectVersionsResponse(
      Headers headers, String bucket, String region, ListVersionsResult result) {
    super(headers, bucket, region, null);
    this.result = result;
  }

  public ListVersionsResult result() {
    return result;
  }
}

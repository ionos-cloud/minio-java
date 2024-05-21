package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ListMultipartUploadsResult;
import okhttp3.Headers;

/** Response class of {@link S3Base#listMultipartUploadsAsync}. */
public class ListMultipartUploadsResponse extends GenericResponse {
  private ListMultipartUploadsResult result;

  public ListMultipartUploadsResponse(
      Headers headers, String bucket, String region, ListMultipartUploadsResult result) {
    super(headers, bucket, region, null);
    this.result = result;
  }

  public ListMultipartUploadsResult result() {
    return result;
  }
}

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ListPartsResult;
import okhttp3.Headers;

/** Response class of {@link S3Base#listPartsAsync}. */
public class ListPartsResponse extends GenericResponse {
  private ListPartsResult result;

  public ListPartsResponse(
      Headers headers, String bucket, String region, String object, ListPartsResult result) {
    super(headers, bucket, region, object);
    this.result = result;
  }

  public ListPartsResult result() {
    return result;
  }
}

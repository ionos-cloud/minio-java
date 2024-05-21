package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ListBucketResultV1;
import okhttp3.Headers;

/** Response class of {@link S3Base#listObjectsV1Async}. */
public class ListObjectsV1Response extends GenericResponse {
  private ListBucketResultV1 result;

  public ListObjectsV1Response(
      Headers headers, String bucket, String region, ListBucketResultV1 result) {
    super(headers, bucket, region, null);
    this.result = result;
  }

  public ListBucketResultV1 result() {
    return result;
  }
}

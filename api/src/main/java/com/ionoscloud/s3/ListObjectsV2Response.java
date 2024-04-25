

package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.ListBucketResultV2;
import okhttp3.Headers;

/** Response class of {@link S3Base#listObjectsV2Async}. */
public class ListObjectsV2Response extends GenericResponse {
  private ListBucketResultV2 result;

  public ListObjectsV2Response(
      Headers headers, String bucket, String region, ListBucketResultV2 result) {
    super(headers, bucket, region, null);
    this.result = result;
  }

  public ListBucketResultV2 result() {
    return result;
  }
}

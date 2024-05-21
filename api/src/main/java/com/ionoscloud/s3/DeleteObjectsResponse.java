package com.ionoscloud.s3;

import com.ionoscloud.s3.messages.DeleteResult;
import okhttp3.Headers;

/** Response class of {@link S3Base#deleteObjectsAsync}. */
public class DeleteObjectsResponse extends GenericResponse {
  private DeleteResult result;

  public DeleteObjectsResponse(Headers headers, String bucket, String region, DeleteResult result) {
    super(headers, bucket, region, null);
    this.result = result;
  }

  public DeleteResult result() {
    return result;
  }
}

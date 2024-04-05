

package com.ionoscloud.s3;

import okhttp3.Headers;

/** Response class of any APIs doing object creation. */
public class ObjectWriteResponse extends GenericResponse {
  private String etag;
  private String versionId;

  public ObjectWriteResponse(
      Headers headers, String bucket, String region, String object, String etag, String versionId) {
    super(headers, bucket, region, object);
    this.etag = etag;
    this.versionId = versionId;
  }

  public String etag() {
    return etag;
  }

  public String versionId() {
    return versionId;
  }
}

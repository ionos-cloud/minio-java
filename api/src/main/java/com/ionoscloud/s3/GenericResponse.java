package com.ionoscloud.s3;

import okhttp3.Headers;

/** Generic response class of any APIs. */
public class GenericResponse {
  private Headers headers;
  private String bucket;
  private String region;
  private String object;

  public GenericResponse(Headers headers, String bucket, String region, String object) {
    this.headers = headers;
    this.bucket = bucket;
    this.region = region;
    this.object = object;
  }

  public Headers headers() {
    return headers;
  }

  public String bucket() {
    return bucket;
  }

  public String region() {
    return region;
  }

  public String object() {
    return object;
  }
}

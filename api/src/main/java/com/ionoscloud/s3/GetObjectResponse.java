

package com.ionoscloud.s3;

import java.io.FilterInputStream;
import java.io.InputStream;
import okhttp3.Headers;

/**
 * Response class of {@link ApiAsyncClient#getObject} and {@link ApiClient#getObject}. This
 * class is {@link InputStream} interface compatible and it must be closed after use to release
 * underneath network resources.
 */
public class GetObjectResponse extends FilterInputStream {
  private GenericResponse response;

  public GetObjectResponse(
      Headers headers, String bucket, String region, String object, InputStream body) {
    super(body);
    this.response = new GenericResponse(headers, bucket, region, object);
  }

  public Headers headers() {
    return response.headers();
  }

  public String bucket() {
    return response.bucket();
  }

  public String region() {
    return response.region();
  }

  public String object() {
    return response.object();
  }
}

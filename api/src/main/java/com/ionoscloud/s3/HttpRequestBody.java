package com.ionoscloud.s3;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/** RequestBody that wraps a single data object. */
class HttpRequestBody extends RequestBody {
  private PartSource partSource;
  private byte[] bytes;
  private int length;
  private String contentType;

  HttpRequestBody(final PartSource partSource, final String contentType) {
    this.partSource = partSource;
    this.contentType = contentType;
  }

  HttpRequestBody(final byte[] bytes, final int length, final String contentType) {
    this.bytes = bytes;
    this.length = length;
    this.contentType = contentType;
  }

  @Override
  public MediaType contentType() {
    MediaType mediaType = null;
    if (contentType != null) mediaType = MediaType.parse(contentType);
    return (mediaType == null) ? MediaType.parse("application/octet-stream") : mediaType;
  }

  @Override
  public long contentLength() {
    return (partSource != null) ? partSource.size() : length;
  }

  @Override
  public void writeTo(BufferedSink sink) throws IOException {
    if (partSource != null) {
      sink.write(partSource.source(), partSource.size());
    } else {
      sink.write(bytes, 0, length);
    }
  }
}

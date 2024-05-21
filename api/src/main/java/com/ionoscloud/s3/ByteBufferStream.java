package com.ionoscloud.s3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/** ByteArrayOutputStream exposes underneath buffer as input stream. */
class ByteBufferStream extends ByteArrayOutputStream {
  public ByteBufferStream() {
    super();
  }

  public InputStream inputStream() {
    return new ByteArrayInputStream(this.buf, 0, this.count);
  }
}

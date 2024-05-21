package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_CompleteMultipartUpload.html">CompleteMultipartUpload
 * API</a>.
 */
@Root(name = "CompleteMultipartUploadOutput")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class CompleteMultipartUploadOutput {
  @Element(name = "Location")
  private String location;

  @Element(name = "Bucket")
  private String bucket;

  @Element(name = "Key")
  private String object;

  @Element(name = "ETag", required = false)
  private String etag;

  @Element(name = "ChecksumCRC32", required = false)
  private String checksumCRC32;

  @Element(name = "ChecksumCRC32C", required = false)
  private String checksumCRC32C;

  @Element(name = "ChecksumSHA1", required = false)
  private String checksumSHA1;

  @Element(name = "ChecksumSHA256", required = false)
  private String checksumSHA256;

  public CompleteMultipartUploadOutput() {}

  public String location() {
    return location;
  }

  public String bucket() {
    return bucket;
  }

  public String object() {
    return object;
  }

  public String etag() {
    return etag;
  }

  public String checksumCRC32() {
    return checksumCRC32;
  }

  public String checksumCRC32C() {
    return checksumCRC32C;
  }

  public String checksumSHA1() {
    return checksumSHA1;
  }

  public String checksumSHA256() {
    return checksumSHA256;
  }
}

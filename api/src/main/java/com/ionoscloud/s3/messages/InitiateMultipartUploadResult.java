package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_CreateMultipartUpload.html">CreateMultipartUpload
 * API</a>.
 */
@Root(name = "InitiateMultipartUploadResult", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class InitiateMultipartUploadResult {
  @Element(name = "Bucket")
  private String bucketName;

  @Element(name = "Key")
  private String objectName;

  @Element(name = "UploadId")
  private String uploadId;

  public InitiateMultipartUploadResult() {}

  /** Returns bucket name. */
  public String bucketName() {
    return bucketName;
  }

  /** Returns object name. */
  public String objectName() {
    return objectName;
  }

  /** Returns upload ID. */
  public String uploadId() {
    return uploadId;
  }
}

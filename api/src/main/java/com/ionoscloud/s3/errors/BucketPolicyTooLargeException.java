

package com.ionoscloud.s3.errors;

/** Thrown to indicate that received bucket policy is larger than 20KiB size. */
@SuppressWarnings("WeakerAccess")
public class BucketPolicyTooLargeException extends ApiException {
  private static final long serialVersionUID = -4589481215582512545L;

  public BucketPolicyTooLargeException(String bucketName) {
    super("Bucket policy is larger than 20KiB size for bucket " + bucketName);
  }
}

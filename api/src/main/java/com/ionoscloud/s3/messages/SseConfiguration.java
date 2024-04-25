

package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutBucketEncryption.html">PutBucketEncryption
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetBucketEncryption.html">GetBucketEncryption
 * API</a>.
 */
@Root(name = "ServerSideEncryptionConfiguration")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class SseConfiguration {
  @Element(name = "Rule", required = false)
  private SseConfigurationRule rule;

  public SseConfiguration(
      @Nullable @Element(name = "Rule", required = false) SseConfigurationRule rule) {
    this.rule = rule;
  }

  public static SseConfiguration newConfigWithSseS3Rule() {
    return new SseConfiguration(new SseConfigurationRule(SseAlgorithm.AES256, null));
  }

  public static SseConfiguration newConfigWithSseKmsRule(@Nullable String kmsMasterKeyId) {
    return new SseConfiguration(new SseConfigurationRule(SseAlgorithm.AWS_KMS, kmsMasterKeyId));
  }

  public SseConfigurationRule rule() {
    return this.rule;
  }
}

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutObjectLockConfiguration.html">PutObjectLockConfiguration
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetObjectLockConfiguration.html">GetObjectLockConfiguration
 * API</a>.
 */
@Root(name = "ObjectLockConfiguration", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class ObjectLockConfiguration {
  @Element(name = "ObjectLockEnabled")
  private String objectLockEnabled = "Enabled";

  @Element(name = "Rule", required = false)
  private Rule rule;

  public ObjectLockConfiguration() {}

  /** Constructs a new ObjectLockConfiguration object with given retention. */
  public ObjectLockConfiguration(RetentionMode mode, RetentionDuration duration)
      throws IllegalArgumentException {
    this.rule = new Rule(mode, duration);
  }

  /** Returns retention mode. */
  public RetentionMode mode() {
    return (rule != null) ? rule.mode() : null;
  }

  /** Returns retention duration. */
  public RetentionDuration duration() {
    return (rule != null) ? rule.duration() : null;
  }
}

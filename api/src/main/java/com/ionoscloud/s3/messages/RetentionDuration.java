package com.ionoscloud.s3.messages;

/** Retention duration of {@link ObjectLockConfiguration} */
public interface RetentionDuration {
  public RetentionDurationUnit unit();

  public int duration();
}

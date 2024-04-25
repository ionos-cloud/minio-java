package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/** Days type retention duration of {@link ObjectLockConfiguration} */
@Root(name = "Days")
public class RetentionDurationDays implements RetentionDuration {
  @Text(required = false)
  private Integer days;

  public RetentionDurationDays() {}

  public RetentionDurationDays(int days) {
    this.days = Integer.valueOf(days);
  }

  public RetentionDurationUnit unit() {
    return RetentionDurationUnit.DAYS;
  }

  public int duration() {
    return days;
  }

  /** Returns RetentionDurationDays as string. */
  @Override
  public String toString() {
    if (days == null) {
      return "";
    }
    return days.toString() + ((days == 1) ? " day" : " days");
  }
}

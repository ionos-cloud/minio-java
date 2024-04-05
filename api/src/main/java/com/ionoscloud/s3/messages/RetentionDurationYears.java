

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/** Years type retention duration of {@link ObjectLockConfiguration} */
@Root(name = "Years")
public class RetentionDurationYears implements RetentionDuration {
  @Text(required = false)
  private Integer years;

  public RetentionDurationYears() {}

  public RetentionDurationYears(int years) {
    this.years = Integer.valueOf(years);
  }

  public RetentionDurationUnit unit() {
    return RetentionDurationUnit.YEARS;
  }

  public int duration() {
    return years;
  }

  /** Returns RetentionDurationYears as string. */
  @Override
  public String toString() {
    if (years == null) {
      return "";
    }
    return years.toString() + ((years == 1) ? " year" : " years");
  }
}

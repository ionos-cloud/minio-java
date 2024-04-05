

package com.ionoscloud.s3.messages;

import java.time.ZonedDateTime;
import org.simpleframework.xml.Element;

/** Base class for {@link Transition} and {@link Expiration}. */
public abstract class DateDays {
  @Element(name = "Date", required = false)
  protected ResponseDate date;

  @Element(name = "Days", required = false)
  protected Integer days;

  public ZonedDateTime date() {
    return (date != null) ? date.zonedDateTime() : null;
  }

  public Integer days() {
    return days;
  }
}

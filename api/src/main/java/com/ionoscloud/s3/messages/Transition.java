

package com.ionoscloud.s3.messages;

import java.time.ZonedDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote transition information for {@link LifecycleRule}. */
@Root(name = "Transition")
public class Transition extends DateDays {
  @Element(name = "StorageClass")
  private String storageClass;

  public Transition(
      @Nullable @Element(name = "Date", required = false) ResponseDate date,
      @Nullable @Element(name = "Days", required = false) Integer days,
      @Nonnull @Element(name = "StorageClass", required = false) String storageClass) {
    if (date != null ^ days != null) {
      this.date = date;
      this.days = days;
    } else {
      throw new IllegalArgumentException("Only one of date or days must be set");
    }
    if (storageClass == null || storageClass.isEmpty()) {
      throw new IllegalArgumentException("StorageClass must be provided");
    }
    this.storageClass = storageClass;
  }

  public Transition(ZonedDateTime date, Integer days, String storageClass) {
    this(date == null ? null : new ResponseDate(date), days, storageClass);
  }

  public String storageClass() {
    return storageClass;
  }
}

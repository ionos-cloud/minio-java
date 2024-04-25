

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

/** Helper class to denote Rule information for {@link ObjectLockConfiguration}. */
@Root(name = "Rule", strict = false)
public class Rule {
  @Path(value = "DefaultRetention")
  @Element(name = "Mode", required = false)
  private RetentionMode mode;

  @Path(value = "DefaultRetention")
  @ElementUnion({
    @Element(name = "Days", type = RetentionDurationDays.class, required = false),
    @Element(name = "Years", type = RetentionDurationYears.class, required = false)
  })
  private RetentionDuration duration;

  public Rule(
      @Element(name = "Mode", required = false) RetentionMode mode,
      @ElementUnion({
            @Element(name = "Days", type = RetentionDurationDays.class, required = false),
            @Element(name = "Years", type = RetentionDurationYears.class, required = false)
          })
          RetentionDuration duration) {
    if (mode != null && duration != null) {
      this.mode = mode;
      this.duration = duration;
    } else if (mode != null || duration != null) {
      if (mode == null) {
        throw new IllegalArgumentException("mode is null");
      }
      throw new IllegalArgumentException("duration is null");
    }
  }

  public RetentionMode mode() {
    return mode;
  }

  public RetentionDuration duration() {
    return duration;
  }
}

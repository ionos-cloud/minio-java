

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote access control translation information for {@link ReplicationDestination}.
 */
@Root(name = "AccessControlTranslation")
public class AccessControlTranslation {
  @Element(name = "Owner")
  private String owner = "Destination";

  public AccessControlTranslation(@Nonnull @Element(name = "Owner") String owner) {
    this.owner = Objects.requireNonNull(owner, "Owner must not be null");
  }

  public String owner() {
    return this.owner;
  }
}

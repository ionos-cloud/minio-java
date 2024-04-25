

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/** Helper class to denote user metadata information of {@link S3OutputLocation}. */
@Root(name = "UserMetadata", strict = false)
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class UserMetadata {
  @ElementMap(
      attribute = false,
      entry = "MetadataEntry",
      inline = true,
      key = "Name",
      value = "Value",
      required = false)
  Map<String, String> metadataEntries;

  private UserMetadata(@Nonnull Map<String, String> metadataEntries) {
    Objects.requireNonNull(metadataEntries, "Metadata entries must not be null");
    if (metadataEntries.size() == 0) {
      throw new IllegalArgumentException("Metadata entries must not be empty");
    }
    this.metadataEntries = Collections.unmodifiableMap(metadataEntries);
  }
}

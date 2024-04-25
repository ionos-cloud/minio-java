

package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote encryption information of {@link S3OutputLocation}. */
@Root(name = "Encryption")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class Encryption {
  @Element(name = "EncryptionType")
  private SseAlgorithm encryptionType;

  @Element(name = "KMSContext", required = false)
  private String kmsContext;

  @Element(name = "KMSKeyId", required = false)
  private String kmsKeyId;

  public Encryption(
      @Nonnull SseAlgorithm encryptionType,
      @Nullable String kmsContext,
      @Nullable String kmsKeyId) {
    this.encryptionType =
        Objects.requireNonNull(encryptionType, "Encryption type must not be null");
    this.kmsContext = kmsContext;
    this.kmsKeyId = kmsKeyId;
  }
}

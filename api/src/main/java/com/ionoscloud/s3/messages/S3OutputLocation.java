package com.ionoscloud.s3.messages;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote S3 output location information of {@link OutputLocation}. */
@Root(name = "S3OutputLocation")
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "URF_UNREAD_FIELD")
public class S3OutputLocation {
  @Element(name = "AccessControlList", required = false)
  private AccessControlList accessControlList;

  @Element(name = "BucketName")
  private String bucketName;

  @Element(name = "CannedACL", required = false)
  private CannedAcl cannedAcl;

  @Element(name = "Encryption", required = false)
  private Encryption encryption;

  @Element(name = "Prefix")
  private String prefix;

  @Element(name = "StorageClass", required = false)
  private String storageClass;

  @Element(name = "Tagging", required = false)
  private Tags tagging;

  @Element(name = "UserMetadata", required = false)
  private UserMetadata userMetadata;

  public S3OutputLocation(
      @Nonnull String bucketName,
      @Nonnull String prefix,
      @Nullable AccessControlList accessControlList,
      @Nullable CannedAcl cannedAcl,
      @Nullable Encryption encryption,
      @Nullable String storageClass,
      @Nullable Tags tagging,
      @Nullable UserMetadata userMetadata) {
    this.bucketName = Objects.requireNonNull(bucketName, "Bucket name must not be null");
    this.prefix = Objects.requireNonNull(prefix, "Prefix must not be null");
    this.accessControlList = accessControlList;
    this.cannedAcl = cannedAcl;
    this.encryption = encryption;
    this.storageClass = storageClass;
    this.tagging = tagging;
    this.userMetadata = userMetadata;
  }
}



package com.ionoscloud.s3;

/** A source object defintion for {@link CopyObjectArgs}. */
public class CopySource extends ObjectConditionalReadArgs {
  protected CopySource() {}

  public CopySource(ObjectConditionalReadArgs args) {
    this.extraHeaders = args.extraHeaders;
    this.extraQueryParams = args.extraQueryParams;
    this.bucketName = args.bucketName;
    this.region = args.region;
    this.objectName = args.objectName;
    this.versionId = args.versionId;
    this.ssec = args.ssec;
    this.offset = args.offset;
    this.length = args.length;
    this.matchETag = args.matchETag;
    this.notMatchETag = args.notMatchETag;
    this.modifiedSince = args.modifiedSince;
    this.unmodifiedSince = args.unmodifiedSince;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link CopySource}. */
  public static final class Builder
      extends ObjectConditionalReadArgs.Builder<Builder, CopySource> {}
}

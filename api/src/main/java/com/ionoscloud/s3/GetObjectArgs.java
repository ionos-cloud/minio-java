

package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#getObject} and {@link ApiClient#getObject}. */
public class GetObjectArgs extends ObjectConditionalReadArgs {
  protected GetObjectArgs() {}

  public GetObjectArgs(DownloadObjectArgs args) {
    this.extraHeaders = args.extraHeaders;
    this.extraQueryParams = args.extraQueryParams;
    this.bucketName = args.bucketName;
    this.region = args.region;
    this.objectName = args.objectName;
    this.versionId = args.versionId;
    this.ssec = args.ssec;
  }

  public static Builder builder() {
    return new Builder();
  }

  /** Argument builder of {@link GetObjectArgs}. */
  public static final class Builder
      extends ObjectConditionalReadArgs.Builder<Builder, GetObjectArgs> {}
}

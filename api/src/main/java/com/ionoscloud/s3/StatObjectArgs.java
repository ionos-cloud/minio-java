package com.ionoscloud.s3;

/** Argument class of {@link ApiAsyncClient#statObject} and {@link ApiClient#statObject}. */
public class StatObjectArgs extends ObjectConditionalReadArgs {
  protected StatObjectArgs() {}

  public StatObjectArgs(ObjectReadArgs args) {
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

  /** Argument builder of {@link StatObjectArgs}. */
  public static final class Builder
      extends ObjectConditionalReadArgs.Builder<Builder, StatObjectArgs> {}
}

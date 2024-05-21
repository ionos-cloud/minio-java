package com.ionoscloud.s3;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import okhttp3.HttpUrl;

/** Argument class of {@link ApiAsyncClient#composeObject} and {@link ApiClient#composeObject}. */
public class ComposeObjectArgs extends ObjectWriteArgs {
  List<ComposeSource> sources;

  protected ComposeObjectArgs() {}

  public ComposeObjectArgs(CopyObjectArgs args) {
    this.extraHeaders = args.extraHeaders;
    this.extraQueryParams = args.extraQueryParams;
    this.bucketName = args.bucketName;
    this.region = args.region;
    this.objectName = args.objectName;
    this.headers = args.headers;
    this.userMetadata = args.userMetadata;
    this.sse = args.sse;
    this.tags = args.tags;
    this.retention = args.retention;
    this.legalHold = args.legalHold;
    this.sources = new LinkedList<>();
    this.sources.add(new ComposeSource(args.source()));
  }

  public List<ComposeSource> sources() {
    return sources;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public void validateSse(HttpUrl url) {
    super.validateSse(url);
    for (ComposeSource source : sources) {
      source.validateSsec(url);
    }
  }

  /** Argument builder of {@link ComposeObjectArgs}. */
  public static final class Builder extends ObjectWriteArgs.Builder<Builder, ComposeObjectArgs> {
    private void validateSources(List<ComposeSource> sources) {
      if (sources == null || sources.isEmpty()) {
        throw new IllegalArgumentException("compose sources cannot be empty");
      }
    }

    @Override
    protected void validate(ComposeObjectArgs args) {
      super.validate(args);
      validateSources(args.sources);
    }

    public Builder sources(List<ComposeSource> sources) {
      validateSources(sources);
      operations.add(args -> args.sources = sources);
      return this;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ComposeObjectArgs)) return false;
    if (!super.equals(o)) return false;
    ComposeObjectArgs that = (ComposeObjectArgs) o;
    return Objects.equals(sources, that.sources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), sources);
  }
}

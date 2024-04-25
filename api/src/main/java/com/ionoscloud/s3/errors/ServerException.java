package com.ionoscloud.s3.errors;

/** Thrown to indicate that S3 service returning HTTP server error. */
public class ServerException extends ApiException {
  private static final long serialVersionUID = 6395201577368980633L;

  private int statusCode;

  public ServerException(String message, int statusCode, String httpTrace) {
    super(message, httpTrace);
    this.statusCode = statusCode;
  }

  public int statusCode() {
    return statusCode;
  }
}

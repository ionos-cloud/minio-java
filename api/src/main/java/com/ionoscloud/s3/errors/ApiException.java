

package com.ionoscloud.s3.errors;

/** Base Exception class for all sdk exceptions. */
public class ApiException extends Exception {
  private static final long serialVersionUID = -7241010318779326306L;

  String httpTrace = null;

  /** Constructs a new ApiException. */
  public ApiException() {
    super();
  }

  /** Constructs a new ApiException with given error message. */
  public ApiException(String message) {
    super(message);
  }

  /** Constructs a new ApiException with given error message. */
  public ApiException(String message, String httpTrace) {
    super(message);
    this.httpTrace = httpTrace;
  }

  public String httpTrace() {
    return this.httpTrace;
  }
}

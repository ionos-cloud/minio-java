package com.ionoscloud.s3.errors;

/**
 * Thrown to indicate that unexpected internal library error occured while processing given request.
 */
public class InternalException extends ApiException {
  private static final long serialVersionUID = 138336287983212416L;

  /** Constructs a new InternalException with given error message. */
  public InternalException(String message, String httpTrace) {
    super(message, httpTrace);
  }
}

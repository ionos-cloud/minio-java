

package com.ionoscloud.s3.errors;

/**
 * Thrown to indicate that reading given InputStream gets EOFException before reading given length.
 */
public class InsufficientDataException extends ApiException {
  private static final long serialVersionUID = -1619719290805056566L;

  /** Constructs a new InsufficientDataException with given error message. */
  public InsufficientDataException(String message) {
    super(message);
  }
}

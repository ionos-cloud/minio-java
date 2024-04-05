

package com.ionoscloud.s3.errors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import com.ionoscloud.s3.messages.ErrorResponse;
import okhttp3.Request;
import okhttp3.Response;

/** Thrown to indicate that error response is received when executing Amazon S3 operation. */
@SuppressWarnings("WeakerAccess")
public class ErrorResponseException extends ApiException {
  private static final long serialVersionUID = -2933211538346902928L;

  private final ErrorResponse errorResponse;

  @SuppressFBWarnings(
      value = "Se",
      justification = "There's really no excuse except that nobody has complained")
  private final Response response;

  /** Constructs a new ErrorResponseException with error response and HTTP response object. */
  public ErrorResponseException(ErrorResponse errorResponse, Response response, String httpTrace) {
    super(errorResponse.message(), httpTrace);
    this.errorResponse = errorResponse;
    this.response = response;
  }

  /** Returns ErrorResponse contains detail of what error occured. */
  public ErrorResponse errorResponse() {
    return this.errorResponse;
  }

  public Response response() {
    return this.response;
  }

  @Override
  public String toString() {
    Request request = response.request();
    return "error occurred\n"
        + errorResponse.toString()
        + "\n"
        + "request={"
        + "method="
        + request.method()
        + ", "
        + "url="
        + request.url()
        + ", "
        + "headers="
        + request
            .headers()
            .toString()
            .replaceAll("Signature=([0-9a-f]+)", "Signature=*REDACTED*")
            .replaceAll("Credential=([^/]+)", "Credential=*REDACTED*")
        + "}\n"
        + "response={"
        + "code="
        + response.code()
        + ", "
        + "headers="
        + response.headers()
        + "}\n";
  }
}

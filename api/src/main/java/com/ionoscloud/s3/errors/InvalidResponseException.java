package com.ionoscloud.s3.errors;

/** Thrown to indicate that non-xml response thrown from server. */
public class InvalidResponseException extends ApiException {
  private static final long serialVersionUID = -4793742105569629274L;

  public InvalidResponseException(
      int responseCode, String contentType, String body, String httpTrace) {
    super(
        "Non-XML response from server. Response code: "
            + responseCode
            + ", Content-Type: "
            + contentType
            + ", body: "
            + body,
        httpTrace);
  }
}

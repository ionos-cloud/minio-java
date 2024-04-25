

package com.ionoscloud.s3.errors;

/** Thrown to indicate that error at XML marshalling or unmarshalling. */
public class XmlParserException extends ApiException {
  private static final long serialVersionUID = -3877568719271880309L;

  public XmlParserException(Exception e) {
    super(e.toString());
  }
}

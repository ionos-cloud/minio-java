

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/** Helper class to denote CloudFunction configuration of {@link NotificationConfiguration}. */
@Root(name = "CloudFunctionConfiguration", strict = false)
public class CloudFunctionConfiguration extends NotificationCommonConfiguration {
  @Element(name = "CloudFunction")
  private String cloudFunction;

  public CloudFunctionConfiguration() {
    super();
  }

  /** Returns cloudFunction. */
  public String cloudFunction() {
    return cloudFunction;
  }

  /** Sets cloudFunction. */
  public void setCloudFunction(String cloudFunction) {
    this.cloudFunction = cloudFunction;
  }
}

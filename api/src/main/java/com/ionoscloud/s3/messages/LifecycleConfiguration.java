

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutBucketLifecycleConfiguration.html">PutBucketLifecycleConfiguration
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetBucketLifecycleConfiguration.html">GetBucketLifecycleConfiguration
 * API</a>.
 */
@Root(name = "LifecycleConfiguration")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class LifecycleConfiguration {
  @ElementList(name = "Rule", inline = true)
  private List<LifecycleRule> rules;

  /** Constructs new lifecycle configuration. */
  public LifecycleConfiguration(
      @Nonnull @ElementList(name = "Rule", inline = true) List<LifecycleRule> rules) {
    this.rules =
        Collections.unmodifiableList(Objects.requireNonNull(rules, "Rules must not be null"));
    if (rules.isEmpty()) {
      throw new IllegalArgumentException("Rules must not be empty");
    }
  }

  public List<LifecycleRule> rules() {
    return rules;
  }
}

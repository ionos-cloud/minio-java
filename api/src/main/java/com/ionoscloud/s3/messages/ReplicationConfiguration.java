

package com.ionoscloud.s3.messages;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Object representation of request XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_PutBucketReplication.html">PutBucketReplication
 * API</a> and response XML of <a
 * href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_GetBucketReplication.html">GetBucketReplication
 * API</a>.
 */
@Root(name = "ReplicationConfiguration")
@Namespace(reference = "http://s3.amazonaws.com/doc/2006-03-01/")
public class ReplicationConfiguration {
  @Element(name = "Role", required = false)
  private String role;

  @ElementList(name = "Rule", inline = true)
  private List<ReplicationRule> rules;

  /** Constructs new replication configuration. */
  public ReplicationConfiguration(
      @Nullable @Element(name = "Role", required = false) String role,
      @Nonnull @ElementList(name = "Rule", inline = true) List<ReplicationRule> rules) {
    this.role = role;

    this.rules =
        Collections.unmodifiableList(Objects.requireNonNull(rules, "Rules must not be null"));
    if (rules.isEmpty()) {
      throw new IllegalArgumentException("Rules must not be empty");
    }
    if (rules.size() > 1000) {
      throw new IllegalArgumentException("More than 1000 rules are not supported");
    }
  }

  public String role() {
    return role;
  }

  public List<ReplicationRule> rules() {
    return Collections.unmodifiableList(rules == null ? new LinkedList<>() : rules);
  }
}

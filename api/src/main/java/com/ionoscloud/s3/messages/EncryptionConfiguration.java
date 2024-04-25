

package com.ionoscloud.s3.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Helper class to denote encryption configuration information for {@link ReplicationDestination}.
 */
@Root(name = "EncryptionConfiguration")
public class EncryptionConfiguration {
  @Element(name = "ReplicaKmsKeyID", required = false)
  private String replicaKmsKeyID;

  public EncryptionConfiguration(
      @Nullable @Element(name = "ReplicaKmsKeyID", required = false) String replicaKmsKeyID) {
    this.replicaKmsKeyID = replicaKmsKeyID;
  }

  public String replicaKmsKeyID() {
    return this.replicaKmsKeyID;
  }
}

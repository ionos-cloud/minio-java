

package com.ionoscloud.s3.messages;

import org.simpleframework.xml.Root;

/** Permission represents type of grantee. */
@Root(name = "Permission")
public enum Permission {
  FULL_CONTROL,
  WRITE,
  WRITE_ACP,
  READ,
  READ_ACP;
}

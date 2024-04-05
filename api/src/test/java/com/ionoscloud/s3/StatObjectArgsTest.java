

package com.ionoscloud.s3;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import org.junit.Assert;
import org.junit.Test;

public class StatObjectArgsTest {
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBuild() {
    StatObjectArgs.builder().build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBucketBuild1() {
    StatObjectArgs.builder().object("myobject").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBucketBuild2() {
    StatObjectArgs.builder().object("myobject").bucket(null).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBucketBuild3() {
    StatObjectArgs.builder().bucket("mybucket").bucket(null).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyRegionBuild() {
    StatObjectArgs.builder().region("").build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyObjectBuild1() {
    StatObjectArgs.builder().object(null).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyObjectBuild2() {
    StatObjectArgs.builder().bucket("mybucket").ssec(null).object("").build();
    Assert.fail("exception should be thrown");
  }

  @Test
  public void testBuild() throws NoSuchAlgorithmException, InvalidKeyException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(256);
    ServerSideEncryptionCustomerKey ssec =
        new ServerSideEncryptionCustomerKey(keyGen.generateKey());
    StatObjectArgs args =
        StatObjectArgs.builder()
            .bucket("mybucket")
            .ssec(ssec)
            .object("myobject")
            .region("myregion")
            .versionId("myversionid")
            .build();
    Assert.assertEquals("mybucket", args.bucket());
    Assert.assertEquals("myregion", args.region());
    Assert.assertEquals("myobject", args.object());
    Assert.assertEquals("myversionid", args.versionId());
    Assert.assertEquals(ssec, args.ssec());

    args = StatObjectArgs.builder().bucket("mybucket").object("myobject").build();
    Assert.assertEquals("mybucket", args.bucket());
    Assert.assertEquals(null, args.region());
    Assert.assertEquals("myobject", args.object());
    Assert.assertEquals(null, args.versionId());
    Assert.assertEquals(null, args.ssec());
  }
}

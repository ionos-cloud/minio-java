package com.ionoscloud.s3;

import org.junit.Assert;
import org.junit.Test;

public class MakeBucketArgsTest {
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBuild() {
    MakeBucketArgs.builder().build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBucketBuild1() {
    MakeBucketArgs.builder().objectLock(false).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBucketBuild2() {
    MakeBucketArgs.builder().bucket(null).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyBucketBuild3() {
    MakeBucketArgs.builder().bucket("mybucket").bucket(null).build();
    Assert.fail("exception should be thrown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyRegionBuild() {
    MakeBucketArgs.builder().region("").build();
    Assert.fail("exception should be thrown");
  }

  @Test
  public void testBuild() {
    MakeBucketArgs args =
        MakeBucketArgs.builder().objectLock(true).bucket("mybucket").region("myregion").build();
    Assert.assertEquals("mybucket", args.bucket());
    Assert.assertEquals("myregion", args.region());
    Assert.assertEquals(true, args.objectLock());

    args = MakeBucketArgs.builder().bucket("mybucket").build();
    Assert.assertEquals("mybucket", args.bucket());
    Assert.assertEquals(null, args.region());
    Assert.assertEquals(false, args.objectLock());
  }
}



package com.ionoscloud.s3.admin;

import com.google.common.io.BaseEncoding;
import java.io.UnsupportedEncodingException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.junit.Assert;
import org.junit.Test;

public class CryptoTest {
  @Test
  public void canEncryptDecryptText()
      throws UnsupportedEncodingException, InvalidCipherTextException {
    byte[] data = "bar".getBytes("UTF-8");
    byte[] encryptedData = Crypto.encrypt("foo", data);
    byte[] decryptedData = Crypto.decrypt("foo", encryptedData);
    Assert.assertArrayEquals(data, decryptedData);
  }

  @Test
  public void canDecryptText() throws UnsupportedEncodingException, InvalidCipherTextException {
    String hexData =
        "0c01c44abba473bae01f777f01edbf988723a60385170577d7644f1fb132b3de00bf47ea28fc00e6ca222e42538c5a5091fa64de7ed4da81c5d0b69c";
    Crypto.decrypt("foo", BaseEncoding.base16().lowerCase().decode(hexData));
  }
}

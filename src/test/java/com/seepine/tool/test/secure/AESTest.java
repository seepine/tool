package com.seepine.tool.test.secure;

import com.seepine.tool.secure.symmetric.AES;

public class AESTest {
  public static void main(String[] args) {
    AES aes = new AES("1234567890123456");
    String str = aes.encrypt("this is password");
    System.out.println(str);
    System.out.println(aes.decrypt(str));
  }
}

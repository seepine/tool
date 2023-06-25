package com.seepine.tool.test.secure.digest;

import com.seepine.tool.secure.digest.Digests;
import com.seepine.tool.secure.digest.Hmac;
import com.seepine.tool.secure.digest.HmacAlgorithm;

public class HmacTest {
  public static void main(String[] args) {
    // 非线程安全
    Hmac hmac = Digests.hmac(HmacAlgorithm.HmacSHA512, "password");
    System.out.println(hmac.digestHex("123456"));
    System.out.println(hmac.digestBase64("123456"));

    // 线程安全
    System.out.println(Digests.hmac(HmacAlgorithm.HmacSHA256, "password").digestHex("123456"));
  }
}

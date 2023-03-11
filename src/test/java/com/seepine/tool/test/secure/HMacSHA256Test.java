package com.seepine.tool.test.secure;

import com.seepine.tool.secure.digest.HMacSHA256;
import com.seepine.tool.secure.symmetric.Base64;
import com.seepine.tool.util.Bytes;

public class HMacSHA256Test {
  public static void main(String[] args) {
    String secret = "your secret";
    String originStr = "123456";
    byte[] res = HMacSHA256.digestByte(secret, originStr);
    System.out.println(Bytes.toHex(res));
    // 2DD2F4AE2B086902A3A63BDB89CE471BA66C5D9898EEBA42E3771AF2E6CB4CEE
    System.out.println(Base64.encode(res));
    // LdL0risIaQKjpjvbic5HG6ZsXZiY7rpC43ca8ubLTO4=
  }
}

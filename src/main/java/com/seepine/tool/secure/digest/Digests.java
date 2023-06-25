package com.seepine.tool.secure.digest;

public class Digests {
  public static Hmac hmac(HmacAlgorithm algorithm, String key) {
    return new Hmac(algorithm, key);
  }

  public static MessageDigest digest(DigestAlgorithm algorithm) {
    return new MessageDigest(algorithm);
  }
}

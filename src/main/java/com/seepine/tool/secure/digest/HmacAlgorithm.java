package com.seepine.tool.secure.digest;

/**
 * HMAC算法类型<br>
 * see: <a
 * href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Mac">doc</a>
 *
 * @author seepine
 */
public enum HmacAlgorithm {
  HmacMD5("HmacMD5"),
  HmacSHA1("HmacSHA1"),
  HmacSHA256("HmacSHA256"),
  HmacSHA384("HmacSHA384"),
  HmacSHA512("HmacSHA512");
  private final String value;

  HmacAlgorithm(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}

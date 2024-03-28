package com.seepine.tool.secure.symmetric;

import com.seepine.tool.util.Strings;

/**
 * 对称可搜索加密
 *
 * @author seepine
 * @since 0.3.14
 */
public class SSE {
  private final String key;
  private final String separate;

  /**
   * 构造
   *
   * @param key aes密钥
   */
  public SSE(String key) {
    this(key, Strings.POUND);
  }
  /**
   * 构造
   *
   * @param key aes密钥
   * @param separate 分隔符，默认#
   */
  public SSE(String key, String separate) {
    this.key = key;
    this.separate = separate;
  }

  /**
   * 快速构建
   *
   * @param key aes密钥
   * @return 实例
   */
  public static SSE create(String key) {
    return new SSE(key);
  }

  /**
   * 加密
   *
   * @param src 明文
   * @return 密文
   */
  public String encode(String src) {
    StringBuilder cipher = new StringBuilder();
    AES aes = new AES(key);
    for (char c : src.toCharArray()) {
      cipher.append(aes.encrypt(String.valueOf(c))).append(separate);
    }
    return cipher.toString();
  }

  /**
   * 解密
   *
   * @param cipher 密文
   * @return 明文
   */
  public String decode(String cipher) {
    StringBuilder src = new StringBuilder();
    AES aes = new AES(key);
    for (String s : cipher.split(separate)) {
      src.append(aes.decrypt(s));
    }
    return src.toString();
  }
}

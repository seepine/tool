package com.seepine.tool.secure.symmetric;

import java.nio.charset.StandardCharsets;

/**
 * 对称加密，base64
 *
 * @author seepine
 * @since 0.0.3
 */
public class Base64 {
  private static final java.util.Base64.Encoder base64Encoder = java.util.Base64.getEncoder();
  private static final java.util.Base64.Decoder base64Decoder = java.util.Base64.getDecoder();

  /** 加密 */
  public static String encode(byte[] src) {
    return base64Encoder.encodeToString(src);
  }

  /** 加密 */
  public static String encode(String src) {
    return encode(src.getBytes(StandardCharsets.UTF_8));
  }

  /** 加密 */
  public static byte[] encodeByte(String src) {
    return encodeByte(src.getBytes(StandardCharsets.UTF_8));
  }

  /** 加密 */
  public static byte[] encodeByte(byte[] src) {
    return base64Encoder.encode(src);
  }

  /** 解密 */
  public static String decode(byte[] cipher) {
    return new String(decodeByte(cipher), StandardCharsets.UTF_8);
  }
  /** 解密 */
  public static String decode(String cipher) {
    return new String(decodeByte(cipher), StandardCharsets.UTF_8);
  }
  /** 解密 */
  public static byte[] decodeByte(String cipher) {
    return decodeByte(cipher.getBytes(StandardCharsets.UTF_8));
  }
  /** 解密 */
  public static byte[] decodeByte(byte[] cipher) {
    return base64Decoder.decode(cipher);
  }
}

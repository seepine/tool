package com.seepine.tool.secure.digest;

import com.seepine.tool.exception.CryptoException;
import com.seepine.tool.util.Bytes;
import com.seepine.tool.util.Strings;

import javax.annotation.Nonnull;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * HMacSHA256
 *
 * @author seepine
 * @since 0.2.2
 */
public class HMacSHA256 {
  byte[] keyBytes;

  public HMacSHA256(@Nonnull String secret) {
    keyBytes = secret.getBytes(StandardCharsets.UTF_8);
  }

  @Nonnull
  public String digest(@Nonnull String str) {
    return Bytes.toHex(digestByte(str));
  }

  @Nonnull
  public byte[] digestByte(@Nonnull String str) {
    return digestByte(str.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * 摘要，线程安全
   *
   * @param strBytes 原值字节数组
   * @return 字节数组
   */
  @Nonnull
  public byte[] digestByte(@Nonnull byte[] strBytes) {
    String algorithm = Strings.H_MAC_SHA256;
    Mac hmacSha256;
    try {
      hmacSha256 = Mac.getInstance(algorithm);
      hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, algorithm));
      return hmacSha256.doFinal(strBytes);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      throw new CryptoException(e);
    }
  }

  /**
   * 摘要
   *
   * @param secret 密钥
   * @param str 原值
   * @return 字节数组
   */
  @Nonnull
  public static byte[] digestByte(@Nonnull String secret, @Nonnull String str) {
    return new HMacSHA256(secret).digestByte(str);
  }

  /**
   * 摘要
   *
   * @param secret 密钥
   * @param str 原值
   * @return 十六进制结果
   */
  @Nonnull
  public static String digest(@Nonnull String secret, @Nonnull String str) {
    return new HMacSHA256(secret).digest(str);
  }
}

package com.seepine.tool.secure.digest;

import com.seepine.tool.exception.CryptoException;
import com.seepine.tool.util.Bytes;
import com.seepine.tool.util.Strings;

import javax.annotation.Nonnull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * md5工具类
 *
 * @author seepine
 * @since 0.2.2
 */
public class MD5 {
  private MD5() {}

  /**
   * md5摘要
   *
   * @param str 原值，例如：123456
   * @return 16进制值，例如：E10ADC3949BA59ABBE56E057F20F883E
   */
  @Nonnull
  public static String digest(@Nonnull String str) {
    return Bytes.toHex(digestByte(str));
  }
  /**
   * md5摘要
   *
   * @param bytes 原值字节数组,例如："123456".getBytes(StandardCharsets.UTF_8)
   * @return 16进制值，例如：E10ADC3949BA59ABBE56E057F20F883E
   */
  @Nonnull
  public static String digest(@Nonnull byte[] bytes) {
    return Bytes.toHex(digestByte(bytes));
  }
  /**
   * md5摘要
   *
   * @param is 输入流
   * @return 16进制值
   */
  @Nonnull
  public static String digest(@Nonnull InputStream is) {
    return Bytes.toHex(digestByte(is));
  }

  /**
   * md5摘要
   *
   * @param str 原值，例如：123456
   * @return 字节数组，例如方便直接再base64加密等
   */
  @Nonnull
  public static byte[] digestByte(@Nonnull String str) {
    return digestByte(str.getBytes(StandardCharsets.UTF_8));
  }
  /**
   * md5摘要
   *
   * @param bytes 原值字节数组,例如："123456".getBytes(StandardCharsets.UTF_8)
   * @return 字节数组，例如方便直接再base64加密等
   */
  @Nonnull
  public static byte[] digestByte(@Nonnull byte[] bytes) {
    return digestByte(new ByteArrayInputStream(bytes));
  }

  /**
   * md5摘要，支持流输入，例如大文件计算md5避免oom
   *
   * @param is 输入流
   * @return 字节数组，例如方便直接再base64加密等
   */
  @Nonnull
  public static byte[] digestByte(@Nonnull InputStream is) throws CryptoException {
    byte[] buffer = new byte[4096];
    int numRead;
    MessageDigest md5;
    try {
      md5 = MessageDigest.getInstance(Strings.MD5);
      while ((numRead = is.read(buffer)) > 0) {
        md5.update(buffer, 0, numRead);
      }
      return md5.digest();
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }
}

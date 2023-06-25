package com.seepine.tool.secure.digest;

import com.seepine.tool.secure.symmetric.Base64;
import com.seepine.tool.util.HexUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;

/**
 * md5工具类，线程安全
 *
 * @author seepine
 * @since 0.2.2
 */
public class MD5 {

  private MD5() {}

  public static byte[] digest(InputStream data, int bufferLength) {
    return Digests.digest(DigestAlgorithm.MD5).digest(data, bufferLength);
  }

  public static byte[] digest(InputStream data) {
    return digest(data, -1);
  }

  public static byte[] digest(@Nonnull byte[] data) {
    return digest(new ByteArrayInputStream(data));
  }

  public static byte[] digest(@Nonnull String data) {
    return digest(data, StandardCharsets.UTF_8);
  }

  public static byte[] digest(@Nonnull String data, @Nonnull Charset charset) {
    return digest(data.getBytes(charset));
  }

  public static String digestHex(@Nonnull String data) {
    return digestHex(data, StandardCharsets.UTF_8);
  }

  public static String digestHex(@Nonnull String data, @Nonnull Charset charset) {
    return HexUtil.encodeHexStr(digest(data, charset));
  }

  public static String digestBase64(@Nonnull String data) {
    return digestBase64(data, StandardCharsets.UTF_8);
  }

  public static String digestBase64(@Nonnull String data, @Nonnull Charset charset) {
    return Base64.encode(digest(data, charset));
  }
}

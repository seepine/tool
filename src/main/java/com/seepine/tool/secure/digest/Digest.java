package com.seepine.tool.secure.digest;

import com.seepine.tool.exception.CryptoException;
import com.seepine.tool.secure.symmetric.Base64;
import com.seepine.tool.util.HexUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nonnull;

public interface Digest {
  /**
   * 加入需要被摘要的内容
   *
   * @param in 内容
   * @param inOff 内容起始位置
   * @param len 内容长度
   */
  void update(byte[] in, int inOff, int len);

  /**
   * 结束并生成摘要
   *
   * @return 摘要内容
   */
  byte[] doFinal();

  /** 重置 */
  void reset();

  default byte[] digest(InputStream data, int bufferLength) {
    if (bufferLength < 1) {
      bufferLength = 8192;
    }
    final byte[] buffer = new byte[bufferLength];
    byte[] result;
    try {
      int read = data.read(buffer, 0, bufferLength);
      while (read > -1) {
        update(buffer, 0, read);
        read = data.read(buffer, 0, bufferLength);
      }
      result = doFinal();
    } catch (IOException e) {
      throw new CryptoException(e);
    } finally {
      reset();
    }
    return result;
  }

  default byte[] digest(InputStream data) {
    return digest(data, -1);
  }

  default byte[] digest(@Nonnull byte[] data) {
    return digest(new ByteArrayInputStream(data));
  }

  default byte[] digest(@Nonnull String data) {
    return digest(data, StandardCharsets.UTF_8);
  }

  default byte[] digest(@Nonnull String data, @Nonnull Charset charset) {
    return digest(data.getBytes(charset));
  }

  default String digestHex(@Nonnull String data) {
    return digestHex(data, StandardCharsets.UTF_8);
  }

  default String digestHex(@Nonnull String data, @Nonnull Charset charset) {
    return HexUtil.encodeHexStr(digest(data, charset));
  }

  default String digestBase64(@Nonnull String data) {
    return digestBase64(data, StandardCharsets.UTF_8);
  }

  default String digestBase64(@Nonnull String data, @Nonnull Charset charset) {
    return Base64.encode(digest(data, charset));
  }
}

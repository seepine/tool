package com.seepine.tool.util;

import javax.annotation.Nonnull;

public class Bytes {
  /**
   * 字节数组转hex
   *
   * @param b 字节数组
   * @return 十六进制
   */
  @Nonnull
  public static String toHex(@Nonnull byte[] b) {
    return toHexLowerCase(b).toUpperCase();
  }
  /**
   * 字节数组转hex
   *
   * @param b 字节数组
   * @return 十六进制
   */
  @Nonnull
  public static String toHexLowerCase(@Nonnull byte[] b) {
    StringBuilder ret = new StringBuilder();
    for (byte value : b) {
      int val = ((int) value) & 0xff;
      if (val < 16) {
        ret.append(Strings.ZERO);
      }
      ret.append(Integer.toHexString(val));
    }
    return ret.toString();
  }
}

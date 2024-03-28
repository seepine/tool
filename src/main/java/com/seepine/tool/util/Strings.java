package com.seepine.tool.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * @author seepine
 * @since 0.2.0
 */
public class Strings {
  public static final String EMPTY = "";
  public static final String BLANK = " ";
  public static final String AT = "@";
  public static final String DOT = ".";
  public static final String AMP = "&";
  public static final String COLON = ":";
  public static final String COMMA = ",";
  public static final String DASHED = "-";
  public static final String EQUAL = "=";
  public static final String POUND = "#";
  public static final String UNDERLINE = "_";
  public static final String SLASH = "/";
  public static final String ZERO = "0";
  public static final String UTF_8 = "UTF-8";
  public static final String MD5 = "MD5";
  public static final String RSA = "RSA";
  public static final String AES = "AES";

  /**
   * 是否为空白 例如null,""," ","\n"
   *
   * @code StrUtil.isBlank(null) // true
   * @code StrUtil.isBlank("") // true
   * @code StrUtil.isBlank(" ") // true
   * @code StrUtil.isBlank(" \t\n") // true
   * @code StrUtil.isBlank("abc") // false
   * @param str 字符串
   * @return 是否为空白
   */
  public static boolean isBlank(@Nullable CharSequence str) {
    if (str == null) {
      return true;
    }
    final int length = str.length();
    for (int i = 0; i < length; i++) {
      if (!isBlankChar(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  /**
   * 是否不为空白
   *
   * @param str 字符串
   * @return 是否不为空白
   */
  public static boolean nonBlank(@Nullable CharSequence str) {
    return !isBlank(str);
  }
  /**
   * 是否空白符 空白符包括空格、制表符、全角空格和不间断空格
   *
   * @param c 字符
   * @return 是否空白符
   */
  public static boolean isBlankChar(char c) {
    return Character.isWhitespace(c)
        || Character.isSpaceChar(c)
        || c == '\ufeff'
        || c == '\u202a'
        || c == '\u0000';
  }

  public static boolean startWith(@Nullable String value, @Nonnull String prefix) {
    return startWith(value, prefix, 0);
  }

  public static boolean startWith(@Nullable String value, @Nonnull String prefix, int offset) {
    return value != null && value.startsWith(prefix, offset);
  }

  public static boolean endsWith(@Nullable String value, @Nonnull String prefix) {
    return value != null && value.endsWith(prefix);
  }

  /**
   * 编码字符串<br>
   * 使用系统默认编码
   *
   * @param str 字符串
   * @return 编码后的字节码
   */
  public static byte[] bytes(@Nullable CharSequence str) {
    return bytes(str, Charset.defaultCharset());
  }

  /**
   * 编码字符串
   *
   * @param str 字符串
   * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
   * @return 编码后的字节码
   */
  public static byte[] bytes(@Nullable CharSequence str, @Nullable String charset) {
    return bytes(
        str, Objects.isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
  }

  /**
   * 编码字符串
   *
   * @param str 字符串
   * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
   * @return 编码后的字节码
   */
  public static byte[] bytes(@Nullable CharSequence str, @Nullable Charset charset) {
    if (str == null) {
      return null;
    }
    if (null == charset) {
      return str.toString().getBytes();
    }
    return str.toString().getBytes(charset);
  }

  /**
   * 解码字节码
   *
   * @param data 字符串
   * @return 解码后的字符串
   */
  @Nullable
  public static String toString(byte[] data) {
    return toString(data, StandardCharsets.UTF_8);
  }

  /**
   * 解码字节码
   *
   * @param data 字符串
   * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
   * @return 解码后的字符串
   */
  @Nullable
  public static String toString(byte[] data, @Nullable Charset charset) {
    if (data == null) {
      return null;
    }
    if (null == charset) {
      return new String(data);
    }
    return new String(data, charset);
  }

  /**
   * 过滤字符串
   *
   * @code String.filter("abcd",(val)->)
   * @param str 字符串
   * @param filter 过滤器
   * @return 过滤后的字符串
   * @since 5.4.0
   */
  @Nullable
  public static String filter(
      @Nullable final CharSequence str, @Nullable final Function<CharSequence, Boolean> filter) {
    if (str == null || filter == null) {
      return toString(str);
    }
    int len = str.length();
    final StringBuilder sb = new StringBuilder(len);
    for (char c : str.toString().toCharArray()) {
      if (filter.apply(String.valueOf(c))) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /**
   * {@link CharSequence} 转为字符串，null安全
   *
   * @param cs {@link CharSequence}
   * @return 字符串
   */
  @Nullable
  public static String toString(@Nullable CharSequence cs) {
    return null == cs ? null : cs.toString();
  }
}

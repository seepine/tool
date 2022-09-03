package com.seepine.tool.util;
/**
 * @author seepine
 * @since 0.0.1
 */
public class StrUtil {
  public static final String AT = "@";
  /**
   * 是否为空，空定义为null或""
   *
   * @param str 字符串
   * @return 是否为空
   */
  public static boolean isEmpty(CharSequence str) {
    return str == null || str.length() == 0;
  }

  /**
   * 是否不为空，空定义为null或""
   *
   * @param str 字符串
   * @return 是否不为空
   */
  public static boolean isNotEmpty(CharSequence str) {
    return !isEmpty(str);
  }

  /**
   * 是否为空白 例如null,""," ","\n" 例： StrUtil.isBlank(null) // true StrUtil.isBlank("") // true
   * StrUtil.isBlank(" ") // true StrUtil.isBlank(" \t\n") // true StrUtil.isBlank("abc") // false
   *
   * @param str 字符串
   * @return 是否为空白
   */
  public static boolean isBlank(CharSequence str) {
    if (isEmpty(str)) {
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
  public static boolean isNotBlank(CharSequence str) {
    return !isBlank(str);
  }

  /**
   * 是否空白符 空白符包括空格、制表符、全角空格和不间断空格
   *
   * @param c 字符
   * @return 是否空白符
   */
  public static boolean isBlankChar(char c) {
    return isBlankChar((int) c);
  }

  /**
   * 是否空白符 空白符包括空格、制表符、全角空格和不间断空格
   *
   * @param c 字符
   * @return 是否空白符
   */
  public static boolean isBlankChar(int c) {
    return Character.isWhitespace(c)
        || Character.isSpaceChar(c)
        || c == '\ufeff'
        || c == '\u202a'
        || c == '\u0000';
  }
}

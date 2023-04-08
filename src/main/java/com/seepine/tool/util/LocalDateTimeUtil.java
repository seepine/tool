package com.seepine.tool.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 *
 * @author seepine
 * @since 0.2.5
 */
public class LocalDateTimeUtil {
  private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  /**
   * 判断a是否小于b
   *
   * @param a eg.2022-7-11 00:00:00
   * @param b eg.2022-7-12 00:00:00
   * @return a是否小于b eg.true
   */
  public static boolean isLt(LocalDateTime a, LocalDateTime b) {
    return a.isBefore(b);
  }
  /**
   * 判断a是否小于等于b
   *
   * @param a eg.2022-7-11 00:00:00
   * @param b eg.2022-7-12 00:00:00
   * @return a是否小于等于b eg.true
   */
  public static boolean isLe(LocalDateTime a, LocalDateTime b) {
    // 也就是b是否大于a
    return isGt(b, a);
  }
  /**
   * 判断a是否大于b
   *
   * @param a eg.2022-7-15 00:00:00
   * @param b eg.2022-7-12 00:00:00
   * @return a是否大于b eg.true
   */
  public static boolean isGt(LocalDateTime a, LocalDateTime b) {
    return a.isAfter(b);
  }
  /**
   * 判断a是否大于等于b
   *
   * @param a eg.2022-7-15 00:00:00
   * @param b eg.2022-7-12 00:00:00
   * @return a是否大于等于b eg.true
   */
  public static boolean isGe(LocalDateTime a, LocalDateTime b) {
    // 也就是b是否小于a
    return isLt(b, a);
  }
  /**
   * 使用yyyy-MM-dd HH:mm:ss格式化
   *
   * @param date 日期
   * @return 2020-11-11 15:13:42
   */
  public static String format(LocalDateTime date) {
    return date.format(DF);
  }
  /**
   * 使用dateTimeFormatter格式化
   *
   * @param date 日期
   * @return dateTimeFormatter配置的格式
   */
  public static String format(LocalDateTime date, DateTimeFormatter dateTimeFormatter) {
    return date.format(dateTimeFormatter);
  }
  /**
   * 使用pattern格式化，例如 yyyy-MM-dd HH:mm:ss
   *
   * @param date 日期
   * @return pattern配置的格式
   */
  public static String format(LocalDateTime date, String pattern) {
    return date.format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 字符串转对象,使用默认yyyy-MM-dd HH:mm:ss格式
   *
   * @param text 2023/01/01 12:12:12
   * @return 对象
   * @since 0.2.6
   */
  public static LocalDateTime parse(CharSequence text) {
    return LocalDateTime.parse(text, DF);
  }

  /**
   * 字符串转对象
   *
   * @param text 2023/01/01 12:12:12
   * @param pattern yyyy/MM/dd HH:mm:ss
   * @return 对象
   * @since 0.2.6
   */
  public static LocalDateTime parse(CharSequence text, String pattern) {
    return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
  }
}

package com.seepine.tool.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * 日期工具类
 *
 * @author seepine
 * @since 0.0.1
 */
public class LocalDateUtil {
  private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  /**
   * 判断a是否小于b
   *
   * @param a eg.2022-7-11
   * @param b eg.2022-7-12
   * @return a是否小于b eg.true
   */
  public static boolean isLt(LocalDate a, LocalDate b) {
    return a.isBefore(b);
  }
  /**
   * 判断a是否小于等于b
   *
   * @param a eg.2022-7-11
   * @param b eg.2022-7-12
   * @return a是否小于等于b eg.true
   */
  public static boolean isLe(LocalDate a, LocalDate b) {
    // 也就是b是否大于a
    return isGt(b, a);
  }
  /**
   * 判断a是否大于b
   *
   * @param a eg.2022-7-15
   * @param b eg.2022-7-12
   * @return a是否大于b eg.true
   */
  public static boolean isGt(LocalDate a, LocalDate b) {
    return a.isAfter(b);
  }
  /**
   * 判断a是否大于等于b
   *
   * @param a eg.2022-7-15
   * @param b eg.2022-7-12
   * @return a是否大于等于b eg.true
   */
  public static boolean isGe(LocalDate a, LocalDate b) {
    // 也就是b是否小于a
    return isLt(b, a);
  }

  /**
   * 使用yyyy-MM-dd格式化
   *
   * @param date 日期
   * @return 2020-11-11
   */
  public static String format(LocalDate date) {
    return date.format(DF);
  }

  /**
   * 使用dateTimeFormatter格式化
   *
   * @param date 日期
   * @return dateTimeFormatter配置的格式
   * @since 0.2.5
   */
  public static String format(LocalDate date, DateTimeFormatter dateTimeFormatter) {
    return date.format(dateTimeFormatter);
  }
  /**
   * 使用pattern格式化，例如yyyyMMdd
   *
   * @param date 日期
   * @return pattern配置的格式
   * @since 0.2.5
   */
  public static String format(LocalDate date, String pattern) {
    return date.format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 字符串转对象,使用默认yyyy-MM-dd格式
   *
   * @param text 2023-01-01
   * @return 对象
   * @since 0.2.6
   */
  public static LocalDate parse(CharSequence text) {
    return LocalDate.parse(text, DF);
  }

  /**
   * 字符串转对象
   *
   * @param text 2023/01/01
   * @param pattern yyyy/MM/dd
   * @return 对象
   * @since 0.2.6
   */
  public static LocalDate parse(CharSequence text, String pattern) {
    return LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
  }
}

package com.seepine.tool.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
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

  public static String format(LocalDate date) {
    return date.format(DF);
  }
}

package com.seepine.tool;

import com.seepine.tool.exception.RunException;
import com.seepine.tool.util.ObjectUtil;
import com.seepine.tool.util.StrUtil;

/**
 * @author seepine
 * @since 0.0.1
 */
public class Run {

  /***
   * 获取值，不为blank则返回str，否则返回defaultStr
   * @param str 值
   * @param defaultStr 默认值
   * @return string
   */
  public static String require(String str, String defaultStr) {
    if (StrUtil.isNotBlank(str)) {
      return str;
    }
    return defaultStr;
  }
  /**
   * 必须为empty，否则抛出异常，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param message the exception message to use if the assertion fails
   */
  public static void isEmpty(Object obj, String message) {
    if (ObjectUtil.isNotEmpty(obj)) {
      throw new RunException(message);
    }
  }
  /**
   * 必须不为empty，否则抛出异常，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param message the exception message to use if the assertion fails
   */
  public static <T> T notEmpty(T obj, String message) {
    if (ObjectUtil.isEmpty(obj)) {
      throw new RunException(message);
    }
    return obj;
  }
  /**
   * 为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param apply 方法
   */
  public static void isEmpty(Object obj, Apply apply) {
    if (ObjectUtil.isEmpty(obj)) {
      apply.run();
    }
  }
  /**
   * 不为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param apply 方法(传入对象)
   */
  public static <T> void notEmpty(T obj, ApplyWith<T> apply) {
    if (!ObjectUtil.isEmpty(obj)) {
      apply.run(obj);
    }
  }
  /**
   * 必须为true，否则抛出异常
   *
   * @param condition 对象
   * @param message the exception message to use if the assertion fails
   */
  public static void isTrue(Boolean condition, String message) {
    if (!Boolean.TRUE.equals(condition)) {
      throw new RunException(message);
    }
  }
  /**
   * 必须为false，否则抛出异常
   *
   * @param condition 对象
   * @param message the exception message to use if the assertion fails
   */
  public static void isFalse(Boolean condition, String message) {
    if (!Boolean.FALSE.equals(condition)) {
      throw new RunException(message);
    }
  }
  /**
   * 必须为blank，否则则抛异常，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param message the exception message to use if the assertion fails
   */
  public static void isBlank(CharSequence str, String message) {
    if (StrUtil.isNotBlank(str)) {
      throw new RunException(message);
    }
  }
  /**
   * 必须不为blank，否则则抛出异常，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param message the exception message to use if the assertion fails
   */
  public static String notBlank(String str, String message) {
    if (StrUtil.isBlank(str)) {
      throw new RunException(message);
    }
    return str;
  }
  /**
   * 为blank则执行方法，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param apply 方法
   */
  public static void isBlank(CharSequence str, Apply apply) {
    if (StrUtil.isBlank(str)) {
      apply.run();
    }
  }
  /**
   * 不为blank则执行方法，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param apply 方法(传入字符)
   */
  public static void notBlank(String str, ApplyWith<String> apply) {
    if (StrUtil.isNotBlank(str)) {
      apply.run(str);
    }
  }
  /**
   * 必须不为blank，符合则去除首尾空格并返回，为blank则抛出异常，为blank则抛异常，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param message the exception message to use if the assertion fails
   */
  public static String notBlankAndTrim(String str, String message) {
    if (StrUtil.isBlank(str)) {
      throw new RunException(message);
    }
    return str.trim();
  }
  /**
   * 不为blank则执行方法且传入去除首尾空格后的值，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param apply 方法(传入字符.trim())
   */
  public static void notBlankAndTrim(String str, ApplyWith<String> apply) {
    if (StrUtil.isNotBlank(str)) {
      apply.run(str.trim());
    }
  }

  public interface Apply {
    void run();
  }

  public interface ApplyWith<T> {
    void run(T val);
  }
}

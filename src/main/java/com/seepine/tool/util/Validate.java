package com.seepine.tool.util;

import com.seepine.tool.exception.ValidateRunException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 校验工具类
 *
 * @since 1.1.0
 */
public class Validate {
  /**
   * 必须为null，不为则抛异常
   *
   * @param o 对象
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void isNull(@Nullable Object o, @Nonnull String tip) throws ValidateRunException {
    if (o != null) {
      throw new ValidateRunException(tip);
    }
  }

  /**
   * 必须不为null，为则抛异常
   *
   * @param o 对象
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  @Nonnull
  public static <T> T nonNull(@Nullable T o, @Nonnull String tip) throws ValidateRunException {
    if (o == null) {
      throw new ValidateRunException(tip);
    }
    return o;
  }
  /**
   * 必须为空，不为则抛异常，空为null或""或[]
   *
   * @param o 对象
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void isEmpty(@Nullable Object o, @Nonnull String tip) throws ValidateRunException {
    if (Objects.nonEmpty(o)) {
      throw new ValidateRunException(tip);
    }
  }
  /**
   * 必须不为空，为则抛异常，空为null或""或[]
   *
   * @param o 对象
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  @Nonnull
  public static <T> T nonEmpty(@Nullable T o, @Nonnull String tip) throws ValidateRunException {
    if (o == null || Objects.isEmpty(o)) {
      throw new ValidateRunException(tip);
    }
    return o;
  }
  /**
   * 必须为blank，不为则抛异常，blank为null或""
   *
   * @param str 对象
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void isBlank(@Nullable String str, @Nonnull String tip)
      throws ValidateRunException {
    if (Objects.nonBlank(str)) {
      throw new ValidateRunException(tip);
    }
  }
  /**
   * 必须不为blank，为则抛异常，blank为null或""
   *
   * @param str 对象
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  @Nonnull
  public static String nonBlank(@Nullable String str, @Nonnull String tip)
      throws ValidateRunException {
    if (str == null || Objects.isBlank(str)) {
      throw new ValidateRunException(tip);
    }
    return str;
  }
  /**
   * 必须为true，不为则抛异常
   *
   * @param condition 条件
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void isTrue(boolean condition, @Nonnull String tip) throws ValidateRunException {
    if (!condition) {
      throw new ValidateRunException(tip);
    }
  }
  /**
   * 必须不为true，为则抛异常
   *
   * @param condition 条件
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void nonTrue(boolean condition, @Nonnull String tip) throws ValidateRunException {
    if (condition) {
      throw new ValidateRunException(tip);
    }
  }
  /**
   * 必须为false，不为则抛异常
   *
   * @param condition 条件
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void isFalse(boolean condition, @Nonnull String tip) throws ValidateRunException {
    nonTrue(condition, tip);
  }
  /**
   * 必须不为false，为则抛异常
   *
   * @param condition 条件
   * @param tip 错误信息
   * @throws ValidateRunException 校验不通过异常
   */
  public static void nonFalse(boolean condition, @Nonnull String tip) throws ValidateRunException {
    isTrue(condition, tip);
  }
}

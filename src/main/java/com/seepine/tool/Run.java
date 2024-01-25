package com.seepine.tool;

import com.seepine.tool.exception.ValidateRunException;
import com.seepine.tool.function.NonnullConsumer;
import com.seepine.tool.util.Objects;
import com.seepine.tool.util.Validate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author seepine
 * @since 0.0.1
 */
public class Run {
  /**
   * 为null则执行方法
   *
   * @param obj 对象
   * @param action 方法(传入对象)
   */
  public static void isNull(@Nullable Object obj, @Nonnull Runnable action) {
    if (Objects.isNull(obj)) {
      action.run();
    }
  }
  /**
   * 不为null则执行方法
   *
   * @param obj 对象
   * @param action 方法(传入对象)
   */
  public static void nonNull(@Nullable Object obj, @Nonnull Runnable action) {
    if (Objects.nonNull(obj)) {
      action.run();
    }
  }
  /**
   * 不为null则执行方法
   *
   * @param obj 对象
   * @param consumer 方法(传入对象)
   */
  public static <T> void nonNull(@Nullable T obj, @Nonnull NonnullConsumer<T> consumer) {
    if (Objects.nonNull(obj)) {
      consumer.accept(obj);
    }
  }
  /**
   * 为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param action 方法(传入对象)
   */
  public static void isEmpty(@Nullable Object obj, @Nonnull Runnable action) {
    if (Objects.isEmpty(obj)) {
      action.run();
    }
  }
  /**
   * 不为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param action 方法(传入对象)
   */
  public static void nonEmpty(@Nullable Object obj, @Nonnull Runnable action) {
    if (Objects.nonEmpty(obj)) {
      action.run();
    }
  }
  /**
   * 不为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param consumer 方法(传入对象)
   */
  public static <T> void nonEmpty(@Nullable T obj, @Nonnull NonnullConsumer<T> consumer) {
    if (Objects.nonEmpty(obj)) {
      consumer.accept(obj);
    }
  }
  /**
   * 为blank则执行方法，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param action 方法
   */
  public static void isBlank(@Nullable CharSequence str, @Nonnull Runnable action) {
    if (Objects.isBlank(str)) {
      action.run();
    }
  }
  /**
   * 不为blank则执行方法，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param consumer 方法(传入字符)
   */
  public static void nonBlank(@Nullable String str, @Nonnull NonnullConsumer<String> consumer) {
    if (Objects.nonBlank(str)) {
      consumer.accept(str);
    }
  }
  /**
   * 不为blank则执行方法且传入去除首尾空格后的值，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param consumer 方法(传入字符.trim())
   */
  public static void nonBlankAndTrim(
      @Nullable String str, @Nonnull NonnullConsumer<String> consumer) {
    if (Objects.nonBlank(str)) {
      consumer.accept(str.trim());
    }
  }

  @Deprecated
  public static <T> T notEmpty(@Nullable T o, @Nonnull String tip) throws ValidateRunException {
    return Validate.nonEmpty(o, tip);
  }

  @Deprecated
  public static void notEmpty(@Nullable Object obj, @Nonnull Runnable action) {
    nonEmpty(obj, action);
  }

  @Deprecated
  public static <T> void notEmpty(@Nullable T obj, @Nonnull NonnullConsumer<T> consumer) {
    nonEmpty(obj, consumer);
  }

  @Deprecated
  public static String nonBlank(@Nullable String str, @Nonnull String tip) {
    return Validate.nonBlank(str, tip);
  }

  @Deprecated
  public static void notBlank(@Nullable String str, @Nonnull NonnullConsumer<String> consumer) {
    nonBlank(str, consumer);
  }

  @Deprecated
  public static void isTrue(boolean condition, @Nonnull String tip) throws ValidateRunException {
    Validate.isTrue(condition, tip);
  }

  @Deprecated
  public static void isFalse(boolean condition, @Nonnull String tip) throws ValidateRunException {
    Validate.isFalse(condition, tip);
  }
}

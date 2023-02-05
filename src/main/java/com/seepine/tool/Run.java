package com.seepine.tool;

import com.seepine.tool.util.Objects;

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
   * @param apply 方法(传入对象)
   */
  public static void isNull(@Nullable Object obj, @Nonnull Apply apply) {
    if (Objects.isNull(obj)) {
      apply.run();
    }
  }
  /**
   * 不为null则执行方法
   *
   * @param obj 对象
   * @param apply 方法(传入对象)
   */
  public static void nonNull(@Nullable Object obj, @Nonnull Apply apply) {
    if (Objects.nonNull(obj)) {
      apply.run();
    }
  }
  /**
   * 不为null则执行方法
   *
   * @param obj 对象
   * @param apply 方法(传入对象)
   */
  public static <T> void nonNull(@Nullable T obj, @Nonnull ApplyWith<T> apply) {
    if (Objects.nonNull(obj)) {
      apply.run(obj);
    }
  }
  /**
   * 为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param apply 方法(传入对象)
   */
  public static void isEmpty(@Nullable Object obj, @Nonnull Apply apply) {
    if (Objects.isEmpty(obj)) {
      apply.run();
    }
  }
  /**
   * 不为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param apply 方法(传入对象)
   */
  public static void nonEmpty(@Nullable Object obj, @Nonnull Apply apply) {
    if (Objects.nonEmpty(obj)) {
      apply.run();
    }
  }
  /**
   * 不为empty则执行方法，empty包含null、数组队列空等情况
   *
   * @param obj 对象
   * @param apply 方法(传入对象)
   */
  public static <T> void nonEmpty(@Nullable T obj, @Nonnull ApplyWith<T> apply) {
    if (Objects.nonEmpty(obj)) {
      apply.run(obj);
    }
  }
  /**
   * 为blank则执行方法，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param apply 方法
   */
  public static void isBlank(@Nullable CharSequence str, @Nonnull Apply apply) {
    if (Objects.isBlank(str)) {
      apply.run();
    }
  }
  /**
   * 不为blank则执行方法，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param apply 方法(传入字符)
   */
  public static void nonBlank(@Nullable String str, @Nonnull ApplyWith<String> apply) {
    if (Objects.nonBlank(str)) {
      apply.run(str);
    }
  }
  /**
   * 不为blank则执行方法且传入去除首尾空格后的值，blank包含null、""、" "等情况
   *
   * @param str 字符
   * @param apply 方法(传入字符.trim())
   */
  public static void nonBlankAndTrim(@Nullable String str, @Nonnull ApplyWith<String> apply) {
    if (Objects.nonBlank(str)) {
      apply.run(str.trim());
    }
  }

  public interface Apply {
    void run();
  }

  public interface ApplyWith<T> {
    void run(@Nonnull T val);
  }
}

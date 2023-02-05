package com.seepine.tool.util;

import com.seepine.tool.exception.BlankStringRunException;
import com.seepine.tool.function.NonnullSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 用于扩展jdk的Objects工具类
 *
 * @author seepine
 * @since 0.2.0
 */
public class Objects {
  /**
   * 判断是否相等
   *
   * @param a 对象a
   * @param b 对象b
   * @return true表示相等
   */
  public static boolean equals(@Nullable Object a, @Nullable Object b) {
    return java.util.Objects.equals(a, b);
  }
  /**
   * 深判断是否相等，与equals不同的是一般用来判断数组类型
   *
   * @param a 对象a
   * @param b 对象b
   * @return true表示相等
   */
  public static boolean deepEquals(@Nullable Object a, @Nullable Object b) {
    return java.util.Objects.deepEquals(a, b);
  }
  /**
   * 计算hash值
   *
   * @param o 对象
   * @return hash值
   */
  public static int hashCode(@Nullable Object o) {
    return java.util.Objects.hashCode(o);
  }
  /**
   * 计算多个对象hash值
   *
   * @param values 对象数组
   * @return hash值
   */
  public static int hashCode(@Nullable Object... values) {
    return java.util.Objects.hash(values);
  }
  /**
   * 输出，null则输出"null"，非null则调用对象的toString()
   *
   * @param o 对象
   * @return 输出
   */
  @Nonnull
  public static String toString(@Nullable Object o) {
    return java.util.Objects.toString(o);
  }
  /**
   * 输出，null则输出nullDefaultString，非null则调用对象的toString()
   *
   * @param o 对象
   * @param nullDefaultString 为null时要输出的内容
   * @return 输出
   */
  @Nonnull
  public static String toString(@Nullable Object o, @Nonnull String nullDefaultString) {
    return java.util.Objects.toString(o, nullDefaultString);
  }
  /**
   * a是否大于b
   *
   * @param a 对象a
   * @param b 对象b
   * @return true表示a大于b，false表示a小于或等于b
   * @param <T> 对象需继承Comparable接口
   */
  public static <T extends Comparable<T>> boolean gt(@Nullable T a, @Nullable T b) {
    return compare(a, b) == 1;
  }
  /**
   * a是否小于b
   *
   * @param a 对象a
   * @param b 对象b
   * @return true表示a小于b，false表示a大于或等于b
   * @param <T> 对象需继承Comparable接口
   */
  public static <T extends Comparable<T>> boolean lt(@Nullable T a, @Nullable T b) {
    return compare(a, b) == -1;
  }
  /**
   * 比大小，都为null则相等，若a为null而b不为，则a小
   *
   * @param a 对象a
   * @param b 对象b
   * @return a>b则1，a=b则0，a<b则-1
   * @param <T> 对象需继承Comparable接口
   */
  public static <T extends Comparable<T>> int compare(@Nullable T a, @Nullable T b) {
    return compare(
        a,
        b,
        (o1, o2) -> {
          if (o1 == o2) {
            return 0;
          }
          if (o1 == null) {
            return -1;
          }
          if (o2 == null) {
            return 1;
          }
          return o1.compareTo(o2);
        });
  }
  /**
   * 比大小
   *
   * @param a 对象a
   * @param b 对象b
   * @param comparator 比较器
   * @return a>b则1，a=b则0，a<b则-1
   */
  public static <T> int compare(
      @Nullable T a, @Nullable T b, @Nonnull Comparator<? super T> comparator) {
    return java.util.Objects.compare(a, b, comparator);
  }
  // ================= require =================
  /***
   * 获取值，不为empty则返回val，否则返回defaultVal
   * @param val 值
   * @param defaultVal 默认值
   * @return string
   */
  @Nonnull
  public static <T> T require(@Nullable T val, @Nonnull T defaultVal) {
    return isEmpty(val) ? defaultVal : val;
  }
  /***
   * 获取值，不为empty则返回val，否则返回()->defaultVal
   * @param val 值
   * @param defaultValSupplier 默认值供应者
   * @return string
   */
  @Nonnull
  public static <T> T require(@Nullable T val, @Nonnull NonnullSupplier<T> defaultValSupplier) {
    return val == null || isEmpty(val) ? defaultValSupplier.get() : val;
  }
  // ================= requireNonBlank =================
  @Nonnull
  public static String requireNonBlank(@Nullable String val, @Nonnull String defaultValSupplier)
      throws BlankStringRunException {
    return requireNonBlank(val, () -> defaultValSupplier);
  }
  /**
   * 获取值，空串则抛异常
   *
   * @param val 值
   * @return 值
   */
  @Nonnull
  public static String requireNonBlank(
      @Nullable String val, @Nonnull NonnullSupplier<String> defaultValSupplier)
      throws BlankStringRunException {
    return val == null || isBlank(val) ? defaultValSupplier.get() : val;
  }
  // ================= null/empty/blank =================
  /**
   * 是否为空，空定义为null
   *
   * @param obj 对象
   * @return 是否为空，true表示为空，false表示不为空
   */
  public static boolean isNull(@Nullable Object obj) {
    return obj == null;
  }
  /**
   * 是否不为空，空定义为null
   *
   * @param obj 对象
   * @return 是否不为空，true表示不为空，false表示为空
   */
  public static boolean nonNull(@Nullable Object obj) {
    return obj != null;
  }
  /**
   * 是否为空，空定义为null或""或[]等等
   *
   * @param obj 对象
   * @return 是否为空，true表示为空，false表示不为空
   */
  public static boolean isEmpty(@Nullable Object obj) {
    if (null == obj) {
      return true;
    }
    if (obj instanceof CharSequence) {
      return ((CharSequence) obj).length() == 0;
    } else if (obj instanceof List) {
      return ((List<?>) obj).isEmpty();
    } else if (obj instanceof Map) {
      return ((Map<?, ?>) obj).isEmpty();
    } else if (obj instanceof Iterable) {
      return !((Iterable<?>) obj).iterator().hasNext();
    } else if (obj instanceof Iterator) {
      return !((Iterator<?>) obj).hasNext();
    } else if (obj.getClass().isArray()) {
      return 0 == Array.getLength(obj);
    }
    return false;
  }
  /**
   * 是否不为空，空定义为null或""或[]等等
   *
   * @param obj 对象
   * @return 是否不为空，true表示不为空，false表示为空
   */
  public static boolean nonEmpty(@Nullable Object obj) {
    return !isEmpty(obj);
  }
  /**
   * 是否为空白 例如null,""," ","\n" 例： StrUtil.isBlank(null) // true StrUtil.isBlank("") // true
   * StrUtil.isBlank(" ") // true StrUtil.isBlank(" \t\n") // true StrUtil.isBlank("abc") // false
   *
   * @param str 字符串
   * @return 是否为空白
   */
  public static boolean isBlank(@Nullable CharSequence str) {
    return StrUtil.isBlank(str);
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
}

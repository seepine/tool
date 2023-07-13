package com.seepine.tool.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author seepine
 * @since 0.0.2
 */
public class ListUtil {
  /**
   * 将List<?>转换为List<T>
   *
   * @param obj 对象
   * @param clazz 转换结果类
   * @return List<T>
   * @param <T> 结果类
   */
  @Nonnull
  public static <T> List<T> castList(@Nonnull Object obj, @Nonnull Class<T> clazz) {
    List<T> result = new ArrayList<>();
    if (obj instanceof List<?>) {
      for (Object o : (List<?>) obj) {
        result.add(clazz.cast(o));
      }
    }
    return result;
  }
  /**
   * list map
   *
   * @param origin origin
   * @param mapper mapper
   * @param <T> T
   * @param <R> R
   * @return new list
   */
  @Nonnull
  public static <T, R> List<R> map(
      @Nullable List<T> origin, @Nonnull Function<? super T, ? extends R> mapper) {
    return origin == null || Objects.isEmpty(origin)
        ? new ArrayList<>()
        : origin.stream().map(mapper).collect(Collectors.toList());
  }
  /**
   * list filter
   *
   * @param origin origin
   * @param predicate predicate
   * @param <T> T
   * @return new list
   */
  @Nonnull
  public static <T> List<T> filter(
      @Nullable List<T> origin, @Nonnull Predicate<? super T> predicate) {
    return origin == null || Objects.isEmpty(origin)
        ? new ArrayList<>()
        : origin.stream().filter(predicate).collect(Collectors.toList());
  }

  /**
   * 将数组转化为list
   *
   * @param arr 数组
   * @return list
   * @param <T> T
   * @since 0.2.0
   */
  @Nonnull
  @SuppressWarnings("unchecked")
  public static <T> List<T> fromArray(@Nonnull T... arr) {
    List<T> list = new ArrayList<>();
    Collections.addAll(list, arr);
    return list;
  }

  /**
   * 针对List排序，排序会修改原List
   *
   * @param <T> 元素类型
   * @param list 被排序的List
   * @param c {@link Comparator}
   * @return 原list
   * @see Collections#sort(List, Comparator)
   */
  @Nonnull
  public static <T> List<T> sort(@Nullable List<T> list, @Nonnull Comparator<? super T> c) {
    if (Objects.isEmpty(list)) {
      return new ArrayList<>();
    }
    list.sort(c);
    return list;
  }
}

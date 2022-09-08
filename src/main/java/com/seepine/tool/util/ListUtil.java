package com.seepine.tool.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
  public static <T> List<T> castList(Object obj, Class<T> clazz) {
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
   * @param origin prigin
   * @param mapper mapper
   * @param <T> T
   * @param <R> R
   * @return new list
   */
  public static <T, R> List<R> map(List<T> origin, Function<? super T, ? extends R> mapper) {
    if (ObjectUtil.isEmpty(origin)) {
      return new ArrayList<>();
    }
    return origin.stream().map(mapper).collect(Collectors.toList());
  }
  /**
   * list filter
   *
   * @param origin origin
   * @param predicate predicate
   * @param <T> T
   * @return new list
   */
  public static <T> List<T> filter(List<T> origin, Predicate<? super T> predicate) {
    if (ObjectUtil.isEmpty(origin)) {
      return new ArrayList<>();
    }
    return origin.stream().filter(predicate).collect(Collectors.toList());
  }
}

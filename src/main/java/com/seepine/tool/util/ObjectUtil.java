package com.seepine.tool.util;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * @author seepine
 * @since 0.0.1
 */
public class ObjectUtil {
  /**
   * 是否为空，空定义为null或""或[]等等
   *
   * @param obj 对象
   * @return 是否为空
   */
  public static boolean isEmpty(Object obj) {
    if (null == obj) {
      return true;
    }
    if (obj instanceof CharSequence) {
      return StrUtil.isEmpty((CharSequence) obj);
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
   * 是否不为空
   *
   * @param obj 对象
   * @return 是否不为空
   */
  public static boolean isNotEmpty(Object obj) {
    return !isEmpty(obj);
  }
}

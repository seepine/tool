package com.seepine.tool.util;

/**
 * @author seepine
 * @since 0.0.1
 * @deprecated please use {@link com.seepine.tool.util.Objects Objects}
 */
@Deprecated
public class ObjectUtil {

  /**
   * 是否为空，空定义为null或""或[]等等
   *
   * @param obj 对象
   * @return 是否为空
   */
  public static boolean isEmpty(Object obj) {
    return Objects.isEmpty(obj);
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

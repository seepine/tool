package com.seepine.tool.util;

import com.seepine.tool.function.ExceptionSupplier;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 重试工具类，例如用于请求某接口，失败则重试
 *
 * @author seepine
 * @since 0.3.9
 */
public class Retry {
  /**
   * 重试执行体
   *
   * @param count 最大重试次数
   * @param millis 每次重试间隔
   * @param supplier 方法,当返回null或异常视为失败
   * @return 获得结果
   * @param <T> 结果类型
   */
  @Nullable
  public static <T> T run(
      @Nonnegative int count, @Nonnegative int millis, @Nonnull ExceptionSupplier<T> supplier) {
    int num = 0;
    while (num < count) {
      try {
        T res = supplier.get();
        if (res != null) {
          return res;
        }
      } catch (Exception e) {
        try {
          Thread.sleep(millis);
        } catch (InterruptedException ex) {
          throw new RuntimeException(ex);
        }
      }
      num++;
    }
    return null;
  }
}

package com.seepine.tool.lock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author seepine
 * @since 0.1.1
 */
public interface LockService {
  /**
   * 锁逻辑
   *
   * @param key 锁值
   * @param supplier 供应者
   * @return supplier.get()
   * @param <T> 供应者泛型
   */
  @Nullable
  <T> T lock(@Nonnull String key, @Nonnull Supplier<T> supplier);
}

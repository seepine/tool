package com.seepine.tool.lock;

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
  <T> T lock(String key, Supplier<T> supplier);
}

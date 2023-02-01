package com.seepine.tool.lock;

import com.seepine.tool.function.VoidSupplier;

import java.util.function.Supplier;

/**
 * @author seepine
 * @since 0.1.1
 */
public class Lock {
  private static LockService lock = new LockSynchronizedImpl();

  /**
   * 增强实现类，例如用redisson支持分布式锁
   *
   * @param lockImpl impl
   */
  public static void enhance(LockService lockImpl) {
    sync(lock.getClass().getName(), () -> lock = lockImpl);
  }
  /**
   * 锁定运行
   *
   * @param key 锁值
   * @param voidSupplier 执行方法
   */
  public static void sync(Object key, VoidSupplier voidSupplier) {
    sync(
        key,
        () -> {
          voidSupplier.run();
          return null;
        });
  }
  /**
   * 锁定运行，有返回值
   *
   * @param key 锁值
   * @param supplier 执行方法
   * @param <T> 返回值类型
   * @return 返回值
   */
  public static <T> T sync(Object key, Supplier<T> supplier) {
    return lock.lock(key.toString(), supplier);
  }
}

package com.seepine.tool.lock;

import com.seepine.tool.function.NonnullSupplier;
import com.seepine.tool.function.VoidSupplier;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * @author seepine
 * @since 0.1.1
 */
public class Lock {
  private Lock() {
    throw new AssertionError("No com.seepine.tool.lock.Lock instances for you!");
  }

  private static LockService lock = new LockSynchronizedImpl();

  /**
   * 增强实现类，例如用redisson支持分布式锁
   *
   * @param lockImpl impl
   */
  public static void enhance(@Nonnull LockService lockImpl) {
    sync(lock.getClass().getName(), () -> lock = lockImpl);
  }
  /**
   * 锁定运行
   *
   * @param key 锁值
   * @param voidSupplier 执行方法
   */
  public static void sync(@Nonnull Object key, @Nonnull VoidSupplier voidSupplier) {
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
  public static <T> T sync(@Nonnull Object key, @Nonnull Supplier<T> supplier) {
    return lock.lock(key.toString(), supplier);
  }
  /**
   * 锁定运行，有返回值
   *
   * @param key 锁值
   * @param supplier 执行方法
   * @param <T> 返回值类型
   * @return 返回值
   */
  public static <T> T syncNonNull(@Nonnull Object key, @Nonnull NonnullSupplier<T> supplier) {
    return lock.lock(key.toString(), supplier::get);
  }
}

package com.seepine.tool.cache;

import com.seepine.tool.function.NonnullSupplier;
import com.seepine.tool.util.CurrentTimeMillis;
import com.seepine.tool.util.Validate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 过期缓存
 *
 * @author seepine
 */
public class ExpireCache<T> {
  private final ConcurrentMap<String, DelayValue<T>> map = new ConcurrentHashMap<>();
  /** 2-6个核心 */
  private static final ScheduledExecutorService EXECUTOR =
      Executors.newScheduledThreadPool(
          Math.min(Math.max(Runtime.getRuntime().availableProcessors(), 2), 6));
  /** 永久缓存标记 */
  public static final long FOREVER_FLAG = 0;
  /**
   * 获取缓存数量
   *
   * @return the number of key-value mappings in this map
   */
  public int size() {
    return map.size();
  }

  /**
   * 主动移除
   *
   * @param key key whose mapping is to be removed from the map
   * @return the previous value associated with key, or null if there was no mapping for key.
   */
  @Nullable
  public T remove(@Nonnull String key) {
    DelayValue<T> delayValue = map.remove(key);
    if (delayValue == null) {
      return null;
    }
    return delayValue.isExpired() ? null : delayValue.data;
  }
  /**
   * key是否存在
   *
   * @param key 键
   * @return bool
   */
  public boolean containsKey(@Nonnull String key) {
    return map.containsKey(key);
  }
  /**
   * 放入一个不过期 k v
   *
   * @param key 键
   * @param value 值
   */
  public void put(@Nonnull String key, @Nonnull T value) {
    put(key, value, FOREVER_FLAG);
  }

  /**
   * 放入一个可过期 k v
   *
   * @param key 键
   * @param value 值
   * @param delayMillisecond 过期时间，单位毫秒
   */
  public void put(@Nonnull String key, @Nonnull T value, @Nonnegative long delayMillisecond) {
    Validate.nonBlank(key, "invalid key, cannot be blank");
    Validate.nonNull(value, "invalid value, cannot be null");
    Validate.isGe(
        delayMillisecond, FOREVER_FLAG, "invalid delayMillisecond, must be greater than zero");
    map.put(key, new DelayValue<>(value, delayMillisecond));
    addClearTimer(key, delayMillisecond);
  }

  /**
   * 取值的时候判断值是否过期
   *
   * @param key key
   * @return 值或null（如果key不存在或过期的话）
   */
  @Nullable
  public T get(@Nonnull String key) {
    DelayValue<T> v = map.get(key);
    if (Objects.isNull(v)) {
      return null;
    }
    return v.isExpired() ? null : v.data;
  }
  /**
   * 取值,若为null则赋值默认值
   *
   * @param key key
   * @return 值
   */
  @Nonnull
  public T get(@Nonnull String key, @Nonnull T defaultValue) {
    return get(key, () -> defaultValue, FOREVER_FLAG);
  }
  /**
   * 取值,若为null则赋值默认值
   *
   * @param key key
   * @return 值
   */
  @Nonnull
  public T get(@Nonnull String key, @Nonnull NonnullSupplier<T> func) {
    return get(key, func, FOREVER_FLAG);
  }
  /**
   * 取值，若为null则赋值默认值及过期毫秒
   *
   * @param key key
   * @return 值
   */
  @Nonnull
  public T get(
      @Nonnull String key, @Nonnull NonnullSupplier<T> func, @Nonnegative long delayMillisecond) {
    T value = get(key);
    if (Objects.isNull(value)) {
      value = func.get();
    }
    put(key, func.get(), delayMillisecond);
    return value;
  }

  private void addClearTimer(@Nonnull String key, @Nonnegative long delayMillisecond) {
    if (delayMillisecond > 0) {
      EXECUTOR.schedule(
          () -> {
            DelayValue<T> delayValue = map.get(key);
            if (delayValue != null && delayValue.isExpired()) {
              map.remove(key);
            }
          },
          delayMillisecond,
          TimeUnit.MILLISECONDS);
    }
  }

  private static class DelayValue<T> {
    // 值
    final T data;
    // -1表示永不过期
    final long delayMillisecond;
    // 当前时间戳
    final long timestamp = CurrentTimeMillis.now();

    DelayValue(@Nonnull T data) {
      this(data, FOREVER_FLAG);
    }

    DelayValue(@Nonnull T data, @Nonnegative long delayMillisecond) {
      this.data = data;
      this.delayMillisecond = delayMillisecond;
    }

    public boolean isExpired() {
      return delayMillisecond != FOREVER_FLAG
          && (CurrentTimeMillis.now() > delayMillisecond + timestamp);
    }

    @Override
    public String toString() {
      return "DelayValue [data=" + data + ", delayMillisecond=" + delayMillisecond + "]";
    }
  }
}

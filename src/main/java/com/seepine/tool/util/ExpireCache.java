package com.seepine.tool.util;

import com.seepine.tool.function.FunctionN;

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
  public static final long FOREVER_FLAG = -1;
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
  public T remove(String key) {
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
  public boolean containsKey(String key) {
    return map.containsKey(key);
  }
  /**
   * 放入一个不过期 k v
   *
   * @param key 键
   * @param value 值
   */
  public void put(String key, T value) {
    put(key, value, FOREVER_FLAG);
  }

  /**
   * 放入一个可过期 k v
   *
   * @param key 键
   * @param value 值
   * @param delayMillisecond 过期时间 单位毫秒
   */
  public void put(String key, T value, long delayMillisecond) {
    if (key == null) {
      throw new IllegalArgumentException("invalid key, cannot be null");
    }
    if (value == null) {
      throw new IllegalArgumentException("invalid value, cannot be null");
    }
    if (delayMillisecond > 0 || delayMillisecond == FOREVER_FLAG) {
      map.put(key, new DelayValue<>(value, delayMillisecond));
      addClearTimer(key, delayMillisecond);
    } else {
      throw new IllegalArgumentException("invalid delayMillisecond, must be greater than zero");
    }
  }

  /**
   * 取值的时候判断值是否过期
   *
   * @param key key
   * @return 值或null（如果key不存在或过期的话）
   */
  public T get(String key) {
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
  public T get(String key, FunctionN<T> func) {
    return get(key, func, FOREVER_FLAG);
  }
  /**
   * 取值，若为null则赋值默认值及过期毫秒
   *
   * @param key key
   * @return 值
   */
  public T get(String key, FunctionN<T> func, long delayMillisecond) {
    T value = get(key);
    if (Objects.isNull(value)) {
      value = func.apply();
    }
    put(key, func.apply(), delayMillisecond);
    return value;
  }

  private void addClearTimer(String key, long delayMillisecond) {
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

    DelayValue(T data) {
      this(data, FOREVER_FLAG);
    }

    DelayValue(T data, long delayMillisecond) {
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

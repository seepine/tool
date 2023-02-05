package com.seepine.tool.cache;

import com.seepine.tool.function.NonnullSupplier;
import com.seepine.tool.lock.Lock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * 缓存工具类
 *
 * @author seepine
 * @since 0.2.0
 */
public class Cache {
  private Cache() {
    throw new AssertionError("No com.seepine.tool.cache.Cache instances for you!");
  }

  private static CacheService cache = new CacheMemoryImpl();
  /**
   * 增强实现类，例如用redis支持分布式缓存
   *
   * @param cacheImpl impl
   */
  public static void enhance(@Nonnull CacheService cacheImpl) {
    Lock.sync(cache.getClass().getName(), () -> cache = cacheImpl);
  }
  /**
   * 获取缓存
   *
   * @param key key
   * @return value
   */
  @Nullable
  @SuppressWarnings("unchecked")
  public static <T> T get(@Nonnull String key) {
    Object value = cache.get(key);
    return Objects.isNull(value) ? null : (T) value;
  }

  /**
   * 获取缓存
   *
   * @param key key
   * @param defaultValue 当缓存为null时，将会返回该值并保存到缓存中
   * @param delayMillisecond 过期时间，毫秒
   * @return 值
   */
  public static <T> T get(
      @Nonnull String key, @Nonnull T defaultValue, @Nonnegative long delayMillisecond) {
    return get(key, () -> defaultValue, delayMillisecond);
  }
  /**
   * 获取缓存
   *
   * @param key key
   * @param supplier 当缓存为null时，将会返回该值并保存到缓存中
   * @param delayMillisecond 过期时间，毫秒
   * @return 值
   */
  @Nonnull
  public static <T> T get(
      @Nonnull String key,
      @Nonnull NonnullSupplier<T> supplier,
      @Nonnegative long delayMillisecond) {
    return Lock.sync(
        key,
        () -> {
          T value = get(key);
          if (Objects.isNull(value)) {
            value = supplier.get();
            set(key, value, delayMillisecond);
          }
          return value;
        });
  }
  /**
   * 获取字符串缓存
   *
   * @param key key
   * @return value
   */
  @Nullable
  public static String getStr(@Nonnull String key) {
    Object value = get(key);
    return value == null ? null : String.valueOf(value);
  }

  /**
   * 获取整型缓存
   *
   * @param key key
   * @return value
   */
  @Nullable
  public static Integer getInt(@Nonnull String key) {
    String value = getStr(key);
    return value == null ? null : Integer.valueOf(value);
  }

  /**
   * 获取长整形缓存
   *
   * @param key key
   * @return value
   */
  @Nullable
  public static Long getLong(@Nonnull String key) {
    String value = getStr(key);
    return value == null ? null : Long.valueOf(value);
  }

  /**
   * 移除缓存
   *
   * @param key key
   */
  @Nullable
  public static Object remove(@Nonnull String key) {
    return cache.remove(key);
  }

  /**
   * 设置缓存，默认无过期时间
   *
   * @param key key
   * @param value value
   */
  public static void set(@Nonnull String key, @Nonnull Object value) {
    cache.set(key, value, ExpireCache.FOREVER_FLAG);
  }

  /**
   * 设置缓存
   *
   * @param key key
   * @param value value
   * @param delayMillisecond 过期时间(毫秒)
   */
  public static void set(
      @Nonnull String key, @Nonnull Object value, @Nonnegative long delayMillisecond) {
    cache.set(key, value, delayMillisecond);
  }
}
package com.seepine.tool.cache;

import com.seepine.tool.function.NonnullSupplier;
import com.seepine.tool.lock.Lock;
import com.seepine.tool.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * 缓存工具类
 *
 * @author seepine
 * @since 0.2.0
 */
public class Cache {
  public static final long FOREVER = 0;

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
   * @return 值
   */
  @Nonnull
  public static <T> T get(@Nonnull String key, @Nonnull T defaultValue) {
    return get(key, () -> defaultValue);
  }
  /**
   * 获取缓存
   *
   * @param key key
   * @param supplier 当缓存为null时，将会返回该值并保存到缓存中
   * @return 值
   */
  @Nonnull
  public static <T> T get(@Nonnull String key, @Nonnull NonnullSupplier<T> supplier) {
    return get(key, supplier, FOREVER);
  }
  /**
   * 获取缓存
   *
   * @param key key
   * @param defaultValue 当缓存为null时，将会返回该值并保存到缓存中
   * @param delayMillisecond 过期时间(毫秒),0为无限期
   * @return 值
   */
  @Nonnull
  public static <T> T get(
      @Nonnull String key, @Nonnull T defaultValue, @Nonnegative long delayMillisecond) {
    return get(key, () -> defaultValue, delayMillisecond);
  }
  /**
   * 获取缓存
   *
   * @param key key
   * @param supplier 当缓存为null时，将会返回该值并保存到缓存中
   * @param delayMillisecond 过期时间(毫秒),0为无限期
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
   * 获取缓存，提供获取供应者，且当供应者提供值时，才缓存
   *
   * @code String str = Cache.getIfPresent("cacheKey", ()-> null , 5000) // 返回null，且不会缓存
   * @code String str = Cache.getIfPresent("cacheKey", ()-> "cacheValue" , 5000) //
   *     返回cacheValue，且会缓存值
   * @param key key
   * @param supplier 当缓存为null时，将会返回该值并保存到缓存中
   * @param delayMillisecond 过期时间(毫秒),0为无限期
   * @return 值
   * @since 2.2.4
   */
  @Nullable
  public static <T> T getIfPresent(
      @Nonnull String key, @Nonnull Supplier<T> supplier, @Nonnegative long delayMillisecond) {
    return Lock.sync(
        "cache:getIfPresent:" + key,
        () -> {
          T value = get(key);
          if (Objects.isNull(value)) {
            value = supplier.get();
            if (Objects.nonNull(value)) {
              set(key, value, delayMillisecond);
            }
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
    cache.set(key, value, FOREVER);
  }

  /**
   * 设置缓存
   *
   * @param key key
   * @param value value
   * @param delayMillisecond 过期时间(毫秒),0为无限期
   */
  public static void set(
      @Nonnull String key, @Nonnull Object value, @Nonnegative long delayMillisecond) {
    cache.set(key, value, delayMillisecond);
  }
}

package com.seepine.tool.cache;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 缓存接口
 *
 * @author seepine
 * @since 0.2.0
 */
public interface CacheService {
  /**
   * @param key 缓存key
   * @param value 缓存值
   * @param delayMillisecond 过期时间，单位毫秒，非负，当为0建议无过期时间
   */
  void set(@Nonnull String key, @Nonnull Object value, @Nonnegative long delayMillisecond);

  @Nullable
  Object get(@Nonnull String key);

  @Nullable
  Object remove(@Nonnull String key);
}

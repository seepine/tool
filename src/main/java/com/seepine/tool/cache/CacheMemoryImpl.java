package com.seepine.tool.cache;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 内存缓存实现类
 *
 * @author seepine
 * @since 0.2.0
 */
public class CacheMemoryImpl implements CacheService {
  private final ExpireCache<Object> expireCache = new ExpireCache<>();

  @Override
  public void set(@Nonnull String key, @Nonnull Object value, @Nonnegative long delayMillisecond) {
    expireCache.put(key, value, delayMillisecond);
  }

  @Nullable
  @Override
  public Object get(@Nonnull String key) {
    return expireCache.get(key);
  }

  @Nullable
  @Override
  public Object remove(@Nonnull String key) {
    return expireCache.remove(key);
  }

  @Override
  public long removeByPattern(@Nonnull String pattern) {
    return expireCache.removeByPattern(pattern);
  }
}

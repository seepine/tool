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

  /**
   * 获取缓存
   *
   * @param key 缓存key
   * @return 缓存值
   */
  @Nullable
  Object get(@Nonnull String key);

  /**
   * 移除缓存
   *
   * @param key 缓存key
   * @return 缓存值
   */
  @Nullable
  Object remove(@Nonnull String key);

  /**
   * 按规则删除，例如 dept*，删除所有dept开头的缓存
   *
   * @param pattern 规则值
   * @return 删除个数
   */
  long removeByPattern(@Nonnull String pattern);
}

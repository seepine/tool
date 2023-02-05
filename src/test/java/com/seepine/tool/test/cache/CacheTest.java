package com.seepine.tool.test.cache;

import com.seepine.tool.cache.Cache;

import java.util.concurrent.TimeUnit;

public class CacheTest {
  public static void main(String[] args) {
    Cache.set("test", "test value", 1500);
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException ignore) {
    }
    // test value,因为还没过期
    System.out.println(Cache.getStr("test"));

    try {
      TimeUnit.MILLISECONDS.sleep(600);
    } catch (InterruptedException ignore) {
    }
    // null,因为过期了
    System.out.println(Cache.getStr("test"));

    // new test value,因为过期了，所以取默认填充的值
    System.out.println(Cache.get("test", () -> "new test value", 0));

    // new test value,因为上次默认值保存到缓存了没过期
    System.out.println(Cache.get("test", () -> "more new value", 0));

    // 可实现CacheService重写缓存逻辑例如缓存到redis等，并在程序初始化时调用Cache.enhance进行增强
    // 而后的Cache.get/Cache.set都会走自定义的CacheService
    // Cache.enhance(new CacheService() {});
  }
}

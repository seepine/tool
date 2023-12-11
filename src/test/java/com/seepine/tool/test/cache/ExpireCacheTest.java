package com.seepine.tool.test.cache;

import com.seepine.tool.cache.ExpireCache;
import java.util.concurrent.TimeUnit;

public class ExpireCacheTest {
  public static void main(String[] args) {
    ExpireCache<String> cache = new ExpireCache<>();
    cache.put("test", "test value", 1500);
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException ignore) {
    }
    System.out.println(cache.get("test"));
    try {
      TimeUnit.MILLISECONDS.sleep(600);
    } catch (InterruptedException ignore) {
    }
    System.out.println(cache.get("test"));
    System.out.println(cache.get("test", () -> "new test value"));

    cache.put("pig", "I am a pig", 1000);
    cache.put("pig", "I am a new pig", 5000);
    try {
      TimeUnit.MILLISECONDS.sleep(2000);
    } catch (InterruptedException ignore) {
    }
    System.out.println(cache.get("pig"));

    cache.removeAll();
    cache.put("abc", "abc");
    cache.put("abb", "abb");
    cache.put("acc", "acc");
    System.out.println("cache size:" + cache.size());
    int count = cache.removeByPattern("ab*");
    System.out.println("remove size:" + count);
    System.out.println("cache size:" + cache.size());
  }
}

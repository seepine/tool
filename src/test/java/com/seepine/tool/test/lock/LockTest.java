package com.seepine.tool.test.lock;

import com.seepine.tool.lock.Lock;
import com.seepine.tool.lock.LockService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

public class LockTest {
  public static void main(String[] args) {
    Lock.sync(1, () -> System.out.println("run end 1"));
    String str =
        Lock.sync(
            1,
            () -> {
              String a = "a";
              return a + "b";
            });
    System.out.println("print str:" + str);
    Lock.enhance(new LockRedisImpl());
    System.out.println("start test 2 lock.sync");
    Lock.sync(
        1,
        () -> {
          // 会在2s后才执行
          System.out.println("run end 2");
        });
  }

  static class LockRedisImpl implements LockService {
    @Nullable
    @Override
    public <T> T lock(@Nonnull String key, @Nonnull Supplier<T> supplier) {
      // 此处可用redis实现分布式锁等，本demo仅简单展示用法
      synchronized (key.intern()) {
        try {
          // 增加延时，判断后面的Lock.sync是否被增强
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        return supplier.get();
      }
    }
  }
}

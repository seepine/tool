package com.seepine.tool.test.util;

import com.seepine.tool.util.Retry;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryTest {
  public static void main(String[] args) {
    AtomicInteger times = new AtomicInteger();
    Integer res =
        Retry.run(
            5,
            1000,
            () -> {
              int count = times.incrementAndGet();
              System.out.println("第" + count + "此执行");
              if (count < 3) {
                // 当返回null或抛出异常，视为失败
                throw new Exception("exec failure");
              }
              return times.getAndIncrement();
            });
    System.out.println("执行结果：" + res);
  }
}

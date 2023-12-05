package com.seepine.tool.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CurrentTime {

  private static long startNanoTime = 0;
  private static long startNano = 0;

  /**
   * 获取纳秒
   *
   * @return nano
   */
  public static long nano() {
    if (startNanoTime == 0) {
      startNanoTime = System.currentTimeMillis() * 1_000_000;
    }
    if (startNano == 0) {
      startNano = System.nanoTime();
    }
    return startNanoTime + System.nanoTime() - startNano;
  }

  /**
   * 获取微秒
   *
   * @return micro
   */
  public static long micro() {
    return nano() / 1_000;
  }

  /**
   * 获取毫秒
   *
   * @return milli
   */
  public static long milli() {
    return nano() / 1_000_000;
  }

  /**
   * 获取秒
   *
   * @return second
   */
  public static long second() {
    return nano() / 1_000_000_000;
  }

  public static LocalDateTime now() {
    Instant instant = Instant.ofEpochSecond(0, nano());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }

}

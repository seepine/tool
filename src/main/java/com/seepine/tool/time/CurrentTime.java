package com.seepine.tool.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CurrentTime {

  public static long startNanoTime = System.currentTimeMillis() * 1_000_000;
  public static long startNano = System.nanoTime();

  /**
   * 获取纳秒
   *
   * @return nano
   */
  public static long nano() {
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

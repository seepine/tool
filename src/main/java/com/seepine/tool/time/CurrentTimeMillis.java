package com.seepine.tool.time;

/**
 * 获取当前时间，用于高并发下替代System.currentTimeMillis()
 *
 * @author seepine
 * @since 0.0.9
 */
public class CurrentTimeMillis {

  public static long now() {
    return CurrentTime.milli();
  }
}

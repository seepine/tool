package com.seepine.tool.time;

import com.seepine.tool.util.Validate;

import java.time.Duration;

public class StopWatch {
  long startTimeMillis = -1;
  long stopTimeMillis = -1;
  Duration duration;
  private static String RUN_FIRST_ERROR = "You must start and stop first.";

  public StopWatch() {}

  public static StopWatch quickStart() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    return stopWatch;
  }

  public void start() {
    stopTimeMillis = -1;
    startTimeMillis = CurrentTimeMillis.now();
  }

  public void stop() {
    Validate.isTrue(startTimeMillis > 0, "You must start first.");
    stopTimeMillis = CurrentTimeMillis.now();
    duration = Duration.ofMillis(stopTimeMillis - startTimeMillis);
  }

  public long getMillis() {
    Validate.isTrue(stopTimeMillis > 0, RUN_FIRST_ERROR);
    return duration.toMillis();
  }

  public long getSeconds() {
    Validate.isTrue(stopTimeMillis > 0, RUN_FIRST_ERROR);
    return duration.getSeconds();
  }

  public long getMinutes() {
    Validate.isTrue(stopTimeMillis > 0, RUN_FIRST_ERROR);
    return duration.toMinutes();
  }

  public long getHours() {
    Validate.isTrue(stopTimeMillis > 0, RUN_FIRST_ERROR);
    return duration.toHours();
  }

  public long getDays() {
    Validate.isTrue(stopTimeMillis > 0, RUN_FIRST_ERROR);
    return duration.toDays();
  }
}

package com.seepine.tool.test.time;

import com.seepine.tool.time.CurrentTime;

import java.time.LocalDateTime;

public class CurrentTimeTest {
  public static void main(String[] args) throws InterruptedException {
    Thread.sleep(5000);
    System.out.println(CurrentTime.nano());
    System.out.println(CurrentTime.micro());
    System.out.println(CurrentTime.milli());
    System.out.println(System.currentTimeMillis());
    System.out.println(CurrentTime.second());
    System.out.println(CurrentTime.now());
    Thread.sleep(2000);
    System.out.println(CurrentTime.now());
    System.out.println(LocalDateTime.now());
  }
}

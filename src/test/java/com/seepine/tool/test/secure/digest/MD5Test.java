package com.seepine.tool.test.secure.digest;

import com.seepine.tool.secure.digest.MD5;
import com.seepine.tool.time.StopWatch;

public class MD5Test {

  public static void main(String[] args) {
    StopWatch watch = StopWatch.quickStart();
    for (int i = 0; i < 1000000; i++) {
      MD5.digestHex("123456");
    }
    watch.stop();
    System.out.println(watch.getMillis());
    System.out.println(MD5.digestBase64("123456"));
  }
}

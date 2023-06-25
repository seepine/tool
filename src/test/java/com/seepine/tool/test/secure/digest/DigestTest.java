package com.seepine.tool.test.secure.digest;

import com.seepine.tool.secure.digest.DigestAlgorithm;
import com.seepine.tool.secure.digest.Digests;
import com.seepine.tool.secure.digest.MessageDigest;
import com.seepine.tool.time.StopWatch;

public class DigestTest {

  public static void main(String[] args) {
    MessageDigest digest = Digests.digest(DigestAlgorithm.MD5);
    StopWatch watch = StopWatch.quickStart();
    for (int i = 0; i < 1000000; i++) {
      digest.digestHex("123456");
    }
    watch.stop();
    System.out.println(watch.getMillis());
    System.out.println(digest.digestBase64("123456"));
  }
}

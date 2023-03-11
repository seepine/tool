package com.seepine.tool.test.secure;

import com.seepine.tool.secure.digest.MD5;

public class MD5Test {
  public static void main(String[] args) {
    String md5 = MD5.digest("123456");
    System.out.println(md5);
    // E10ADC3949BA59ABBE56E057F20F883E
  }
}

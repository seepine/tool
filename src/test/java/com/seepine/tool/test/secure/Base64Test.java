package com.seepine.tool.test.secure;

import com.seepine.tool.secure.symmetric.Base64;

public class Base64Test {
  public static void main(String[] args) {
    String str = Base64.encode("this is password");
    System.out.println("encode:");
    System.out.println(str);
    System.out.println("decode:");
    System.out.println(Base64.decode(str));
  }
}

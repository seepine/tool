package com.seepine.tool.test.util;

import com.seepine.tool.util.Strings;

public class StringsTest {
  public static void main(String[] args) {
    System.out.println(Strings.startWith("abc", "a"));
    System.out.println(Strings.filter("abc", val -> !val.equals("b")));
  }
}

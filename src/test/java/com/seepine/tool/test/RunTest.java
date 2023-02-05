package com.seepine.tool.test;

import com.seepine.tool.Run;

public class RunTest {
  public static void main(String[] args) {
    String str = " hello world ";
    Run.isBlank(str, () -> System.out.println("为空则进入方法体"));
    Run.nonBlank(
        str,
        (val -> {
          System.out.println("不为空则进入方法体");
          System.out.println(val);
        }));
    Run.nonBlankAndTrim(
        str,
        (val -> {
          System.out.println("不为空则进入方法体，并执行trim");
          System.out.println(val);
        }));
  }
}

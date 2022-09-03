package com.seepine.tool.test;

import com.seepine.tool.Run;

public class RunTest {
  public static void main(String[] args) {

    String str = " hello world ";
    Run.notBlank(
        str,
        (val -> {
          System.out.println("不为空则进入方法体");
          System.out.println(val);
        }));
    // 不为blank，则返回trim之后的值
    String strTrim = Run.notBlankAndTrim(str, "参数不能为空");
    System.out.println("这是trim之后的值：\n" + strTrim);

    Integer age = null;
    // 符合条件，不会抛出异常
    Run.isEmpty(age, "年龄必须为null");
    // 不符合条件，将会抛出异常
    Run.notEmpty(age, "年龄不能为null");
  }
}

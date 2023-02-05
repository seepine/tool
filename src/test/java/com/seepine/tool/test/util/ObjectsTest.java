package com.seepine.tool.test.util;

import com.seepine.tool.util.Objects;

public class ObjectsTest {
  public static void main(String[] args) {
    String name = "";
    String nameTemp1 = Objects.require(name, "zhang");
    System.out.println(nameTemp1); // zhang

    String name2 = " ";
    String nameTemp2 = Objects.require(name2, "li");
    System.out.println(nameTemp2); // " "
    String nameTemp3 = Objects.requireNonBlank(name2, "wang");
    System.out.println(nameTemp3); // wang
  }
}

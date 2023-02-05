package com.seepine.tool.test.util;

import com.seepine.tool.exception.ValidateRunException;
import com.seepine.tool.util.Validate;

import java.util.Collections;
import java.util.List;

public class ValidateTest {
  public static void main(String[] args) {
    try {
      testNull();
    } catch (ValidateRunException e) {
      System.out.println(e.getMessage());
    }
    try {
      testEmpty();
    } catch (ValidateRunException e) {
      System.out.println(e.getMessage());
    }
    try {
      testBlank();
    } catch (ValidateRunException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void testNull() {
    Validate.isNull(null, "对象必须为null");
    Validate.nonNull(null, "对象必须不为null");
  }

  public static void testEmpty() {
    List<String> list = Collections.singletonList("");
    Validate.isEmpty(list, "对象必须为empty");
    List<String> newList = Validate.nonEmpty(list, "对象必须不为empty");
    System.out.println("new list:" + newList.size());
  }

  public static void testBlank() {
    Validate.isBlank(" ", "对象必须为blank");
    Validate.nonBlank(" ", "对象必须不为blank");
  }
}

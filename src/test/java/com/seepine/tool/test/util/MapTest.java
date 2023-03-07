package com.seepine.tool.test.util;

import com.seepine.tool.util.Maps;

public class MapTest {
  public static void main(String[] args) {
    System.out.println(Maps.put("b", 6).put("a", 5).put("c", 66).build());
  }
}

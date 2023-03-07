package com.seepine.tool.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {

  public static <K, V> BuilderMap<K, V> put(K key, V value) {
    return Maps.<K, V>builder().put(key, value);
  }

  private static <K, V> BuilderMap<K, V> builder() {
    return new BuilderMap<>(new HashMap<>());
  }

  public static <K, V> BuilderMap<K, V> linkedHashMap() {
    return new BuilderMap<>(new LinkedHashMap<>());
  }

  public static class BuilderMap<K, V> {
    private final Map<K, V> map;

    private BuilderMap(Map<K, V> map) {
      this.map = map;
    }

    public BuilderMap<K, V> put(K key, V value) {
      map.put(key, value);
      return this;
    }

    public BuilderMap<K, V> putAll(Map<? extends K, ? extends V> m) {
      map.putAll(m);
      return this;
    }

    public Map<K, V> build() {
      return map;
    }
  }
}

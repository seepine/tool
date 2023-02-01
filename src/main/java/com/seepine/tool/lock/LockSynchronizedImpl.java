package com.seepine.tool.lock;

import java.util.function.Supplier;

/**
 * @author seepine
 * @since 0.1.1
 */
public class LockSynchronizedImpl implements LockService {
  @Override
  public <T> T lock(String key, Supplier<T> supplier) {
    synchronized (key.intern()) {
      return supplier.get();
    }
  }
}

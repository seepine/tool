package com.seepine.tool.lock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author seepine
 * @since 0.1.1
 */
public class LockSynchronizedImpl implements LockService {
  @Nullable
  @Override
  public <T> T lock(@Nonnull String key, @Nonnull Supplier<T> supplier) {
    synchronized (key.intern()) {
      return supplier.get();
    }
  }
}

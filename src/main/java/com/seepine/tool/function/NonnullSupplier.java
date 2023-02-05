package com.seepine.tool.function;

import javax.annotation.Nonnull;

/**
 * 非null供应者
 *
 * @author seepine
 * @since 0.2.0
 * @param <T>
 */
public interface NonnullSupplier<T> {
  /** Gets nonnull. */
  @Nonnull
  T get();
}

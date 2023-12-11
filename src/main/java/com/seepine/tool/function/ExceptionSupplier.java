package com.seepine.tool.function;

import javax.annotation.Nullable;

/**
 * 支持异常供应者
 *
 * @author seepine
 * @since 0.3.9
 * @param <T>
 */
public interface ExceptionSupplier<T> {
  @Nullable
  T get() throws Exception;
}

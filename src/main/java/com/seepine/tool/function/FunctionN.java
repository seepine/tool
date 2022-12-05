package com.seepine.tool.function;

/**
 * 匿名函数接口
 *
 * @author seepine
 * @since 0.0.5
 */
public interface FunctionN<R> {
  /**
   * Applies this function to the given argument.
   *
   * @return the function result
   */
  R apply();
}

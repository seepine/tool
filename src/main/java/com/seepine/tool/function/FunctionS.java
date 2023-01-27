package com.seepine.tool.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 使Function获得序列化能力
 *
 * @param <T>
 * @param <R>
 * @since 0.1.0
 */
@FunctionalInterface
public interface FunctionS<T, R> extends Function<T, R>, Serializable {}

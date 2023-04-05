package com.seepine.tool.function;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;
/**
 * 非null供应者
 *
 * @author seepine
 * @since 0.2.4
 * @param <T>
 */
@FunctionalInterface
public interface NonnullConsumer<T> {

  /**
   * Performs this operation on the given argument.
   *
   * @param t the input argument
   */
  void accept(@Nonnull T t);

  /**
   * Returns a composed {@code Consumer} that performs, in sequence, this operation followed by the
   * {@code after} operation. If performing either operation throws an exception, it is relayed to
   * the caller of the composed operation. If performing this operation throws an exception, the
   * {@code after} operation will not be performed.
   *
   * @param after the operation to perform after this operation
   * @return a composed {@code Consumer} that performs in sequence this operation followed by the
   *     {@code after} operation
   * @throws NullPointerException if {@code after} is null
   */
  default NonnullConsumer<T> andThen(Consumer<? super T> after) {
    Objects.requireNonNull(after);
    return (T t) -> {
      accept(t);
      after.accept(t);
    };
  }
}

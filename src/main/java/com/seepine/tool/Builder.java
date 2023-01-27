package com.seepine.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 通用增强builder
 *
 * @param <T>
 * @since 0.1.0
 */
public class Builder<T> {
  private final Supplier<T> supplier;

  private final List<Consumer<T>> modifiers = new ArrayList<>();

  private Builder(Supplier<T> instant) {
    this.supplier = instant;
  }

  public static <T> Builder<T> of(Supplier<T> instant) {
    return new Builder<>(instant);
  }

  public <P1> Builder<T> with(Consumer1<T, P1> consumer, P1 p1) {
    Consumer<T> c = instance -> consumer.accept(instance, p1);
    modifiers.add(c);
    return this;
  }

  public <P1, P2> Builder<T> with(Consumer2<T, P1, P2> consumer, P1 p1, P2 p2) {
    Consumer<T> c = instance -> consumer.accept(instance, p1, p2);
    modifiers.add(c);
    return this;
  }

  public <P1, P2, P3> Builder<T> with(Consumer3<T, P1, P2, P3> consumer, P1 p1, P2 p2, P3 p3) {
    Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3);
    modifiers.add(c);
    return this;
  }

  public <P1, P2, P3, P4> Builder<T> with(
      Consumer4<T, P1, P2, P3, P4> consumer, P1 p1, P2 p2, P3 p3, P4 p4) {
    Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4);
    modifiers.add(c);
    return this;
  }

  public <P1, P2, P3, P4, P5> Builder<T> with(
      Consumer5<T, P1, P2, P3, P4, P5> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
    Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5);
    modifiers.add(c);
    return this;
  }

  public <P1, P2, P3, P4, P5, P6> Builder<T> with(
      Consumer6<T, P1, P2, P3, P4, P5, P6> consumer, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
    Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6);
    modifiers.add(c);
    return this;
  }

  public <P1, P2, P3, P4, P5, P6, P7> Builder<T> with(
      Consumer7<T, P1, P2, P3, P4, P5, P6, P7> consumer,
      P1 p1,
      P2 p2,
      P3 p3,
      P4 p4,
      P5 p5,
      P6 p6,
      P7 p7) {
    Consumer<T> c = instance -> consumer.accept(instance, p1, p2, p3, p4, p5, p6, p7);
    modifiers.add(c);
    return this;
  }

  public T build() {
    T value = supplier.get();
    modifiers.forEach(modifier -> modifier.accept(value));
    modifiers.clear();
    return value;
  }
  /** 1 参数 Consumer */
  @FunctionalInterface
  public interface Consumer1<T, P1> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数二
     */
    void accept(T t, P1 p1);
  }

  /** 2 参数 Consumer */
  @FunctionalInterface
  public interface Consumer2<T, P1, P2> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数一
     * @param p2 参数二
     */
    void accept(T t, P1 p1, P2 p2);
  }

  /** 3 参数 Consumer */
  @FunctionalInterface
  public interface Consumer3<T, P1, P2, P3> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数一
     * @param p2 参数二
     * @param p3 参数三
     */
    void accept(T t, P1 p1, P2 p2, P3 p3);
  }

  /** 4 参数 Consumer */
  @FunctionalInterface
  public interface Consumer4<T, P1, P2, P3, P4> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数一
     * @param p2 参数二
     * @param p3 参数三
     * @param p4 参数四
     */
    void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4);
  }

  /** 5 参数 Consumer */
  @FunctionalInterface
  public interface Consumer5<T, P1, P2, P3, P4, P5> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数一
     * @param p2 参数二
     * @param p3 参数三
     * @param p4 参数四
     * @param p5 参数五
     */
    void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
  }

  /** 6 参数 Consumer */
  @FunctionalInterface
  public interface Consumer6<T, P1, P2, P3, P4, P5, P6> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数一
     * @param p2 参数二
     * @param p3 参数三
     * @param p4 参数四
     * @param p5 参数五
     * @param p6 参数六
     */
    void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
  }
  /** 7 参数 Consumer */
  @FunctionalInterface
  public interface Consumer7<T, P1, P2, P3, P4, P5, P6, P7> {
    /**
     * 接收参数方法
     *
     * @param t 对象
     * @param p1 参数一
     * @param p2 参数二
     * @param p3 参数三
     * @param p4 参数四
     * @param p5 参数五
     * @param p6 参数六
     * @param p7 参数七
     */
    void accept(T t, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
  }
}

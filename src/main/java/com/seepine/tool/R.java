package com.seepine.tool;

import com.seepine.tool.exception.RunException;
import com.seepine.tool.util.Validate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * @author seepine
 * @since 0.0.1
 * @param <T> t
 */
public class R<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final int SUCCESS = 0;
  private static final int FAIL = 1;
  /** 错误码 */
  private int code;
  /** 错误信息 */
  private String msg;
  /** 数据 */
  private T data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public static R<?> ok() {
    return ok(null, null);
  }

  public static <T> R<T> ok(@Nonnull T data) {
    return ok(data, null);
  }

  public static R<?> ok(@Nonnull String msg) {
    return ok(null, msg);
  }

  public static <T> R<T> ok(T data, String msg) {
    return build(SUCCESS, data, msg);
  }

  @Nonnull
  public static R<?> fail() {
    return fail(null, null);
  }

  @Nonnull
  public static R<?> fail(@Nonnull String msg) {
    return fail(null, msg);
  }

  @Nonnull
  public static <T> R<T> fail(@Nonnull T data) {
    return fail(data, null);
  }

  @Nonnull
  public static R<?> fail(@Nonnegative int code, @Nullable String msg) {
    if (code == SUCCESS) {
      throw new RunException("fail code cannot be the same as success");
    }
    return build(code, null, msg);
  }

  @Nonnull
  public static <T> R<T> fail(@Nullable T data, @Nullable String msg) {
    return build(FAIL, data, msg);
  }

  @Nonnull
  public static <T> R<T> build(@Nonnegative int code, @Nullable T data, @Nullable String msg) {
    Validate.isTrue(code >= 0, "code must be a positive number");
    R<T> res = new R<>();
    res.setCode(code);
    res.setData(data);
    res.setMsg(msg);
    return res;
  }

  @Override
  public String toString() {
    return "R{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
  }
}

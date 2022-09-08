package com.seepine.tool;

import com.seepine.tool.exception.RunException;

import java.io.Serializable;

/**
 * @author seepine
 * @since 0.0.1
 * @param <T> t
 */
public class R<T> implements Serializable {

  public static final int SUCCESS = 0;
  public static final int FAIL = 1;
  private static final long serialVersionUID = 1L;
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

  public static R<Object> ok() {
    return build(SUCCESS, null, null);
  }

  public static <T> R<T> ok(T data) {
    return build(SUCCESS, data, null);
  }

  public static <T> R<T> ok(T data, String msg) {
    return build(SUCCESS, data, msg);
  }

  public static R<Object> fail() {
    return build(FAIL, null, null);
  }

  public static R<Object> fail(String msg) {
    return build(FAIL, null, msg);
  }

  public static R<Object> fail(int code, String msg) {
    if (code == SUCCESS) {
      throw new RunException("fail code cannot be the same as success");
    }
    return build(code, null, msg);
  }

  public static <T> R<T> fail(T data) {
    return build(FAIL, data, null);
  }

  public static <T> R<T> fail(T data, String msg) {
    return build(FAIL, data, msg);
  }

  public static <T> R<T> build(int code, T data, String msg) {
    Run.isTrue(code >= 0, "code must be a positive number");
    R<T> res = new R<>();
    res.setCode(code);
    res.setData(data);
    res.setMsg(msg);
    return res;
  }
}

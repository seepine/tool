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
  /** http状态码 */
  private int status;
  /** 错误类型 */
  private String title;
  /** 错误码 */
  private int code;
  /** 可直接toast的错误提示 */
  private String msg;
  /** 数据 */
  private T data;

  public static <T> R<T> ok() {
    return build(null, SUCCESS, null);
  }

  public static <T> R<T> ok(T data) {
    return build(data, SUCCESS, null);
  }

  public static <T> R<T> ok(T data, String msg) {
    return build(data, SUCCESS, msg);
  }

  public static <T> R<T> fail() {
    return build(null, FAIL, null);
  }

  public static <T> R<T> fail(String msg) {
    return build(null, FAIL, msg);
  }

  public static <T> R<T> fail(int code, String msg) {
    if (code == SUCCESS) {
      throw new RunException("fail code cannot be the same as success");
    }
    return build(null, code, msg);
  }

  public static <T> R<T> fail(T data) {
    return build(data, FAIL, null);
  }

  public static <T> R<T> fail(T data, String msg) {
    return build(data, FAIL, msg);
  }

  public static <T> R<T> build(T data, int code, String msg) {
    R<T> res = new R<>();
    res.setCode(code);
    res.setData(data);
    res.setMsg(msg);
    return res;
  }

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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}

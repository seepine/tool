package com.seepine.tool.exception;

/**
 * 自定义Runtime异常类
 *
 * @author seepine
 * @since 0.0.1
 */
public class RunException extends RuntimeException {

  public RunException(String msg) {
    super(msg);
  }

  /**
   * 使用指定的详细信息消息和原因构造新的运行时异常。
   *
   * @param e 异常
   * @since 0.2.5
   */
  public RunException(Throwable e) {
    super(e.getMessage(), e);
  }

  /**
   * 使用指定的详细信息消息和原因构造新的运行时异常。
   *
   * @param e 异常
   * @since 0.2.5
   */
  public RunException(String msg, Throwable e) {
    super(msg, e);
  }
}

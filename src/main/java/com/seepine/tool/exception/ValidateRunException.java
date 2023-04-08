package com.seepine.tool.exception;
/**
 * 校验Runtime异常类
 *
 * @author seepine
 * @since 0.2.0
 */
public class ValidateRunException extends RunException {

  public ValidateRunException(String msg) {
    super(msg);
  }

  /**
   * 使用指定的详细信息消息和原因构造新的运行时异常。
   *
   * @param e 异常
   * @since 0.2.5
   */
  public ValidateRunException(Throwable e) {
    super(e);
  }

  /**
   * 使用指定的详细信息消息和原因构造新的运行时异常。
   *
   * @param e 异常
   * @since 0.2.5
   */
  public ValidateRunException(String msg, Throwable e) {
    super(msg, e);
  }
}

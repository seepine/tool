package com.seepine.tool.exception;

/**
 * 自定义加解密Runtime异常类
 *
 * @author seepine
 * @since 0.0.5
 */
public class CryptoException extends RuntimeException {
  public CryptoException(String msg) {
    super(msg);
  }

  /**
   * 使用指定的详细信息消息和原因构造新的运行时异常。
   *
   * @param e 异常
   * @since 0.2.5
   */
  public CryptoException(Throwable e) {
    super(e.getMessage(), e);
  }

  /**
   * 使用指定的详细信息消息和原因构造新的运行时异常。
   *
   * @param e 异常
   * @since 0.2.5
   */
  public CryptoException(String msg, Throwable e) {
    super(msg, e);
  }
}

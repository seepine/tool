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
}

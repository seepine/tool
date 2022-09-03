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
}

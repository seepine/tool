package com.seepine.tool.exception;

/**
 * 自定义空指针Runtime异常类，方便web框架全局拦截
 *
 * @author seepine
 * @since 0.2.0
 */
public class BlankStringRunException extends RunException {

  public BlankStringRunException() {
    super(null);
  }

  public BlankStringRunException(String msg) {
    super(msg);
  }
}

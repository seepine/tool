package com.seepine.tool.secure;
/**
 * @author seepine
 * @since 0.0.3
 */
public enum Mode {
  /** 密码分组连接模式（Cipher Block Chaining） */
  CBC,
  /** 密文反馈模式（Cipher Feedback） */
  CFB,
  /** 计数器模式（A simplification of OFB） */
  CTR,
  /** 电子密码本模式（Electronic CodeBook） */
  ECB,
  /** 输出反馈模式（Output Feedback） */
  OFB
}

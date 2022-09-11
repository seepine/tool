package com.seepine.tool.secure;

/**
 * @author seepine
 * @since 0.0.3
 */
public enum Padding {
  /** 无补码 */
  NoPadding,
  /** 0补码，即不满block长度时使用0填充 */
  ZeroPadding,
  /**
   * This padding for block ciphers is described in 5.2 Block Encryption Algorithms in the W3C's
   * "XML Encryption Syntax and Processing" document.
   */
  ISO10126Padding,
  /** Optimal Asymmetric Encryption Padding scheme defined in PKCS1 */
  OAEPPadding,
  /** The padding scheme described in PKCS #1, used with the RSA algorithm */
  PKCS1Padding,
  /**
   * The padding scheme described in RSA Laboratories, "PKCS #5: Password-Based Encryption
   * Standard," version 1.5, November 1993.
   */
  PKCS5Padding,
  /**
   * The padding scheme described in RSA Laboratories, "PKCS #5: Password-Based Encryption
   * Standard," version 1.5, November 1993.
   */
  PKCS7Padding
}

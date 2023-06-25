package com.seepine.tool.secure.digest;

import com.seepine.tool.exception.CryptoException;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要算法，线程不安全
 *
 * @author seepine
 * @since 0.3.3
 */
public class MessageDigest implements Digest {
  private final java.security.MessageDigest digest;

  public MessageDigest(DigestAlgorithm algorithm) {
    this(algorithm.getValue());
  }

  public MessageDigest(String algorithm) {
    try {
      this.digest = java.security.MessageDigest.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      throw new CryptoException(e);
    }
  }

  @Override
  public void update(byte[] in, int inOff, int len) {
    digest.update(in, inOff, len);
  }

  @Override
  public byte[] doFinal() {
    return digest.digest();
  }

  @Override
  public void reset() {
    digest.reset();
  }
}

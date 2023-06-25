package com.seepine.tool.secure.digest.mac;

import com.seepine.tool.exception.CryptoException;
import com.seepine.tool.secure.digest.Digest;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * MAC算法，线程不安全
 *
 * @author seepine
 * @since 0.3.3
 */
public class Mac implements Digest {
  private final javax.crypto.Mac mac;

  /**
   * 构造
   *
   * @param algorithm 算法
   * @param key 密钥
   * @since 4.5.13
   */
  public Mac(String algorithm, byte[] key) {
    this(algorithm, (null == key) ? null : new SecretKeySpec(key, algorithm));
  }

  /**
   * 构造
   *
   * @param algorithm 算法
   * @param key 密钥
   * @since 4.5.13
   */
  public Mac(String algorithm, Key key) {
    this(algorithm, key, null);
  }
  /**
   * 初始化
   *
   * @param algorithm 算法
   * @param key 密钥 {@link SecretKey}
   * @param spec {@link AlgorithmParameterSpec}
   * @throws CryptoException Cause by IOException
   * @since 5.7.12
   */
  public Mac(String algorithm, Key key, AlgorithmParameterSpec spec) {
    try {
      mac = javax.crypto.Mac.getInstance(algorithm);
      if (null == key) {
        throw new CryptoException(algorithm + ": key cannot be null");
      }
      if (null != spec) {
        mac.init(key, spec);
      } else {
        mac.init(key);
      }
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  /**
   * 获得 {@link javax.crypto.Mac}
   *
   * @return {@link javax.crypto.Mac}
   */
  public javax.crypto.Mac getMac() {
    return mac;
  }

  public void update(byte[] in) {
    this.mac.update(in);
  }

  @Override
  public void update(byte[] in, int inOff, int len) {
    this.mac.update(in, inOff, len);
  }

  @Override
  public byte[] doFinal() {
    return this.mac.doFinal();
  }

  @Override
  public void reset() {
    this.mac.reset();
  }

  public int getMacLength() {
    return mac.getMacLength();
  }

  public String getAlgorithm() {
    return this.mac.getAlgorithm();
  }
}

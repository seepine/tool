package com.seepine.tool.secure.symmetric;

import com.seepine.tool.Run;
import com.seepine.tool.exception.CryptoException;
import com.seepine.tool.secure.Mode;
import com.seepine.tool.secure.Padding;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
/**
 * @author seepine
 * @since 0.0.3
 */
public class AES implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String ALGORITHM = "AES";
  Cipher encryptCipher;
  Cipher decryptCipher;
  int blockSize;
  /**
   * ECB/PKCS5Padding
   *
   * @param key 密钥，支持三种密钥长度：128、192、256位
   * @since 3.3.0
   */
  public AES(String key) {
    this(Mode.ECB, Padding.PKCS5Padding, key, null);
  }
  /**
   * CBC/PKCS5Padding
   *
   * @param key 密钥，支持三种密钥长度：128、192、256位
   * @param iv 偏移向量，加盐
   * @since 3.3.0
   */
  public AES(String key, String iv) {
    this(Mode.CBC, Padding.PKCS5Padding, key, iv);
  }
  /**
   * 构造
   *
   * @param mode 模式{@link Mode}
   * @param padding {@link Padding}补码方式
   * @param key 密钥，支持三种密钥长度：128、192、256位
   * @since 3.3.0
   */
  public AES(Mode mode, Padding padding, String key) {
    this(mode, padding, key, null);
  }
  /**
   * 构造
   *
   * @param mode 模式{@link Mode}
   * @param padding {@link Padding}补码方式
   * @param key 密钥，支持三种密钥长度：128、192、256位
   * @param iv 偏移向量，加盐
   * @since 4.6.7
   */
  public AES(Mode mode, Padding padding, String key, String iv) {
    try {
      encryptCipher = Cipher.getInstance(ALGORITHM + "/" + mode.name() + "/" + padding.name());
      decryptCipher = Cipher.getInstance(ALGORITHM + "/" + mode.name() + "/" + padding.name());
      blockSize = encryptCipher.getBlockSize();
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
      if (iv == null) {
        Run.isTrue(!mode.equals(Mode.CBC), "CBC mode need iv");
        encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
        decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
      } else {
        Run.isTrue(mode.equals(Mode.CBC), mode.name() + " mode not need iv");
        // CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        decryptCipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new CryptoException(e.getMessage());
    }
  }

  /**
   * 加密
   *
   * @param src 数据
   * @return 密文
   */
  public String encrypt(String src) throws CryptoException {
    try {
      byte[] dataBytes = src.getBytes();
      int plaintextLength = dataBytes.length;
      if (plaintextLength % blockSize != 0) {
        plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
      }
      byte[] plaintext = new byte[plaintextLength];
      System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
      byte[] encrypted = encryptCipher.doFinal(plaintext);
      return Base64.encode(encrypted);
    } catch (Exception e) {
      throw new CryptoException(e.getMessage());
    }
  }

  /**
   * 解密
   *
   * @param ciphertext 密文
   * @return 数据
   */
  public String decrypt(String ciphertext) throws CryptoException {
    try {
      byte[] encrypted1 = Base64.decodeByte(ciphertext);
      byte[] original = decryptCipher.doFinal(encrypted1);
      return new String(original, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new CryptoException(e.getMessage());
    }
  }
}

package com.seepine.tool.secure.asymmetric;

import com.seepine.tool.exception.CryptoException;
import com.seepine.tool.secure.symmetric.Base64;
import com.seepine.tool.util.Strings;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密，RSA加解密封装
 *
 * @author seepine
 * @since 0.0.3
 */
public class RSA implements Serializable {
  private static final long serialVersionUID = 1L;

  String charset = "UTF-8";

  String signAlgorithm = "MD5withRSA";

  RSAPublicKey publicKey;
  String publicKeyBase64;

  RSAPrivateKey privateKey;
  String privateKeyBase64;

  int PRIVATE_DECODE_BLOCK_SIZE;
  int PRIVATE_ENCODE_BLOCK_SIZE;
  int PUBLIC_DECODE_BLOCK_SIZE;
  int PUBLIC_ENCODE_BLOCK_SIZE;

  public RSA() {
    this(1024);
  }

  public RSA(int keySize) {
    genKeyPair(keySize);
  }

  public RSA(String publicKey, String privateKey) {
    this.publicKeyBase64 = publicKey;
    this.privateKeyBase64 = privateKey;
    initKey();
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public void setSignAlgorithm(String signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }
  /**
   * 获取公钥base64
   *
   * @return 返回公钥base64
   */
  public String getPublicKey() {
    return this.publicKeyBase64;
  }

  /**
   * 获取私钥base64
   *
   * @return 返回私钥base64
   */
  public String getPrivateKey() {
    return this.privateKeyBase64;
  }

  /** 生成公私密钥 */
  private void genKeyPair(int keySize) {
    try {
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Strings.RSA);
      keyPairGen.initialize(keySize, new SecureRandom());
      KeyPair keyPair = keyPairGen.generateKeyPair();
      publicKey = (RSAPublicKey) keyPair.getPublic();
      privateKey = (RSAPrivateKey) keyPair.getPrivate();
      publicKeyBase64 = Base64.encode(publicKey.getEncoded());
      privateKeyBase64 = Base64.encode((privateKey.getEncoded()));
      initCipher();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new CryptoException(e.getMessage());
    }
  }

  private void initKey() {
    try {
      if (publicKeyBase64 != null) {
        publicKey =
            (RSAPublicKey)
                KeyFactory.getInstance(Strings.RSA)
                    .generatePublic(new X509EncodedKeySpec(Base64.decodeByte(publicKeyBase64)));
      }
      if (privateKeyBase64 != null) {
        privateKey =
            (RSAPrivateKey)
                KeyFactory.getInstance(Strings.RSA)
                    .generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeByte(privateKeyBase64)));
      }
      initCipher();
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new CryptoException(e.getMessage());
    }
  }
  /** 初始化公私钥Cipher */
  private void initCipher() {
    if (privateKey != null) {
      PRIVATE_DECODE_BLOCK_SIZE = privateKey.getModulus().bitLength() / 8;
      PRIVATE_ENCODE_BLOCK_SIZE = PRIVATE_DECODE_BLOCK_SIZE - 11;
    }
    if (publicKey != null) {
      PUBLIC_DECODE_BLOCK_SIZE = publicKey.getModulus().bitLength() / 8;
      PUBLIC_ENCODE_BLOCK_SIZE = PUBLIC_DECODE_BLOCK_SIZE - 11;
    }
  }

  public Cipher getCipher(int var1, Key key) throws CryptoException {
    try {
      Cipher cipher = Cipher.getInstance(Strings.RSA);
      cipher.init(var1, key);
      return cipher;
    } catch (Exception e) {
      throw new CryptoException(e.getMessage());
    }
  }
  /**
   * 私钥加密
   *
   * @param origin 明文
   * @return 密文
   * @throws CryptoException 异常信息
   */
  public String privateEncrypt(String origin) throws CryptoException {
    return encodeDoFinal(
        origin, getCipher(Cipher.ENCRYPT_MODE, privateKey), PRIVATE_ENCODE_BLOCK_SIZE);
  }
  /**
   * 私钥解密
   *
   * @param secret 密文
   * @return 明文
   * @throws CryptoException 异常信息
   */
  public String privateDecrypt(String secret) throws CryptoException {
    return decodeDoFinal(
        secret, getCipher(Cipher.DECRYPT_MODE, privateKey), PRIVATE_DECODE_BLOCK_SIZE);
  }

  /**
   * 公钥加密
   *
   * @param origin 明文
   * @return 密文
   * @throws CryptoException 异常信息
   */
  public String publicEncrypt(String origin) throws CryptoException {
    return encodeDoFinal(
        origin, getCipher(Cipher.ENCRYPT_MODE, publicKey), PUBLIC_ENCODE_BLOCK_SIZE);
  }
  /**
   * 公钥解密
   *
   * @param secret 密文
   * @return 明文
   * @throws CryptoException 异常信息
   */
  public String publicDecrypt(String secret) throws CryptoException {
    return decodeDoFinal(
        secret, getCipher(Cipher.DECRYPT_MODE, publicKey), PUBLIC_DECODE_BLOCK_SIZE);
  }

  private static String encodeDoFinal(String str, Cipher cipher, int maxBlock)
      throws CryptoException {
    if (str == null) {
      throw new CryptoException("加密对象不能为空");
    }
    return Base64.encode(divisionDoFinal(str.getBytes(StandardCharsets.UTF_8), cipher, maxBlock));
  }

  private static String decodeDoFinal(String str, Cipher cipher, int maxBlock)
      throws CryptoException {
    if (str == null) {
      throw new CryptoException("解密对象不能为空");
    }
    return new String(
        divisionDoFinal(Base64.decodeByte(str.getBytes(StandardCharsets.UTF_8)), cipher, maxBlock),
        StandardCharsets.UTF_8);
  }

  private static byte[] divisionDoFinal(byte[] inputArray, Cipher cipher, int maxBlock)
      throws CryptoException {
    try {
      if (cipher == null) {
        throw new CryptoException("公钥或私钥为空时，不能使用其进行加解密");
      }
      int inputLength = inputArray.length;
      int offSet = 0;
      final ByteArrayOutputStream out = new ByteArrayOutputStream();
      while (inputLength - offSet > 0) {
        if (inputLength - offSet > maxBlock) {
          out.write(cipher.doFinal(inputArray, offSet, maxBlock));
          offSet += maxBlock;
        } else {
          out.write(cipher.doFinal(inputArray, offSet, inputLength - offSet));
          offSet = inputLength;
        }
      }
      return out.toByteArray();
    } catch (IllegalBlockSizeException | BadPaddingException | IOException e) {
      throw new CryptoException(e.getMessage());
    }
  }

  /**
   * 用私钥加签
   *
   * @param content 加签明文
   * @return 加签base64值
   */
  public String sign(String content) {
    try {
      byte[] data = content.getBytes(charset);
      Signature signature = Signature.getInstance(signAlgorithm);
      signature.initSign(privateKey);
      signature.update(data);
      return Base64.encode(signature.sign());
    } catch (Exception e) {
      e.printStackTrace();
      throw new CryptoException(e.getMessage());
    }
  }

  /**
   * 用公钥验签
   *
   * @param content 验签明文
   * @param sign 已加签的密文
   * @return true/false
   */
  public boolean verify(String content, String sign) {
    try {
      byte[] data = content.getBytes(charset);
      Signature signature = Signature.getInstance(signAlgorithm);
      signature.initVerify(publicKey);
      signature.update(data);
      return signature.verify(Base64.decodeByte(sign));
    } catch (Exception e) {
      e.printStackTrace();
      throw new CryptoException(e.getMessage());
    }
  }
}

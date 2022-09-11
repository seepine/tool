package com.seepine.tool.test.secure;

import com.seepine.tool.secure.asymmetric.RSA;

public class RSATest {
  public static void main(String[] args) {
    RSA rsa = new RSA();
    System.out.println("public key:");
    System.out.println(rsa.getPublicKey());
    System.out.println("private key:");
    System.out.println(rsa.getPrivateKey());
    System.out.println();
    String str = rsa.privateEncrypt("this is password");
    System.out.println("private encrypt:");
    System.out.println(str);
    System.out.println("public decrypt:");
    System.out.println(rsa.publicDecrypt(str));
  }
}

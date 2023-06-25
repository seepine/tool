package com.seepine.tool.secure.digest;

import com.seepine.tool.secure.digest.mac.Mac;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC算法，线程不安全
 *
 * @author seepine
 * @since 0.3.3
 */
public class Hmac extends Mac {
  public Hmac(HmacAlgorithm algorithm, String key) {
    super(
        algorithm.getValue(),
        new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm.getValue()));
  }
}

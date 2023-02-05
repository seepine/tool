package com.seepine.tool.util;

import com.seepine.tool.exception.RunException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 随机工具类
 *
 * @author hutool
 * @author xiaoleilu
 * @since 0.0.4
 */
public class RandomUtil {
  /** 用于随机选的数字 */
  public static final String BASE_NUMBER = "0123456789";
  /** 用于随机选的字符 */
  public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
  /** 用于随机选的字符和数字 */
  public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

  /**
   * 获取随机数生成器对象<br>
   * ThreadLocalRandom是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
   *
   * <p>注意：此方法返回的{@link ThreadLocalRandom}不可以在多线程环境下共享对象，否则有重复随机数问题。
   * 见：https://www.jianshu.com/p/89dfe990295c
   *
   * @return {@link ThreadLocalRandom}
   * @since 3.1.2
   */
  public static ThreadLocalRandom getRandom() {
    return ThreadLocalRandom.current();
  }

  /**
   * 创建{@link SecureRandom}，类提供加密的强随机数生成器 (RNG)<br>
   *
   * @param seed 自定义随机种子
   * @return {@link SecureRandom}
   * @since 4.6.5
   */
  public static SecureRandom createSecureRandom(byte[] seed) {
    return (null == seed) ? new SecureRandom() : new SecureRandom(seed);
  }

  /**
   * 获取SHA1PRNG的{@link SecureRandom}，类提供加密的强随机数生成器 (RNG)<br>
   * 注意：此方法获取的是伪随机序列发生器PRNG（pseudo-random number generator）
   *
   * <p>相关说明见：https://stackoverflow.com/questions/137212/how-to-solve-slow-java-securerandom
   *
   * @return {@link SecureRandom}
   * @since 3.1.2
   */
  public static SecureRandom getSecureRandom() {
    return getSecureRandom(null);
  }

  /**
   * 获取SHA1PRNG的{@link SecureRandom}，类提供加密的强随机数生成器 (RNG)<br>
   * 注意：此方法获取的是伪随机序列发生器PRNG（pseudo-random number generator）
   *
   * <p>相关说明见：https://stackoverflow.com/questions/137212/how-to-solve-slow-java-securerandom
   *
   * @param seed 随机数种子
   * @return {@link SecureRandom}
   * @see #createSecureRandom(byte[])
   * @since 5.5.2
   */
  public static SecureRandom getSecureRandom(byte[] seed) {
    return createSecureRandom(seed);
  }

  /**
   * 获取SHA1PRNG的{@link SecureRandom}，类提供加密的强随机数生成器 (RNG)<br>
   * 注意：此方法获取的是伪随机序列发生器PRNG（pseudo-random number generator）,在Linux下噪声生成时可能造成较长时间停顿。<br>
   * see: http://ifeve.com/jvm-random-and-entropy-source/
   *
   * <p>相关说明见：https://stackoverflow.com/questions/137212/how-to-solve-slow-java-securerandom
   *
   * @param seed 随机数种子
   * @return {@link SecureRandom}
   * @since 5.5.8
   */
  public static SecureRandom getSHA1PRNGRandom(byte[] seed) {
    SecureRandom random;
    try {
      random = SecureRandom.getInstance("SHA1PRNG");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RunException(e.getMessage());
    }
    if (null != seed) {
      random.setSeed(seed);
    }
    return random;
  }

  /**
   * 获取algorithms/providers中提供的强安全随机生成器<br>
   * 注意：此方法可能造成阻塞或性能问题
   *
   * @return {@link SecureRandom}
   * @since 5.7.12
   */
  public static SecureRandom getSecureRandomStrong() {
    try {
      return SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RunException(e.getMessage());
    }
  }

  /**
   * 获取随机数产生器
   *
   * @param isSecure 是否为强随机数生成器 (RNG)
   * @return {@link Random}
   * @see #getSecureRandom()
   * @see #getRandom()
   * @since 4.1.15
   */
  public static Random getRandom(boolean isSecure) {
    return isSecure ? getSecureRandom() : getRandom();
  }

  /**
   * 获得随机Boolean值
   *
   * @return true or false
   * @since 4.5.9
   */
  public static boolean randomBoolean() {
    return 0 == randomInt(2);
  }

  /**
   * 随机汉字（'\u4E00'-'\u9FFF'）
   *
   * @return 随机的汉字字符
   * @since 5.7.15
   */
  public static char randomChinese() {
    return (char) randomInt('\u4E00', '\u9FFF');
  }

  /**
   * 获得指定范围内的随机数
   *
   * @param min 最小数（包含）
   * @param max 最大数（不包含）
   * @return 随机数
   */
  public static int randomInt(int min, int max) {
    return getRandom().nextInt(min, max);
  }

  /**
   * 获得随机数int值
   *
   * @return 随机数
   * @see Random#nextInt()
   */
  public static int randomInt() {
    return getRandom().nextInt();
  }

  /**
   * 获得指定范围内的随机数 [0,limit)
   *
   * @param limit 限制随机数的范围，不包括这个数
   * @return 随机数
   * @see Random#nextInt(int)
   */
  public static int randomInt(int limit) {
    return getRandom().nextInt(limit);
  }

  /**
   * 获得指定范围内的随机数[min, max)
   *
   * @param min 最小数（包含）
   * @param max 最大数（不包含）
   * @return 随机数
   * @see ThreadLocalRandom#nextLong(long, long)
   * @since 3.3.0
   */
  public static long randomLong(long min, long max) {
    return getRandom().nextLong(min, max);
  }

  /**
   * 获得随机数
   *
   * @return 随机数
   * @see ThreadLocalRandom#nextLong()
   * @since 3.3.0
   */
  public static long randomLong() {
    return getRandom().nextLong();
  }

  /**
   * 获得指定范围内的随机数 [0,limit)
   *
   * @param limit 限制随机数的范围，不包括这个数
   * @return 随机数
   * @see ThreadLocalRandom#nextLong(long)
   */
  public static long randomLong(long limit) {
    return getRandom().nextLong(limit);
  }

  /**
   * 获得指定范围内的随机数
   *
   * @param min 最小数（包含）
   * @param max 最大数（不包含）
   * @return 随机数
   * @see ThreadLocalRandom#nextDouble(double, double)
   * @since 3.3.0
   */
  public static double randomDouble(double min, double max) {
    return getRandom().nextDouble(min, max);
  }

  /**
   * 获得随机数[0, 1)
   *
   * @return 随机数
   * @see ThreadLocalRandom#nextDouble()
   * @since 3.3.0
   */
  public static double randomDouble() {
    return getRandom().nextDouble();
  }

  /**
   * 获得指定范围内的随机数 [0,limit)
   *
   * @param limit 限制随机数的范围，不包括这个数
   * @return 随机数
   * @see ThreadLocalRandom#nextDouble(double)
   * @since 3.3.0
   */
  public static double randomDouble(double limit) {
    return getRandom().nextDouble(limit);
  }

  /**
   * 随机bytes
   *
   * @param length 长度
   * @return bytes
   */
  public static byte[] randomBytes(int length) {
    byte[] bytes = new byte[length];
    getRandom().nextBytes(bytes);
    return bytes;
  }

  /**
   * 随机获得列表中的元素
   *
   * @param <T> 元素类型
   * @param list 列表
   * @return 随机元素
   */
  public static <T> T randomEle(List<T> list) {
    return randomEle(list, list.size());
  }

  /**
   * 随机获得列表中的元素
   *
   * @param <T> 元素类型
   * @param list 列表
   * @param limit 限制列表的前N项
   * @return 随机元素
   */
  public static <T> T randomEle(List<T> list, int limit) {
    if (list.size() < limit) {
      limit = list.size();
    }
    return list.get(randomInt(limit));
  }

  /**
   * 随机获得数组中的元素
   *
   * @param <T> 元素类型
   * @param array 列表
   * @return 随机元素
   * @since 3.3.0
   */
  public static <T> T randomEle(T[] array) {
    return randomEle(array, array.length);
  }

  /**
   * 随机获得数组中的元素
   *
   * @param <T> 元素类型
   * @param array 列表
   * @param limit 限制列表的前N项
   * @return 随机元素
   * @since 3.3.0
   */
  public static <T> T randomEle(T[] array, int limit) {
    if (array.length < limit) {
      limit = array.length;
    }
    return array[randomInt(limit)];
  }

  /**
   * 随机获得列表中的一定量元素
   *
   * @param <T> 元素类型
   * @param list 列表
   * @param count 随机取出的个数
   * @return 随机元素
   */
  public static <T> List<T> randomEles(List<T> list, int count) {
    final List<T> result = new ArrayList<>(count);
    int limit = list.size();
    while (result.size() < count) {
      result.add(randomEle(list, limit));
    }

    return result;
  }

  /**
   * 获得一个随机的字符串（只包含数字和字符）
   *
   * @param length 字符串的长度
   * @return 随机字符串
   */
  public static String randomString(int length) {
    return randomString(BASE_CHAR_NUMBER, length);
  }

  /**
   * 获得一个随机的字符串（只包含数字和大写字符）
   *
   * @param length 字符串的长度
   * @return 随机字符串
   * @since 4.0.13
   */
  public static String randomStringUpper(int length) {
    return randomString(BASE_CHAR_NUMBER, length).toUpperCase();
  }

  /**
   * 获得一个只包含数字的字符串
   *
   * @param length 字符串的长度
   * @return 随机字符串
   */
  public static String randomNumbers(int length) {
    return randomString(BASE_NUMBER, length);
  }

  /**
   * 获得一个随机的字符串
   *
   * @param baseString 随机字符选取的样本
   * @param length 字符串的长度
   * @return 随机字符串
   */
  public static String randomString(String baseString, int length) {
    if (Objects.isEmpty(baseString)) {
      return Strings.EMPTY;
    }
    if (length < 1) {
      length = 1;
    }

    final StringBuilder sb = new StringBuilder(length);
    int baseLength = baseString.length();
    for (int i = 0; i < length; i++) {
      int number = randomInt(baseLength);
      sb.append(baseString.charAt(number));
    }
    return sb.toString();
  }

  /**
   * 随机数字，数字为0~9单个数字
   *
   * @return 随机数字字符
   * @since 3.1.2
   */
  public static char randomNumber() {
    return randomChar(BASE_NUMBER);
  }

  /**
   * 随机字母或数字，小写
   *
   * @return 随机字符
   * @since 3.1.2
   */
  public static char randomChar() {
    return randomChar(BASE_CHAR_NUMBER);
  }

  /**
   * 随机字符
   *
   * @param baseString 随机字符选取的样本
   * @return 随机字符
   * @since 3.1.2
   */
  public static char randomChar(String baseString) {
    return baseString.charAt(randomInt(baseString.length()));
  }
}

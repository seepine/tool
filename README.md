## tool

## 一、安装

#### LatestVersion

[![Maven Central](https://img.shields.io/maven-central/v/com.seepine/tool.svg)](https://search.maven.org/search?q=g:com.seepine%20a:tool)

#### Maven

```xml

<dependency>
  <groupId>com.seepine</groupId>
  <artifactId>tool</artifactId>
  <version>${latestVersion}</version>
</dependency>
```

#### Gradle

```gradle
implementation("com.seepine:tool:${lastVersion}")
```

## 二、用法

### run

```java
class RunTest {
  public static void main(String[] args) {
    String str = " hello world ";
    Run.isBlank(str, () -> System.out.println("为空则进入方法体"));
    Run.nonBlank(
      str,
      (val -> {
        System.out.println("不为空则进入方法体");
        System.out.println(val);
      }));
    Run.nonBlankAndTrim(
      str,
      (val -> {
        System.out.println("不为空则进入方法体，并执行trim");
        System.out.println(val);
      }));
  }
}
```

### validate

```java
class Test {
  public static void main(String[] args) {
    Validate.isNull(null, "对象必须为null");
    Validate.nonBlank(" ", "对象必须不为blank");
  }
}
```

### secure

```java
class Test {
  public static void main(String[] args) {
    RSA rsa = new RSA();
    // rsa.privateEncrypt(src)

    AES aes = new AES("key");
    // aes.encrypt(src);

    Base64.encode("data");
    Base64.decode("data");
  }
}
```

### builder

```java
class BuilderTest {
  public static void main(String[] args) {
    User user =
      Builder.of(User::new).with(User::setName, "zhangSan").with(User::setAge, 27).build();
    System.out.println(user.toString());
    // User{name='zhangSan', age=27}

    User user2 = Builder.of(User::new).with(User::setAll, "liSi", 26).build();
    System.out.println(user2.toString());
    // User{name='liSi', age=26}
  }
}
```

### Lock

```java
class LockTest {
  public static void main(String[] args) {
    Lock.sync(1, () -> System.out.println("run end 1"));
    String str =
      Lock.sync(
        1,
        () -> {
          String a = "a";
          return a + "b";
        });

    // 还可自定义锁的实现，例如增强为分布式锁等
    Lock.enhance(new LockService() {
      // ...
    });
  }
}
```

### Cache

```java
public class CacheTest {
  public static void main(String[] args) {
    Cache.set("test", "test value", 1500);
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException ignore) {
    }
    System.out.println(Cache.getStr("test"));
    // test value,因为还没过期

    try {
      TimeUnit.MILLISECONDS.sleep(600);
    } catch (InterruptedException ignore) {
    }
    System.out.println(Cache.getStr("test"));
    // null,因为过期了

    System.out.println(Cache.get("test", () -> "new test value", 0));
    // new test value,因为过期了，所以取默认填充的值

    System.out.println(Cache.get("test", () -> "more new value", 0));
    // new test value,因为上次默认值保存到缓存了没过期

    // 可实现CacheService重写缓存逻辑例如缓存到redis等，并在程序初始化时调用Cache.enhance进行增强
    // 而后的Cache.get/Cache.set都会走自定义的CacheService
    // Cache.enhance(new CacheService() {});
  }
}
```

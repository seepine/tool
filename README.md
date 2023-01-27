## tool

## 依赖

- Latest
  Version: [![Maven Central](https://img.shields.io/maven-central/v/com.seepine/tool.svg)](https://search.maven.org/search?q=g:com.seepine%20a:tool)
- Maven:

```xml

<dependency>
  <groupId>com.seepine</groupId>
  <artifactId>tool</artifactId>
  <version>${latest.version}</version>
</dependency>
```

## 使用

### run assert

```java
class RunTest {
  public static void main(String[] args) {
    String str = " hello world ";
    Run.notBlank(
      str,
      (val -> {
        System.out.println("不为空则进入方法体");
        System.out.println(val);
      }));
    // 不为blank，则返回trim之后的值
    String strTrim = Run.notBlankAndTrim(str, "参数不能为空");
    System.out.println("这是trim之后的值：\n" + strTrim);

    Integer age = null;
    // 符合条件，不会抛出异常
    Run.isEmpty(age, "年龄必须为null");
    // 不符合条件，将会抛出异常
    Run.notEmpty(age, "年龄不能为null");
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

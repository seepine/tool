package com.seepine.tool.test;

import com.seepine.tool.Builder;

public class BuilderTest {
  public static void main(String[] args) {
    User user =
        Builder.of(User::new).with(User::setName, "zhangSan").with(User::setAge, 27).build();
    System.out.println(user.toString());
    // User{name='zhangSan', age=27}

    User user2 = Builder.of(User::new).with(User::setAll, "liSi", 26).build();
    System.out.println(user2.toString());
    // User{name='liSi', age=26}
  }

  static class User {
    private String name;
    private Integer age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setAll(String name, Integer age) {
      this.name = name;
      this.age = age;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
  }
}

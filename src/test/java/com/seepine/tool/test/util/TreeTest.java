package com.seepine.tool.test.util;

import com.seepine.tool.util.Trees;
import java.util.Arrays;
import java.util.List;

public class TreeTest {
  public static void main(String[] args) {
    /*
    - 2
      - 23
        - 231
        - 232
      - 21
      - 22
    - 1
      - 10
      - 12
      - 11
    */
    List<Trees.Node> list =
        Arrays.asList(
            Trees.Node.build().setId("1").setSort(1).setParentId("-1"),
            Trees.Node.build().setId("10").setSort(0).setParentId("1"),
            Trees.Node.build().setId("11").setSort(2).setParentId("1"),
            Trees.Node.build().setId("12").setSort(1).setParentId("1"),
            Trees.Node.build().setId("2").setSort(0).setParentId("-1"),
            Trees.Node.build().setId("21").setSort(1).setParentId("2"),
            Trees.Node.build().setId("22").setSort(2).setParentId("2"),
            Trees.Node.build().setId("23").setSort(0).setParentId("2"),
            Trees.Node.build().setId("231").setSort(1).setParentId("23"),
            Trees.Node.build().setId("232").setSort(2).setParentId("23"));

    // 自定义节点类，例如传入自己的部门类List<DeptNode>等等，手动指定主键、父节点、排序、设置子节点等方法
    List<Trees.Node> tree =
        Trees.build(
            list,
            Trees.Node::getId,
            Trees.Node::getParentId,
            "-1",
            Trees.Node::getSort,
            Trees.Node::setChildren);
    // 通过
    List<Trees.Node> tree2 = Trees.build(list, "-1");

    System.out.println("id   parentId   sort");
    print(tree);
    System.out.println();
    System.out.println("id   parentId   sort");
    print(tree2);
  }

  public static void print(List<Trees.Node> list) {
    if (list == null) {
      return;
    }
    for (Trees.Node o : list) {
      System.out.printf("%-4s %-10s %-4s \n", o.getId(), o.getParentId(), o.getSort());
      print(o.getChildren());
    }
  }
}

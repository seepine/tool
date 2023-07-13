package com.seepine.tool.util;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Trees {

  public static class Node implements Serializable {
    /** 主键 */
    private Serializable id;
    /** 名称 */
    private String name;
    /** 父节点 */
    private Serializable parentId;
    /** 是否可拖拽 */
    private Boolean draggable;
    /** 是否显示复选框 */
    private Boolean checkable;
    /** 是否禁用 */
    private Boolean disabled;
    /** 排序值 */
    private Integer sort;
    /** 子节点列表 */
    private List<Node> children = new ArrayList<>();

    public static Node build() {
      return new Node();
    }

    public Serializable getId() {
      return id;
    }

    public Node setId(Serializable id) {
      this.id = id;
      return this;
    }

    public String getName() {
      return name;
    }

    public Node setName(String name) {
      this.name = name;
      return this;
    }

    public Serializable getParentId() {
      return parentId;
    }

    public Node setParentId(Serializable parentId) {
      this.parentId = parentId;
      return this;
    }

    public Boolean getDraggable() {
      return draggable;
    }

    public Node setDraggable(Boolean draggable) {
      this.draggable = draggable;
      return this;
    }

    public Boolean getCheckable() {
      return checkable;
    }

    public Node setCheckable(Boolean checkable) {
      this.checkable = checkable;
      return this;
    }

    public Integer getSort() {
      return sort;
    }

    public Node setSort(Integer sort) {
      this.sort = sort;
      return this;
    }

    public Boolean getDisabled() {
      return disabled;
    }

    public Node setDisabled(Boolean disabled) {
      this.disabled = disabled;
      return this;
    }

    public List<Node> getChildren() {
      return children;
    }

    public Node setChildren(List<Node> children) {
      this.children = children;
      return this;
    }

    @Override
    public String toString() {
      return "Node{"
          + "id='"
          + id
          + '\''
          + ", name='"
          + name
          + '\''
          + ", parentId='"
          + parentId
          + '\''
          + ", draggable="
          + draggable
          + ", checkable="
          + checkable
          + ", sort="
          + sort
          + ", children="
          + children
          + '}';
    }
  }

  /**
   * 构造数据树 O(N)
   *
   * @param list 原集合
   * @param mKey 父级被子集关联的字段,比如Node::getId
   * @param treeConnectKey 子集关联父级的字段，比如Node::getParentId
   * @param treeSortKey 同级菜单的排序字段，比如Node::getSort
   * @param consumer 添加子集的函数，如Node::setChildren
   * @param <M> 泛型
   * @return 树list
   */
  @Nonnull
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <M> List<M> build(
      @Nullable List<M> list,
      @Nonnull Function<M, ?> mKey,
      @Nonnull Function<M, ?> treeConnectKey,
      @Nonnull Object rootId,
      @Nullable Function<M, ? extends Comparable> treeSortKey,
      @Nonnull Consumers<M, M> consumer) {
    // 线程安全类集合
    List<M> tree = Collections.synchronizedList(new ArrayList<>());
    if (Objects.isEmpty(list)) {
      return tree;
    }
    // 按上级id分组
    final Map<?, List<M>> collect =
        list.parallelStream().collect(Collectors.groupingBy(treeConnectKey));
    list.parallelStream()
        .filter(
            m -> {
              // 判断是否叶节点
              final boolean isToplevel = Objects.equals(treeConnectKey.apply(m), rootId);
              if (isToplevel) {
                tree.add(m);
              }
              return !isToplevel;
            })
        .forEach(
            // 通过对象地址引用实现父子级关联，即使父级先添加了子级，子级在添加孙子级，父子孙三级也全部都会关联
            m -> {
              if (treeSortKey != null) {
                consumer.accept(
                    m,
                    ListUtil.sort(collect.get(mKey.apply(m)), Comparator.comparing(treeSortKey)));
              } else {
                consumer.accept(m, collect.get(mKey.apply(m)));
              }
            });
    if (treeSortKey != null) {
      // 排序
      tree.sort(Comparator.comparing(treeSortKey));
      tree.parallelStream()
          .forEach(
              m ->
                  consumer.accept(
                      m,
                      ListUtil.sort(
                          collect.get(mKey.apply(m)), Comparator.comparing(treeSortKey))));
      return tree;
    } else {
      return tree.parallelStream()
          .peek(b -> consumer.accept(b, collect.get(mKey.apply(b))))
          .collect(Collectors.toList());
    }
  }

  public static List<Node> build(@Nonnull List<Node> list, Serializable rootId) {
    return build(list, Node::getId, Node::getParentId, rootId, Node::getSort, Node::setChildren);
  }

  @FunctionalInterface
  public interface Consumers<M, N> {

    /**
     * 消费函数,用于设置子节点
     *
     * @param m node
     * @param n children
     */
    void accept(M m, List<N> n);
  }
}

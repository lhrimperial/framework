package com.github.framework.util.stream;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 树工具类
 *
 * @author linbeihua
 * @date 2022/5/5 18:20
 **/
public class TreeUtil {

    /**
     * 递归生成树
     *
     * @param list
     * @param pid
     * @return
     */
    public static <T, R> T build(List<T> list, String pid, Function<T, R> pidFun, Function<T, R> idFun, Consumer<T> childFun) {
        List<T> deptTreeList = buildTree(list, pid, pidFun, idFun, childFun);
        return deptTreeList.get(0);
    }

    /**
     * 递归生成树
     *
     * @param list
     * @param pid
     * @return
     */
    public static <T, R> List<T> buildTreeList(List<T> list, String pid, Function<T, R> pidFun, Function<T, R> idFun, Consumer<T> childFun) {
        return buildTree(list, pid, pidFun, idFun, childFun);
    }

    private static <T, R> List<T> buildTree(List<T> list, String pid, Function<T, R> pidFun, Function<T, R> idFun, Consumer<T> childFun) {
        List<T> tree = new ArrayList<>();
        for (T node : list) {
            if (pidFun.apply(node) == null || pid.equals(pidFun.apply(node))) {
                tree.add(findChild(node, list, pidFun, idFun, childFun));
            }
        }
        return tree;
    }

    private static <T, R> T findChild(T node, List<T> list, Function<T, R> pidFun, Function<T, R> idFun, Consumer<T> childFun) {
        for (T curNode : list) {
            if (pidFun.apply(curNode) != null && pidFun.apply(curNode).equals(idFun.apply(node))) {
                childFun.accept(node, findChild(curNode, list, pidFun, idFun, childFun));
            }
        }
        return node;
    }
}

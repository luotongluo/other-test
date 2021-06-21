package com.lt.dailytest.othertest.tree;

/**
 * @author tong.luo
 * @description Node
 * @date 2021/3/22 14:35
 */
public class Node {
//    private K key;
//    private V value;
    private Object key;
    private Object value;
    private Node left;
    private Node right;
    //size的计算方式，左子树的个数 + 1 + 右子树的个数
    private int size = 1;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

}

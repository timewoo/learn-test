package com.learntest.datastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yanglin
 * @date 2022/10/31 11:14
 */
public class Tree {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Node{
        private Node left;
        private int data;
        private Node right;
    }

    public static void main(String[] args) {

    }

    public static Node getTree(int[] datas){
        if (datas.length==0){
            return null;
        }
        Node node = new Node();
        node.data = datas[0];
        return node;
    }
}

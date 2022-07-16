package com.learntest.collectionstest;

/**
 * @author yanglin
 * @date 2022/7/15 17:33
 */
public class NodeTest {

    public static class Node {
        public Node() {

        }

        public int value;
        public Node next;
    }

    public static void main(String[] args) {
        Node node = null;
        for (int i = 0; i < 5; i++) {
            Node head = new Node();
            head.next = node;
            head.value = i;
            node = head;
        }
        System.out.println("order node:");
        Node orderNode = node;
        while (node != null) {
            System.out.println(node.value);
            Node next = node.next;
            node = next;
        }
        Node reverseNode = reverse(orderNode);
        System.out.println("reverse node:");
        while (reverseNode != null) {
            System.out.println(reverseNode.value);
            Node next = reverseNode.next;
            reverseNode = next;
        }
    }

    public static Node reverse(Node node) {
        Node newHead = null;
        while (node != null) {
            Node next = node.next;
            node.next = newHead;
            newHead = node;
            node = next;
        }
        return newHead;
    }
}

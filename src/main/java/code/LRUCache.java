package code;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2023-04-14 13:09
 */
@SuppressWarnings("WeakerAccess")
public class LRUCache {

    private Map<Integer, Node> cache;
    private int size;
    private int cap;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity + 1);
        this.cap = capacity;
        // 使用伪头部和伪尾部节点
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            Node newNode = new Node(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > cap) {
                // 如果超出容量，删除双向链表的尾部节点
                Node tempTail = removeNode(tail.pre);
                // 删除哈希表中对应的项
                cache.remove(tempTail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        // 因为用了伪尾结点 head.next不可能为null
        head.next.pre = node;
        head.next = node;
    }

    private Node removeNode(Node node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        return node;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 双向链表节点
     */
    private class Node {
        int key;
        int value;
        Node pre;
        Node next;

        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

}

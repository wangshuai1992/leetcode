package code;

/**
 * https://leetcode.com/problems/design-hashmap/
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-11-27 10:56
 */
public class DesignHashMap {

    private static class MyHashMap {

        Node[] bucket;

        static final int BUCKET_LEN = 100000;

        /** Initialize your data structure here. */
        public MyHashMap() {
            //这里也可以在每个位置生成一个(-1,-1)的dummyNode
            bucket = new Node[BUCKET_LEN];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hash = key % BUCKET_LEN;
            if(bucket[hash] == null) {
                bucket[hash] = new Node(key, value);
                return;
            }
            Node cur = bucket[hash];
            Node pre = null;
            while (cur != null) {
                if(cur.key == key) {
                    cur.val = value;
                    return;
                }
                pre = cur;
                cur = cur.next;
            }
            pre.next = new Node(key, value);
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hash = key % BUCKET_LEN;
            if(bucket[hash] == null) {
                return -1;
            }

            Node cur = bucket[hash];
            while (cur != null) {
                if(cur.key == key) {
                    return cur.val;
                }
                cur = cur.next;
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hash = key % BUCKET_LEN;
            if(bucket[hash] == null) {
                return;
            }

            Node cur = bucket[hash];
            Node pre = null;
            while (cur != null) {
                if(cur.key == key && pre == null) {
                    bucket[hash] = cur.next;
                    cur.next = null;
                    return;
                }
                if(cur.key == key) {
                    pre.next = cur.next;
                    cur.next = null;
                    return;
                }
                pre = cur;
                cur = cur.next;
            }
        }

        private static class Node {
            int key;
            int val;
            Node next;

            Node(int key, int val) {
                this.val = val;
                this.key = key;
            }
        }
    }

}

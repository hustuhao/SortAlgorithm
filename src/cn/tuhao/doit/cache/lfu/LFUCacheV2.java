package cn.tuhao.doit.cache.lfu;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LFU 缓存的实现
 * source:https://leetcode.com/problems/lfu-cache/discuss/1339757/Java-easy-to-understand-with-HashMap-and-TreeMap-with-explanation!!!
 *
 * the basic idea for this approach is that
 *
 * Save list of nodes having same count(frequency) as double linked list in tree map with count as key and double linked list as values
 * if we want to put a key,
 * a. if the key already exists in the nodeMap update the node value, update the position of the node in treeMap
 * b. if the keys doesn't exist create a new node and add it to nodeMap,
 * i. if the nodeMap size exceeds the capacity, delete the LFU node from nodeMap and dllMap.
 * c. add the node to dllMap.
 * if we want to get a key, check if exists in the nodeMap
 * a. if it exists, update the position of the node in dllMap by updating the count of the node.
 * b. if it doesn't exist, return -1.
 *
 * 核心思路：
 * 1、使用 HashMap 来记录 key-value 键值对，
 * 2、使用 TreeMap 来记录 key 出现的次数，出现相同次数的key-value节点会被保存到同一条双向链表中（DLL，Double Linked List）
 */
public class LFUCacheV2 {
    // 记录出现次数
    TreeMap<Integer, DLL> dllMap = new TreeMap<>();
    // 记录内容
    Map<Integer, Node> nodeMap = new HashMap<>();
    // 缓存容量
    int capacity;

    public LFUCacheV2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            updateNodePos(node);
            return node.val;
        }
        return -1;
    }

    // 更新dllMap中的位置
    public void updateNodePos(Node node) {
        DLL dll = dllMap.get(node.count);
        dll.removeNode(node);
        if(dll.head == null) {
            dllMap.remove(node.count);
        }
        node.count++;
        dllMap.computeIfAbsent(node.count, x -> new DLL()).addNode(node);
    }

    public void put(int key, int value) {
        if(capacity == 0)
            return;
        if(nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.val = value;
            updateNodePos(node);
            return;
        }
        Node node = new Node(key, value);
        nodeMap.put(key, node);
        if(nodeMap.size() > capacity) {
            DLL dll = dllMap.get(dllMap.firstKey());
            Node nd = dll.tail;
            nodeMap.remove(nd.key);
            dll.removeNode(dll.tail);
            if(dll.head == null)
                dllMap.remove(nd.count);
        }
        dllMap.computeIfAbsent(1, x -> new DLL()).addNode(node);
    }

    /**
     * DLL 是一个链表，保存出现相同次数的节点
     */
    class DLL {
        Node head;
        Node tail;

        /**
         * 往链表中更新节点
         * @param node
         */
        public void addNode(Node node) {
            if(head == null) {
                head = node;
                tail = node;
                return;
            }
            head.right = node;
            node.left = head;
            head = node;
        }

        /**
         * 移除链表中的节点
         * @param node
         */
        public void removeNode(Node node) {
            if(node == head) {
                head = head.left;
                node.left = null;
                if(head != null)
                    head.right = null;
            } else if (node == tail) {
                tail = tail.right;
                tail.left = null;
                // node.right = null;
            } else {
                node.left.right = node.right;
                node.right.left = node.left;
                node.left = null;
                node.right = null;
            }
        }

    }

    class Node {
        int key;
        int val;
        int count = 1;
        Node left;
        Node right;
        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
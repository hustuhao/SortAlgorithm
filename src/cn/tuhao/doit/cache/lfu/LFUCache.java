package cn.tuhao.doit.cache.lfu;

import java.util.HashMap;

/**
 * Java - all operations O(1) using 2 HashMap and Doubly-linked-list
 * https://leetcode.com/problems/lfu-cache/discuss/1323186/Java-all-operations-O(1)-using-2-HashMap-and-Doubly-linked-list-with-epxplantion-comments
 *
 */
public class LFUCache {

    HashMap<Integer, LFUNode> keyValues;
    HashMap<Integer, LFUNode> freqValues;
    int lowestFrequency;
    int capacity;
    int size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        lowestFrequency = -1;
        keyValues = new HashMap<Integer, LFUNode>();
        freqValues = new HashMap<Integer, LFUNode>();
    }

    public int get(int key) {
        if (!keyValues.containsKey(key))
            return -1;

        LFUNode node = keyValues.get(key);
        // we need to increase the frequency of the key and hence move it from old frequency doubly linked list to new higher frequency doubly linked list.
        removeNode(node);
        node.increaseFreq();
        addNode(node);

        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        LFUNode node;

        if (keyValues.containsKey(key)) {
            node = keyValues.get(key);
            removeNode(node);
            node.increaseFreq();
            node.updateValue(value);
        } else {
            if (size == capacity)
                removeLeastFrequentElement();
            else
                size++;

            node = new LFUNode(key, value);
            // Whenever a new node is added, it's frequency will be 1 and it will be the new lowest frequency.
            lowestFrequency = 1;
        }

        addNode(node);
    }

    private void removeNode(LFUNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private void addNode(LFUNode node) {
        keyValues.put(node.key, node);

        LFUNode start;

        if (!freqValues.containsKey(node.freq)) {
            start = new LFUNode(-10, -10);
            LFUNode end = new LFUNode(-10, -10);
            start.next = end;
            end.prev = start;

            // any new node will go and sit next to start. When we need to remove the oldest node at any given frequency, we will need to traverse the full list.
            // To avoid this traverse, we can point start.prev to end which will allow us to directly jump at the end of the list in O(1) time and
            // get the node we need to remove as end.prev
            start.prev = end;

            freqValues.put(node.freq, start);
        } else  {
            start = freqValues.get(node.freq);
        }

        node.next = start.next;
        start.next = node;
        node.prev = start;
        node.next.prev = node;

        // If the previous frequency of this node was the lowestFrequency, it might be possible that we removed the last node of that frequency.
        // In this case, we need to update the lowestFrequency with current frequency of node
        int prevFreq = node.freq - 1;
        if (size == capacity && prevFreq == lowestFrequency) {
            start = freqValues.get(prevFreq);

            // only for the last node in the list, the "next" will be set to null. We can use it to check if there are any value at this frequency
            if (start.next.next == null) {
                lowestFrequency = node.freq;
            }
        }
    }

    private void removeLeastFrequentElement() {
        LFUNode start = freqValues.get(lowestFrequency);
        LFUNode nodeToRemove = start.prev.prev;
        removeNode(nodeToRemove);
        keyValues.remove(nodeToRemove.key);
    }

    class LFUNode {
        LFUNode prev;
        LFUNode next;
        int freq;
        int key;
        int val;

        public LFUNode(int key, int val) {
            this.prev = null;
            this.next = null;
            this.freq = 1;
            this.val = val;
            this.key = key;
        }

        public void increaseFreq() {
            this.freq++;
        }

        public void updateValue(int val) {
            this.val = val;
        }
    }
}
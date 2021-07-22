package cn.tuhao.doit.cache.lru;

import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 一种 LRU 缓存的实现方法
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V>{
    private int MAX_ENTRIES;
    public LRUCache(int size) {
        this.MAX_ENTRIES = size;
    }
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > MAX_ENTRIES;
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        cache.put(1, 1);
        cache.put(2, 1);
        cache.put(3, 1);
        cache.put(4, 1);
        cache.put(5, 1);
        cache.put(6, 1);
        // 最后cache剩下： 2, 3, 4, 5, 6
        Assert.assertTrue(cache.size() == 5 && null == cache.get(1));
    }
}

package com.learntest.collectionstest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yanglin
 * @date 2022/11/2 18:26
 */
public class LRUCacheTest {

    static class LRUCache<K,V> extends LinkedHashMap<K,V>{

        private final int CACHE_SIZE;

        public LRUCache(int cacheSize){
            super((int) (Math.ceil(cacheSize/0.75)+1),0.75f,true);
            CACHE_SIZE = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size()>CACHE_SIZE;
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer,Integer> cache = new LRUCache<>(10);
        for (int i = 0; i < 10; i++) {
            cache.put(i,i);
        }
        cache.get(0);
        cache.put(11,11);
        cache.forEach((k,v)->{
            System.out.println("k:"+k+"v:"+v);
        });
    }
}

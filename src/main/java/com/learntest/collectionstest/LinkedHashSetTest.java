package com.learntest.collectionstest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * @author yanglin
 * @date 2020/10/8 13:38
 */
public class LinkedHashSetTest {

    public static void main(String[] args) throws IOException {
//        LinkedHashSet<Integer> set = new LinkedHashSet<>();
//        HashSet<Integer> set = new HashSet<>();
//        System.out.println(set.add(1));
//        System.out.println(set.add(0));
//        System.out.println(set.toString());
//        Map<Integer,Integer> map = new HashMap<>(20);
//        Map<Integer,Integer> map = new TreeMap<>();
//        map.put(1,null);
//        System.out.println(map.toString());
//        Integer length = 16;
//        Integer hash = 5;
//        System.out.println(hash%length);
//        System.out.println(hash&(length-1));
//        List<Map<String,Object>> source = new ArrayList<>();
//        Map<String,Object> sourcem = new HashMap<>();
//        sourcem.put("key",1);
//        source.add(sourcem);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String str = objectMapper.writeValueAsString(source);
//        List<Map<String,Object>> list = objectMapper.readValue(str,new TypeReference<List<Map<String,Object>>>() {
//        });
//        System.out.println(list.toString());
        int n = 17-1;
        n |= n >>>1;
        n |= n >>>2;
        n |= n >>>4;
        n |= n >>>8;
        n |= n >>>16;
        System.out.println(n+1);
    }
}

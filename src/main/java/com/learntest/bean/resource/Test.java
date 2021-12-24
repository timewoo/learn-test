package com.learntest.bean.resource;


import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.producer.TransactionListener;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author yanglin
 * @date 2020/12/30 12:27
 */
public class Test {

    public static void main(String[] args) {
//        Integer[] index = new Integer[]{3, 3, 9, 4, 9, 2, 2, 8, 9, 9, 8, 9};
//        HashMap<String, Long> collect = Arrays.stream(index).sorted().collect(Collectors.groupingBy(String::valueOf, HashMap::new, Collectors.counting()));
//        LinkedHashMap<String, Long> collect1 = collect.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//        System.out.println(collect1.keySet().toString());
//        ArrayList<Integer> objects = new ArrayList<>(Arrays.asList(2, 5, 3, 9, 7));
//        System.out.println(f(objects,8));
//        System.out.println(foo(Arrays.asList("not", "add", "dog", "acid", "add", "elf", "gap", "cat", "rat"),"d"));
//        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 3, 5, 5, 5, 11, 6, 11));
//        sort(list);
//        String str1 = "a"+"b"+"c";
//        String str = "abc";
//        System.out.println(str==str1);
        int i =4;
        Integer a = 3;
        System.out.println(i+"----------"+a);
        callByValue(i,a);
        System.out.println(i+"-----------"+a);
    }

    public static void callByValue(int i,Integer a){
        i = 2;
        a = 8;
    }

    public static void sort(List<Integer> list){
        HashMap<Integer, Long> map = list.stream().collect(Collectors.groupingBy(i->i, HashMap::new, Collectors.counting()));
        LinkedHashMap<Integer, Long> result = map.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().thenComparing(Map.Entry.comparingByKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(result.keySet().toString());
    }

//    public static List<String> foo(List<String> list, String subString) {
//        if (CollectionUtils.isEmpty(list)){
//            return list;
//        }
//        switch(subString){
//            case "a":
//                return list.stream().filter(s->s.contains("a")).collect(Collectors.toList());
//            case "b":
//                return list.stream().map(s->new StringBuilder(s).reverse().toString()).collect(Collectors.toList());
//            case "c":
//                return list.stream().distinct().collect(Collectors.toList());
//            case "d":
//                return list.stream().limit(3).collect(Collectors.toList());
//            default:
//                return list;
//        }
//    }
//
    public static boolean f(List<Integer> list,int sum){
        Collections.sort(list);
        int left=0;
        int right = list.size()-1;
        boolean flag = false;
        while (left<right){
            if (list.get(left)+list.get(right)<sum){
                left++;
            }else if (list.get(left)+list.get(right)>sum){
                right--;
            }else {
                flag= true;
                break;
            }
        }
        return flag;
    }

//    public static void main(String[] arg) {
//        try {
//            get();
//        } catch (Exception ex) {
//            System.out.println("Catch");
//            return;
//        } finally {
//            System.out.println("Finally");
//        }
//        System.out.println("Exit");
//    }
//    public static void get() {
//        System.out.println("Get");
//        throw new NullPointerException();
//    }

}

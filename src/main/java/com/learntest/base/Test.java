package com.learntest.base;


import javax.sound.midi.Soundbank;
import java.lang.module.ModuleDescriptor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanglin
 * @date 2020/10/5 13:56
 */
public class Test {

    public static void main(String[] args) {
//        String str = "a";
//        String str1 = "a";
//        System.out.println(str==str1);
//        System.out.println(str.equals(str1));
//        Long a = 2L;
//        Integer b = 1;
//        Long c= 1L;
//        Integer d = 1;
//
//        System.out.println(a.equals(b+c));
//        System.out.println(a.equals(b+d));
//        String inputVersion = "1.3.1";
//        List<String> orderList = List.of("0.0.0", "1.2.3", "1.3.2", "1.5.4", "2.0.10");
//        System.out.println(checkRangeOrder(inputVersion, orderList));
//        List<String> outOfOrderList = List.of("1.5.4", "2.0.10", "0.0.0", "1.3.2", "1.2.3");
//        System.out.println(checkRangeOutOfOrder(inputVersion,outOfOrderList));
        swapValue(3,5);
    }

    public static String checkRangeOrder(String v, List<String> list) {
        ModuleDescriptor.Version version = ModuleDescriptor.Version.parse(v);
        int index = list.size()-1;
        for (int i = 0; i < list.size(); i++) {
            ModuleDescriptor.Version parse = ModuleDescriptor.Version.parse(list.get(i));
            if (version.compareTo(parse) < 0) {
                index=i-1;
                break;
            }
        }
        return list.get(index);
    }

    public static String checkRangeOutOfOrder(String version,List<String> list){
        List<String> orderList = list.stream().sorted(Comparator.comparing(String::toString, (l, r) -> {
            ModuleDescriptor.Version left = ModuleDescriptor.Version.parse(l);
            ModuleDescriptor.Version right = ModuleDescriptor.Version.parse(r);
            return left.compareTo(right);
        })).collect(Collectors.toList());
        int index = orderList.size()-1;
        ModuleDescriptor.Version inputVersion = ModuleDescriptor.Version.parse(version);
        for (int i = 0; i < orderList.size(); i++) {
            ModuleDescriptor.Version parse = ModuleDescriptor.Version.parse(orderList.get(i));
            if (inputVersion.compareTo(parse) < 0) {
                index=i-1;
                break;
            }
        }
        return orderList.get(index);
    }

    public static void swapValue(Integer first,Integer seconds){
        System.out.println("swap before:"+first+","+seconds);
        first^=seconds;
        System.out.println(first);
        seconds^=first;
        System.out.println(seconds);
        first^=seconds;
        System.out.println(first);
        System.out.println("swap after:"+first+","+seconds);
    }
}

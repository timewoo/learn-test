package com.learntest.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.tomcat.util.digester.DocumentProperties;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglin
 * @date 2020/10/26 17:21
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        int total = 1000000;
        BloomFilter<CharSequence> charSequenceBloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,0.0001);
//        List<String> list = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            charSequenceBloomFilter.put("" + i);
//            list.add(""+i);
        }
//        int count = 0;
        System.out.println(System.currentTimeMillis());
//        for (int i = 0; i < total + 10000; i++) {
//            if (charSequenceBloomFilter.mightContain("" + i)) {
//                count++;
//            }
////            if (list.contains("" + i)) {
////                count++;
////            }
//        }
        System.out.println(charSequenceBloomFilter.mightContain(""+1950230));
        System.out.println(System.currentTimeMillis());
//        System.out.println(count);
    }
}

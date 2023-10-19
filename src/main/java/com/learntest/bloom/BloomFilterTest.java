package com.learntest.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.tomcat.util.digester.DocumentProperties;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanglin
 * @date 2020/10/26 17:21
 */
public class BloomFilterTest {


    public static void main(String[] args) {
        int total = 1000000;
        BloomFilter<CharSequence> charSequenceBloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total,0.0001);
        for (int i = 0; i < total; i++) {
            charSequenceBloomFilter.put("" + i);
        }
        int existCount=0;
        for (int i = 0; i < total; i++) {
            if (charSequenceBloomFilter.mightContain(""+i)){
                existCount++;
            }
        }
        System.out.println(existCount);
        int notExistCount=0;
        for (int i = total; i < total + 10000; i++) {
            if (charSequenceBloomFilter.mightContain("" + i)) {
                notExistCount++;
            }
        }
        System.out.println(notExistCount);
    }
}

package com.learntest.iotest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanglin
 * @date 2023/4/19 13:38
 */
public class FileConvert {

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\company\\ttwa\\v3.2\\test.md");
        HashMap<Integer, List<String>> map = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        AtomicInteger index = new AtomicInteger();
        index.set(211);
        bufferedReader.lines().forEach(line -> {
            String[] split = line.split(",", 9);
            if (split.length<9){
                List<String> list = map.get(index.get());
                if (CollectionUtils.isEmpty(list)){
                    list = new ArrayList<>();
                }
                list.add(line);
            }else {
                Integer id = Integer.valueOf(split[0]);
                if (!map.containsKey(id)){
                    ArrayList<String> list = new ArrayList<>();
                    list.add(line);
                    map.put(id,list);
                    index.set(id);
                }
            }
        });
        ArrayList<String> result = new ArrayList<>();
        map.forEach((k,v)->{
            String[] split = v.get(0).split(",", 9);
            String detail0 = split[8];
            Integer detailLastIndex = v.size()-1;
            if (StringUtils.isBlank(v.get(detailLastIndex))){
                detailLastIndex = v.size()-2;
            }
            String detailLast = v.get(detailLastIndex);
            if (detail0.startsWith("“")){
                detail0 = detail0.replaceFirst("^“", "\"");
            }
            if (detail0.startsWith("”")){
                detail0 = detail0.replaceFirst("^”", "\"");
            }
            if (detailLast.endsWith("“")){
                detailLast = detailLast.replaceFirst("“$", "\"");
            }
            if (detailLast.endsWith("”")){
                detailLast = detailLast.replaceFirst("”$", "\"");
            }
            if (detail0.startsWith("\"") && !detailLast.endsWith("\"")){
                detailLast = detailLast + "\"";
            }
            if (!detail0.startsWith("\"") && detailLast.endsWith("\"")){
                detail0 = "\"" + detail0;
            }
            split[8] = detail0;
            v.set(0,String.join(",", split));
            v.set(detailLastIndex,detailLast);
            result.addAll(v);
        });
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\company\\ttwa\\v3.2\\test2.md"));
        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(result.get(i));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}

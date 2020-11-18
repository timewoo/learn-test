package com.learntest.iotest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author yanglin
 * @date 2020/11/3 13:43
 */
public class FileIoTest {

    public static void main(String[] args) {
        read();
//        write();
    }

    public static void write() {
        String filePath = "C:\\Users\\11795\\Desktop\\文件2.txt";
        String context = "333333sdadsads单打决赛你打开技术的";
//        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
//            fileWriter.write(context);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //jdk nio
        try {
            Files.write(Paths.get(filePath),context.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        String filePath = "C:\\Users\\11795\\Desktop\\文件2.txt";
//        try (FileReader reader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(reader)) {
//            StringBuilder stringBuilder = new StringBuilder();
//            String str;
//            while (null != (str = bufferedReader.readLine())) {
//                stringBuilder.append(str);
//            }
//            System.out.println(stringBuilder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            System.out.println(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

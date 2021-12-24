package com.learntest.collectionstest;

import java.util.HashMap;

/**
 * @author yanglin
 * @date 2021/1/19 18:57
 */
public class MapTest {

    public static void main(String[] args) {
        // 1010
        int cap = 0;
        // 1001
        int n = cap-1;
        // n = n | n>>>1
        // 或(|) 只要有1就是1
        // 与(&) 都是1就是1
        // 异或(^) 不相同是1
        // 将数字的最高位的1的所有低位变成1，1xxxx->11111
        // 将数字加1变成不大于最小2的幂次方（比原来的数大1），11111+1->100000
        // 需要判断原来数就是2的幂次方，这样就会使原来的数变成2倍。
        //
        // 1001 | 0100 = 1101
        // 1010 | 0101 = 1111
        n |= n >>> 1;
        // 1101 | 0011 = 1111
        // 1111 | 0011 = 1111
        n |= n >>> 2;
        // 1111 | 0000 = 1111
        n |= n >>> 4;
        // 1111 | 0000 = 1111
        n |= n >>> 8;
        // 1111 | 0000 = 1111
        // 所有位全部都是1，比最小2的幂次方少1
        n |= n >>> 16;
        // 10000
        System.out.println(n);
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}

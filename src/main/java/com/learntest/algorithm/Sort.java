package com.learntest.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanglin
 * @date 2022/10/17 16:24
 */
public class Sort {

    public static void main(String[] args) {
        int[] giveArrays = new int[]{1, 42, 34, 1, 232, 45, 34, 32, 32, 1032, 23, 123, 5, 5, 42, 232, 4};
        int[] sortArrays = bubbleSort(giveArrays);
//        int[] sortArrays = selectionSort(giveArrays);
//        int[] sortArrays = quickSort(giveArrays, 0, giveArrays.length - 1);
//        int[] sortArrays = heapSort(giveArrays, giveArrays.length);
        System.out.println(Arrays.toString(sortArrays));
        List<Integer> list = new ArrayList<>();
    }

    public static int[] bubbleSort(int[] giveArrays) {
        for (int i = 0; i < giveArrays.length - 1; i++) {
            for (int j = 0; j < giveArrays.length - 1 - i; j++) {
                if (giveArrays[j] > giveArrays[j + 1]) {
                    swap(giveArrays, j, j + 1);
                }
            }
        }
        return giveArrays;
    }

    public static int[] selectionSort(int[] giveArrays) {
        for (int i = 0; i < giveArrays.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < giveArrays.length; j++) {
                if (giveArrays[min] > giveArrays[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(giveArrays, min, i);
            }
        }
        return giveArrays;
    }

    public static int[] quickSort(int[] giveArrays, int left, int right) {
        if (left >= right) {
            return giveArrays;
        }
        int index = left;
        int base = giveArrays[index];
        int i = left + 1;
        int j = right;
        while (true) {
            while (giveArrays[i] < giveArrays[index] && i < j) {
                i++;
            }
            while (giveArrays[j] > giveArrays[index]) {
                j--;
            }
            if (i < j) {
                giveArrays[i] ^= giveArrays[j];
                giveArrays[j] ^= giveArrays[i];
                giveArrays[i] ^= giveArrays[j];
                i++;
                j--;
            } else {
                break;
            }
        }
        giveArrays[index] = giveArrays[j];
        giveArrays[j] = base;
        index = j;
        quickSort(giveArrays, left, index - 1);
        quickSort(giveArrays, index + 1, right);
        return giveArrays;
    }

    public static int[] heapSort(int[] giveArrays, int length) {
        if (length <= 1) {
            return giveArrays;
        }
        for (int i = length / 2 - 1; i >= 0; i--) {
            buildBigHeap(giveArrays, i, length);
        }
        // swap tail and head
        swap(giveArrays, 0, length - 1);
        heapSort(giveArrays, length - 1);
        return giveArrays;
    }

    public static int[] buildBigHeap(int[] giveArrays, int i, int length) {
        int leftChild = 2 * i + 1;
        int rightChild = leftChild + 1;
        int max = i;
        // get the max value child
        if (rightChild <= length - 1) {
            max = giveArrays[leftChild] > giveArrays[rightChild] ? leftChild : rightChild;
        } else if (leftChild <= length - 1 && rightChild > length - 1) {
            max = leftChild;
        }
        if (giveArrays[i] < giveArrays[max]) {
            swap(giveArrays, i, max);
            buildBigHeap(giveArrays, max, length);
        }
        return giveArrays;
    }

    public static void swap(int[] giveArrays, int one, int two) {
        giveArrays[one] ^= giveArrays[two];
        giveArrays[two] ^= giveArrays[one];
        giveArrays[one] ^= giveArrays[two];
    }

}

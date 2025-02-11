package com.phasmidsoftware.dsaipg.sort.elementary;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class InsertionSortBenchmark {

    public static void main(String[] args) {

        int[] sizes = {100, 200, 400, 800, 1600};


        for (int n : sizes) {
            System.out.println("Array size: " + n);


            Integer[] randomArray = generateRandomArray(n);
            benchmarkSort(randomArray, "Random");


            Integer[] orderedArray = generateOrderedArray(n);
            benchmarkSort(orderedArray, "Ordered");


            Integer[] partiallyOrderedArray = generatePartiallyOrderedArray(n);
            benchmarkSort(partiallyOrderedArray, "Partially-Ordered");


            Integer[] reverseOrderedArray = generateReverseOrderedArray(n);
            benchmarkSort(reverseOrderedArray, "Reverse-Ordered");

            System.out.println();
        }
    }

    private static void benchmarkSort(Integer[] array, String description) {

        for (int i = 0; i < 10; i++) {
            InsertionSortComparator.sort(array.clone());
        }


        long startTime = System.nanoTime();
        InsertionSortComparator.sort(array.clone());
        long endTime = System.nanoTime();

        double timeTaken = (endTime - startTime) / 1_000_000.0;
        System.out.println(description + " array: " + timeTaken + " ms");
    }

    private static Integer[] generateRandomArray(int n) {
        Random random = new Random();
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);
        }
        return array;
    }

    private static Integer[] generateOrderedArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        return array;
    }

    private static Integer[] generatePartiallyOrderedArray(int n) {
        Integer[] array = generateOrderedArray(n);
        Random random = new Random();
        for (int i = 0; i < n / 10; i++) {
            int index1 = random.nextInt(n);
            int index2 = random.nextInt(n);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    }

    private static Integer[] generateReverseOrderedArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = n - i - 1;
        }
        return array;
    }
}

package indi.daniel.fling.algorithm.sort;

import java.util.Random;

/**
 * Created by daniel on 2018/11/20.
 */
public class Main {
    public static void main(String[] args) {
        int[] array = GetOriginalArray(100);

        System.out.println("Original Array:");
        print(array);

        performSort(array, BubbleSort::sort, "Bubble");
        performSort(array, SelectionSort::sort, "Selection");
        performSort(array, InsertionSort::sort, "Insertion");
        performSort(array, HeapSort::sort, "Heap");
    }

    public static int[] GetOriginalArray(int n) {

        Random rand = new Random(System.currentTimeMillis());
        int[] array = new int[n];

        for(int i = 0; i < n; i ++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }

    public static int[] performSort(int[] array, SortAction sortAction, String sortName) {
        System.out.println(sortName + " sort:");
        int[] sortedArray = array.clone();
        System.out.println(sortAction);
        sortAction.sort(sortedArray);
        print(sortedArray);
        return sortedArray;
    }

    public static void print(int[] array) {
        int n = array.length;

        System.out.print("{");
        for(int i = 0; i < n; i ++) {
            System.out.print(array[i] + ",");
        }
        System.out.println("}");
        System.out.println("");
    }

}


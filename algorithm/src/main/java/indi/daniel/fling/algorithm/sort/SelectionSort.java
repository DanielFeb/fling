package indi.daniel.fling.algorithm.sort;

/**
 * Created by daniel on 2018/11/20.
 */
public class SelectionSort {
    public static void sort(int[] array) {
        int n = array.length;
        int tmp;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            tmp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = tmp;
        }
    }
}

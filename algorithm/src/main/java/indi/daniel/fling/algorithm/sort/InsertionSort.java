package indi.daniel.fling.algorithm.sort;

/**
 * Created by daniel on 2018/11/20.
 */
public class InsertionSort {
    public static void sort (int[] array) {
        int n = array.length;

        int insertion;

        for (int i = 1; i < n; i++) {
            insertion = array[i];
            int j = i - 1;
            while(j >= 0 && insertion < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = insertion;
        }
    }
}

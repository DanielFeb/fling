package indi.daniel.fling.algorithm.sort;

/**
 * Created by daniel on 2018/11/20.
 */
public class BubbleSort {
    public static void sort(int[] array) {
        int n = array.length;
        Integer tmp;

        for(int i = (n - 1); i >= 0; i --) {
            for(int current = 0; current < i; current ++) {
                Integer next = current + 1;
                
                if (array[current] > array[next]) {
                    tmp = array[current];
                    array[current] = array[next];
                    array[next] = tmp;
                }
            }
        }
    }
}

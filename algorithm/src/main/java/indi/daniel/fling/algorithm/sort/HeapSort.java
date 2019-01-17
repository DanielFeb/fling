package indi.daniel.fling.algorithm.sort;

/**
 * Created by daniel on 2018/11/21.
 */
public class HeapSort {
    public static void sort(int[] array) {
        int n = array.length;

        for (int i = n - 1; i > 0; i --) {
            int parentNode = (i-1)/2;
            if ((i & 1) == 1) {
                //i是奇数
                if(array[i] > array[i/2]) {
                    swap(array, i, i/2);
                }
                parentNode --;
            }
            for (; parentNode >= 0 ; parentNode--) {

                int leftNode = 2 * parentNode +1;
                int rightNode = leftNode + 1;

                if(array[leftNode] > array[parentNode]) {
                    swap(array, leftNode, parentNode);
                }
                if(array[rightNode] > array[parentNode]) {
                    swap(array, rightNode, parentNode);
                }
            }
            swap(array, 0, i);
        }

    }


    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}

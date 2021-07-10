package cn.tuhao.array;

import java.util.Arrays;

/**
 * 合并有序数组
 * 数组的合并和链表的合并区别：数组的合并，位置是确定的，填值就行；链表需要操作节点。
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.mergeTwo(new int[]{1,2,3,0,0,0}, 3,new int[]{4,5,6}, 3);
    }

    /**
     * 方法1：填充B的元素到A之后，对A数组进行排序
     * 要求把数组B合并到数组A
     * @param A 有序数组A
     * @param m 有效元素有m个，前m个
     * @param B 有序数组B
     * @param n 有效元素有n个，前n个
     */
    public void merge(int A[], int m, int B[], int n) {
        for (int i = m, j = 0; i <= A.length - 1; i++,j++) {
            A[i] = B[j];
        }
        // 快排
        Arrays.sort(A);
    }

    /**
     * 方法2：倒着合并数组
     * 要求把数组B合并到数组A
     */
    public void mergeTwo(int A[], int m, int B[], int n) {
        int a = m - 1;
        int b = n - 1;
        // m  + n - 1 为合并后数组的最大值的下标
        for (int i = m + n -1; i >= 0; i--) {
            if (a >= 0 && b >= 0) {
                if (A[a] >= B[b]) {
                    A[i] = A[a];
                    a--;
                } else {
                    A[i] = B[b];
                    b--;
                }
                continue;
            }

            if (b < 0) {
                A[i] = A[a];
                a--;
                continue;
            }

            if (a < 0) {
                A[i] = B[b];
                b--;
            }
        }
    }
}

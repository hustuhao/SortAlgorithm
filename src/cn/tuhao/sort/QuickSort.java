package cn.tuhao.sort;

import org.junit.Test;
import java.util.LinkedList;

/**
 * pivot 基准值，哨兵
 * 时间复杂度：最糟糕得情况下时间复杂度是O(n²)，平均的复杂度是O(nlogn)
 * @核心思想：
 * 1、分而治之，每一轮确定基准值key的位置。
 * 2、同时利用key，将区间划分为大于key的区间和小于key的区间
 * @方法
 * 方法一：递归
 * 方法二：非递归（队列 ）
 */
public class QuickSort {
    /**
     * 快速排序算法实现 1
     * @param arr
     * @param low
     * @param high
     */
    public void quickSortA(int[] arr,int low,int high){
        // 设置区间哨兵
        int i=low, j=high;
        // 设置基准值
        int key=arr[low];
        //本轮结束条件 i==j
        while(i < j)
        {
            //从后面找，直到直到符合条件的数 x < key
            while(i<j && arr[j]>=key) j--;

            //交换a[i]和a[j]
            if(i < j) {
                swap(arr, i, j);
                i++;
            }
            //从前面找，直到直到符合条件的数 x > key
            while(i < j && arr[i] <= key) i++;
            //交换a[i]和a[j]
            if(i < j){
                swap(arr, i, j);
                j--;
            }
        }
        //本轮结束，确定了基准值的位置i，开始下面几轮,确定左右两个区间的位置
        /*将序列以key为界分成大于key和小于key的两部分：不把key放入其中*/
        if(i > low)
            quickSortA(arr,low,i-1);
        if(j < high)
            quickSortA(arr,i+1,high);
    }

    /**
     * 快排非递归实现
     * 考点：分而治之的思想，分成两个区间，每个区间用两个指针
     * @param arr
     */
    public void quickSortB(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(low); // 存区间 [low , high]
        queue.offer(high);
        while(!queue.isEmpty()){ // 用队列存储区间信息
            low = queue.poll();
            high = queue.poll();
            // 设置首尾两个哨兵
            int i = low ,j = high;
            // 设置基准值，以基准值为界，分为小于基准值的区间和大于基准值的区间
            int key = arr[low];
            //本轮结束条件 i==j
            while (i < j) {
                //从后面找，直到直到符合条件的数 x < key
                while(i < j && arr[j] >= key) j--;
                //交换a[i]和a[j]
                if (i < j) {
                    swap(arr, i, j);
                    i++;
                }
                //从前面找，直到直到符合条件的数 x > key
                while(i < j && arr[i] <= key) i++;
                //交换a[i]和a[j]
                if(i < j){
                    swap(arr, i, j);
                    j--;
                }
            }

            if(i > low){
                queue.offer(low);
                queue.offer(i-1);
            }

            if(j < high){
                queue.offer(i+1);
                queue.offer(high);
            }
        }
    }

    public void swap (int[] arr, int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void testQuickSort(){
         //int array[] = new int[]{6, 4, 3, 7, 5, 1, 2};
         int array[] = new int[]{4, 5, 6, 1, 3, 2, 7};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSortA(array,0,array.length-1);
    }
}

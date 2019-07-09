package cn.tuhao.sort;

import cn.tuhao.common.ListNode;
import org.junit.Test;

/**
 * pivot 基准值
 */
public class QuickSort {
    /**
     * 快速排序算法实现 1
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSortA(int[] arr,int low,int high){
        int i=low;
        int j=high;
        int key=arr[low];

        while(i<j)
        {
            while(i<j && arr[j]>=key) j--;

            if(i<j)
            {
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                i++;
            }

            while(i<j && arr[i]<=key)i++;

            if(i<j)
            {
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                j--;
            }
        }
        /*将序列以key为界分成大于key和小于key的两部分：不把key放入其中*/
        if(i>low)quickSortA(arr,low,i-1);
        if(j<high)quickSortA(arr,i+1,high);
    }
    @Test
    public void testQuickSort(){
         int array[] = new int[]{6, 4, 3, 7, 5, 1, 2};
         QuickSort.quickSortA(array,0,array.length-1);
    }
}

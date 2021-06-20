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
        //本轮结束条件 i==j
        while(i<j)
        {
            //从后面找，直到直到符合条件的数 x < key
            while(i<j && arr[j]>=key) j--;

            //交换a[i]和a[j]
            if(i<j)
            {
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                i++;
            }
            //从前面找，直到直到符合条件的数 x > key
            while(i<j && arr[i]<=key)i++;
            //交换a[i]和a[j]
            if(i<j){
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
                j--;
            }
        }
        //本轮结束，开始下面几轮
        /*将序列以key为界分成大于key和小于key的两部分：不把key放入其中*/
        if(i>low)quickSortA(arr,low,i-1);
        if(j<high)quickSortA(arr,i+1,high);
    }
    @Test
    public void testQuickSort(){
         //int array[] = new int[]{6, 4, 3, 7, 5, 1, 2};
         int array[] = new int[]{4, 5, 6, 1, 3, 2, 7};
         QuickSort.quickSortA(array,0,array.length-1);
    }
}

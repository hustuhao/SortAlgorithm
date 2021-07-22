package cn.tuhao.sort;

import org.junit.Test;

/**
 * 选择排序(比较低级的排序算法，个人的看法)
 * 基本原理：从待排序的数据中寻找最小值，将其与序列最左边的数字进行交换
 * 时间复杂度：最糟糕的情况下：n+(n-1)+(n-2)+.....+1 = O(n2)
 * 序列从大到小排列是最糟糕的情况。
 *
 **/
public class SelectionSort {
    public void SelectionSort(int arr[]){
        for(int i = 0 ; i < arr.length - 1; i++){      //内循环,是每次都在减少,修改变量的定义
            int min = i;
            for(int j = i+1 ; j < arr.length ; j++){   //数组的元素进行判断
                if(arr[min] > arr[j]){                   //数组的换位
                    min = j;
                }
            }
            //将待排序序列的最小值放到i处
            swap(arr,min,i);
        }
    }

    public void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    @Test
    public void testSelectionSort(){
        int array[] = {6,4,3,7,5,1,2};
        SelectionSort(array);
    }
}

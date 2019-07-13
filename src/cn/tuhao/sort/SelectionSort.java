package cn.tuhao.sort;

import org.junit.Test;

/*选择排序(比较低级的排序算法，个人的看法)
* 基本原理：从待排序的数据中寻找最小值，将其与序列最左边的数字进行交换
* 时间复杂度：最糟糕的情况下：n+(n-1)+(n-2)+.....+1 = O(n2)
* 序列从大到小排列是最糟糕的情况。
*
* */
public class SelectionSort {
    public void SelectionSort(int arr[]){
        if(arr.length==0)
            return;
        int j;
        for(int i=0;i<arr.length;i++){
            //min:当前的最小值
            int min = arr[i];
            //minX当前最小值的位置
            int minX = i;
            for(j=i+1;j<arr.length;j++){
                /*记录最小的那个数和它的位置*/
                if(arr[j] < min){
                    min = arr[j];
                    minX = j;
                }
            }
            int temp = arr[minX];
            arr[minX] = arr[i];
            arr[i] = temp;
        }
    }
    @Test
    public void testSelectionSort(){
        int array[] = {6,4,3,7,5,1,2};
        SelectionSort(array);
    }
}

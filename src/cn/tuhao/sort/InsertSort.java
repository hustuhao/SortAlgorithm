package cn.tuhao.sort;

import org.junit.Test;

/*插入排序
* 时间复杂度：O(n2)
* 输入数据原本的排序是从大到小的时候是最糟糕的情况
* */
public class InsertSort {
    public void insertSort(int[] arr){
        int len = arr.length;
        if(len<=1)
            return;
        for(int i=0;i<len;i++){
            for(int j=i;j>0;j--){
                if(arr[j] < arr[j-1]) {
                    swap(arr, j, j-1);
                }else{
                    break;
                }
            }

        }
    }

    /*交换数组的两个位置*/
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    @Test
    public void testInsertSort(){
        int arr[] = new int[]{12,5,9,36,8,21,7};
        int[] result;
        insertSort(arr);
        /**
         * 过程：
         *  12 5 9 36 8 21 7
         *  5 12 9 36 8 21 7
         *  5 9 12 36 8 21 7
         *  5 9 12 36 8 21 7
         *  5 9 12 8 36 21 7 =>5 9 8 12 36 21 7 =>5 8 9 12 36 21 7
         *  5 8 9 12 21 36 7
         *  5 8 9 12 21 7 36=>5 8 9 12 7 21 36=>5  8 9 7 12 21 36=>...=>5 7 8 9 12 21 36
         */
    }
}


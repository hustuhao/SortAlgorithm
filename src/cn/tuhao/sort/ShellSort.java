package cn.tuhao.sort;

import org.junit.Test;

/**
 * 希尔排序，插入排序的一种改进方法
 * 与插入排序的不同之处在于：每一次数组序号不是变化1，而是变化increment
 **/
public class ShellSort {
    public void shellSort(int[] arr){
        int len = arr.length;
        int increment = len/3+1;
        while(increment >=1){
            /*这一段由直接插入排序改编而来
            * 新序列：i,i+increment,i+2*increment,...,i+n*increment
            * 对这个序列进行直接插入排序
            * 一个整个for循环对应一个序列
            * */
            for(int i=increment;i<len;i++){
                for(int j=i;j>0;j=j-increment){
                    //这里j-increment保证数组不越界
                    if((j-increment)>=0 && arr[j] < arr[j-increment] ) {
                        swap(arr, j, j-increment);
                    }else{
                        break;
                    }
                }
            }
            if(increment==1)break;//increment=1就是最后一次进行插入排序
            increment = increment/3+1;

        }

    }

    private void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    @Test
    public void testShellSortTest(){
        int arr[] = new int[]{12,5,9,36,8,21,7};
        int arr2[] = new int[]{6,5,4,3,2,1};
        int arr3[] = new int[]{};
        int arr4[] = new int[]{0};
        int arr5[] = new int[]{1,2,3,4,5,6};

        shellSort(arr);
        shellSort(arr2);
        shellSort(arr3);
        shellSort(arr4);
        shellSort(arr5);
    }
}

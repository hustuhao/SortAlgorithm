package cn.tuhao.sort;

import org.junit.Test;

import java.util.Arrays;

public class BinarySearch {
     /*
	     定义方法,实现,折半查找
		 返回值: 索引
		 参数: 数组,被找的元素
      */
    public int binarySearch(int[] arr, int key){
        int min = 0;
        int max = arr.length - 1;
        int mid = 0;
        while(min <= max){
            mid = (min + max)/2;// 优化：mid = min + ((max - min + 1 )>>1);
            if(key > arr[mid]) {
                min = mid + 1;
            }else if(key < arr[mid]){
                max = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    @Test
    public void fun(){

        int [] arr1 = {2,5,7,10,14,15,18,23,35,41,52};
        int [] arr2 = {2,5,7,10,14,14,14,23,35,41,52};
        int [] arr3 = {12};
        int [] arr4 = {};
        int [][] test = new int[][]{arr1,arr2,arr3,arr4};
        int[]  count = new int[1];
        int[] res= new int[test.length];
        for(int i=0;i<res.length;i++){
            res[i] = binarySearch(test[i],14);
        }
        System.out.println(Arrays.toString(res));
    }
}

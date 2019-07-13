package cn.tuhao.search;

import org.junit.Test;

/**
 * 二分查找法
 * 前提：序列已经由大到小排好序
 * 时间复杂度：O(log2N)
 * @param arr     目标数组
 * @param target  目标数字
 * @return 位置：-1表示没有找到。
 */
public class BinarySearch {
    public int BinarySearch(int arr[],int target){
        int result = -1;
        int high = arr.length-1;
        int low  = 0;
        int mid;

        if(arr.length==0){
            return result;
        }
        while (low < high){
            mid = (high+low)/2;
            if( target > arr[mid]){
                //注意这里不能是low = mid;
                low = mid+1;
            }else if(target < arr[mid]){
                high = mid;
            }else{
                result = mid;
                break;
            }

        }
        return result;
    }
    @Test
    public void testBinarySearch(){
        /*测试数字：1,30,8,7,50,-5*/
        int test[]    = new int[]{1,30,8,7,50,-5};
        int predict[] = new int[]{0,6,-1,3,-1,-1};
        int arr[]  = new int[]{1,3,5,7,9,15,30};
        int position = BinarySearch(arr,-5);
        System.out.println(position);
    }
}

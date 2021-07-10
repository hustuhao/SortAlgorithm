package cn.tuhao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * JZ13 调整数组顺序使奇数位于偶数前
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变
 *
 * 输入：
 * [1,2,3,4]
 * 返回值：
 * [1,3,2,4]
 */
public class ReOrderArrayTest {

    public int[] reOrderArrayOne (int[] array) {
        // write code here
        //双指针，头尾指针
        int[] nums = new int[array.length];
        int head = 0;
        int tail = array.length-1;
        int index_head = head;
        int index_tail = tail;
        while(head < array.length && tail >= 0){
            if(isOdd(array[head])){
                //奇数，放前面
                nums[index_head] = array[head];
                index_head++;
            }
            head++;
            if(!isOdd(array[tail])){
                //从后到前，如果为偶数，则从后开始填
                nums[index_tail] = array[tail];
                index_tail--;
            }
            tail--;
        }
        return nums;
    }

    /**
     * 利用插入排序
     * @param array int整型一维数组
     * @return int整型一维数组
     *
     * 1 2 3 4 5 6
     * 1 3 2 4 5 6
     * 1 3 5 2 4 6
     *
     * 2 4 6 1 3 5 8 11 9 10
     *
     */
    public int[] reOrderArray (int[] array) {
        //     nthOdd用来记录当前是第几个奇数---对应数组下标
        int nthOdd = -1;
        for(int i = 0; i < array.length; i++){
            if(isOdd(array[i])){
                nthOdd++;
                int curOdd = array[i];
                //  如果是个奇数，就把前面的偶数往后移一位，腾出来的位置即oddNth位，放入该奇数
                for(int j = i; j > nthOdd; j--){
                    array[j] = array[j-1];
                }
                array[nthOdd] = curOdd;
            }
        }
        return array;
    }

    /**
     * odd  number 奇数
     * even number
     */
    public boolean isOdd(int x) {
        return (x & 1) == 1;
    }
}

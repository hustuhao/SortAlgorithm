package cn.tuhao.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 滑动窗口的最大值
 */
public class MaxWindowValueTest {
    public static void main(String[] args) {
//        [2,3,4,2,6,2,5,1],3
        int[] nums = {2,3,4,2,6,2,5,1};
        int size = 3;
        MaxWindowValueTest maxWindowValueTest = new MaxWindowValueTest();
        ArrayList<Integer> arrayList = maxWindowValueTest.maxInWindows(nums, size);
    }


    public ArrayList<Integer> maxInWindows(int [] nums, int size) {
        // 窗口值大于数组的值
        if (size > nums.length || size == 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i <= nums.length - size; i++) {
            resultList.add(getMax(nums, i, i + size - 1));
        }
        return resultList;
    }

    private int getMax(int[] input, int start, int end) {
        int max = input[start];
        for (int i = start; i <= end; i++) {
            max = Math.max(max, input[i]);
        }
        return max;
    }
}

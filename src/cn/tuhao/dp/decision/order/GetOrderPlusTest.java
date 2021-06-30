package cn.tuhao.dp.decision.order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目描述：求正整数的全排序，可能会有重复的数
 * 考点：决策树 ,回溯算法
 * 比如：
 * 输入：1 2 3
 * 输出：{1,2,3},{1,3,2},{2,1,3}.{2,3,1},{3,1,2},{3,2,1}
 *
 * 输入：1 1 3
 * 输出：{1,1,3},{1,3,1},{3,1,1}
 */
public class GetOrderPlusTest {
    // 存放结果的数组
    public static List<List<Integer>> res = new LinkedList<>();
    // 主程序
    public List<List<Integer>> permutation(int nums[]) {
        // 边界判断
        if(nums == null || nums.length == 0)
            return new ArrayList<>();
        else
            // 回溯
            backtrace(nums,0,nums.length-1);
        return res;
    }

    /**
     * 回溯算法核心实现
     * @param nums 选择的列表
     * @param start 开始位置
     * @param end 结束位置
     */
    private void backtrace(int[] nums, int start, int end) {
        // 结束条件
        if(start == end){
            LinkedList<Integer> linkedList = new LinkedList<>();
            for(int i=0;i<=end;i++){
                linkedList.add(nums[i]);
            }
            res.add(linkedList);
            return;
        }
        // 在选择列表中做选择
        for(int i = start;i<=end;++i) {
            // 满足回溯条件：不重复
            if(isSwap(nums,start,i)){
                // 做选择
                Swap(nums,start,i);
                // 递归
                backtrace(nums, start+1, end);
                // 撤销选择
                Swap(nums,start,i);
            }
        }
    }
    public static void Swap(int list[], int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    /**
     * 判断当前 "i"处的字符是否之前已经出现过，出现过则返回 false，不交换
     * @param list
     * @param start
     * @param i
     * @return
     */
    private static boolean isSwap(int[] list, int start, int i) {
        for(int k = start; k<i;k++)
            if(list[k] == list[i])
                return false;
        return true;
    }

    public static void  main(String[] args){
//        int[] nums = {1,2,3};
        int[] nums = {1,1,3};
        GetOrderPlusTest getOrderPlusTest = new GetOrderPlusTest();
        final List<List<Integer>> permutation = getOrderPlusTest.permutation(nums);
    }
}

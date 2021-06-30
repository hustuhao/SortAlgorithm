package cn.tuhao.dp.decision.order;


import java.util.*;

/**
 * 题目描述，求一组不重复数正整数的全排列
 * 考点：决策树,回溯算法
 * 回溯算法核心逻辑：https://labuladong.gitee.io/algo/1/4/
 * 回溯算法框架：
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择:
 *             路径.add(选择))
 *         backtrack(路径, 选择列表)
 *         撤销选择:
 *             路径.remove(选择)
 *
 * 最后可能要对结果进行排序：
 * Arrays.sort(int[])
 * Arrays.sort(Integer[], Collections.reverseOrder())
 *
 */
public class GetOrderTest {
    public static List<List<Integer>> res = new LinkedList<>();

    /**
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> getAllOrder(int[] nums) {
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(nums, trace);
        return res;
    }
    private void backtrace(int[] nums, List<Integer> trace) {
        // 结束条件
        if (nums.length == trace.size()) {
            List<Integer> traceResultList = new ArrayList<>(trace);
            Collections.copy(traceResultList, trace);
            res.add(traceResultList);
            return;
        }
        for (int num : nums) {
            if (trace.contains(num)) {
                continue;
            }
            trace.add(num);
            backtrace(nums, trace);
            trace.remove(trace.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        GetOrderTest getOrderTest = new GetOrderTest();
        getOrderTest.getAllOrder(nums);
        System.out.println(res.toString());
        System.out.println("------------------------");
    }
}

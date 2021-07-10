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
 *
 *
 * 回溯是递归的一种形式，通常情况是，你面临一些选项，你必须选择其中一个。在你做出选择后，你又会得到一组新的选择，即你所得到的选项取决于你所做的选择。这种步骤不断重复，直到你到达最终状态，如果你一直做出对的选择，最后的状态就是目标状态。如果你没有，它就不是。
 *
 * 作者：xiao_ben_zhu
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses/solution/shou-hua-tu-jie-huan-yuan-dfs-hui-su-de-xi-jie-by-/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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

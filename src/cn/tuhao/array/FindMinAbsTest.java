package cn.tuhao.array;

import cn.tuhao.sort.BinarySearch;
import org.junit.Test;

/**
 * 获取顺序数组中的绝对值最小的数。
 * 考点：分治，查找
 */
public class FindMinAbsTest {

    @Test
    public void test() {
        FindMinAbsTest findMinAbsTest = new FindMinAbsTest();
//        int[] nums = {-4,-2,1,3,5};
//        int[] nums = {-4,-2,2,3,5};
//        int[] nums = {-4,-2,2,3,5};
//        int[] nums = {0,1,3,5};
        int[] nums = {-1,-3,-5,0};
        int minAbs = findMinAbsTest.getMinAbs(nums);
        System.out.println(minAbs);
    }

    /**
     * 找到一个数尽可能接近0
     * 核心思路：
     * 1、二分法查找，搜索左侧边界
     * 2、返回的值是大于或者等于0的第一个数
     * @param nums
     * @return
     */
    public int getMinAbs(int[] nums) {
        // 0| 特殊情况处理
        if (nums.length == 0) {
            throw new RuntimeException("参数错误");
        } else if (nums.length == 1) {
            return nums[0];
        }
        // 1、num >= 0
        int first = nums[0];
        int last = nums[nums.length - 1];
        if (first >= 0 && last >= 0) {
            return first;
        }

        // 2、num <= 0
        if (first <= 0 && last <= 0) {
            return last;
        }

        // 3、num 正负均有
        final int target = 0;
        BinarySearch binarySearch = new BinarySearch();
        int index = binarySearch.left_bound(nums, target);
        if (nums[index] == 0) {
            return nums[index];
        } else {
            return Math.min(Math.abs(nums[index]), Math.abs(nums[index - 1]));
        }
    }

    /**
     * 取巧：找到正负分界点
     * @param nums
     * @return
     */
    public int getMinAbsTwo(int[] nums) {
        // 0| 特殊情况处理
        if (nums.length == 0) {
            throw new RuntimeException("参数错误");
        } else if (nums.length == 1) {
            return nums[0];
        }
        // 1、num >= 0
        int first = nums[0];
        int last = nums[nums.length - 1];
        if (first >= 0 && last >= 0) {
            return first;
        }

        // 2、num <= 0
        if (first <= 0 && last <= 0) {
            return last;
        }

        // 3、 两者都有
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] < 0 && nums[i+1] >= 0) {
                return Math.min(Math.abs(nums[i]), Math.abs(nums[i+1]));
            }
        }
        throw new RuntimeException("过程异常");
    }
}

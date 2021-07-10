package cn.tuhao.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * leetcode 题目：错误的集合
 * https://leetcode-cn.com/problems/set-mismatch/solution/cuo-wu-de-ji-he-by-leetcode-solution-1ea4/
 * 集合 s 包含从 1 到 n的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-mismatch
 */
public class FindErrorNumsTest {

    /**
     * 1、哈希表
     * 2、排序
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int[] errors = new int[2];
        int curr;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            curr = nums[i];
            if (curr == pre) {
                errors[0] = curr;
            } else if (curr - pre > 1) {
                errors[1] = curr - 1;
            }
            pre = curr;
        }
        if (nums[nums.length - 1]!= nums.length) {
            errors[1] = nums.length;
        }
        return errors;
    }

    @Test
    public void test1() {
        int[] input = {1, 2, 2, 4};
        int[] corrects = {2,3};
        FindErrorNumsTest findErrorNumsTest = new FindErrorNumsTest();
        int[] errorNums = findErrorNumsTest.findErrorNums(input);
        Assert.assertArrayEquals(corrects, errorNums);
    }

    @Test
    public void test2() {
        int[] input = {1, 1};
        int[] corrects = {1,2};
        FindErrorNumsTest findErrorNumsTest = new FindErrorNumsTest();
        int[] errorNums = findErrorNumsTest.findErrorNums(input);
        Assert.assertArrayEquals(corrects, errorNums);
    }
}

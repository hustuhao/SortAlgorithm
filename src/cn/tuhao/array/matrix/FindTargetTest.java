package cn.tuhao.array.matrix;

import org.junit.Test;

/**
 * JZ1 二维数组中的查找
 * 思路：确定一个起点：往一个方向增，往一个方向减，比如左下角和右上角
 * 面试过的企业：滴滴
 */
public class FindTargetTest {

    /**
     * col 行，row 列
     * @param nums
     * @param target
     */
    public boolean findTarget(int[][] nums, int target) {
        int col = 0, row = nums[0].length - 1;
        while (col <= nums.length - 1 && row >= 0) {
            int curValue = nums[col][row];
            if (curValue == target) {
                return true;
            } else if (curValue > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }

    @Test
    public void test() {
        /**
         *  1 2 10
         *  1 3 11
         *  5 5 12
         *  7 8 15
         */
        FindTargetTest findTargetTest = new FindTargetTest();
        boolean target = findTargetTest.findTarget(new int[][]{{1, 1}}, 2);
    }
}

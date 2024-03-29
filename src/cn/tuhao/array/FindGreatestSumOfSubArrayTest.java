package cn.tuhao.array;

/**
 * JZ 连续子数组的最大和
 * 题目：
 * 输入一个整型数组,数组里有正数也有负数。
 * 数组中的一个或连续多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)。
 *
 * 核心解题思路：
 * 1、加入一个数求和，与原来的和作比较，取大者
 *
 * 两种解题方法：
 * 1、滑动窗口
 * 2、动态规划
 */
public class FindGreatestSumOfSubArrayTest {

    /**
     * 方法1：
     * 关于0x80000000为什么等于-2147483648和负数在内存上储存的问题
     * https://blog.csdn.net/youyou362/article/details/72667951/
     *
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int curSum = 0;
        // 赋值 Integer.MIN_VALUE,处理数组中全是负数的情况。
        int greatestSum = 0x80000000;
        for (int i = 0; i < array.length; i++) {
            if (curSum <= 0) {
                //当前和为负数则舍弃,重新计算求和
                curSum = array[i];
            }else {
                //当array[i]为正数时,加上之前的最大值并更新最大值。
                curSum += array[i];
            }
            if (curSum > greatestSum) {
                // 记录最大值
                greatestSum = curSum;
            }
        }
        return greatestSum;
    }

    /**
     * 动态规划
     *
     * F(i)：以array[i]为末尾元素的子数组的和的最大值,子数组的元素的相对位置不变
     * F(i)=max(F(i-1)+array[i], array[i])
     * res：所有子数组的和的最大值
     * res=max(res, F(i))
     *
     * 如数组[6, -3, -2, 7, -15, 1, 2, 2]
     * 初始状态：
     *
     *     F(0)=6
     *     res=6
     * i=1：
     *     F(1)=max(F(0)-3,-3)=max(6-3,3)=3
     *     res=max(F(1),res)=max(3,6)=6
     * i=2：
     *     F(2)=max(F(1)-2,-2)=max(3-2,-2)=1
     *     res=max(F(2),res)=max(1,6)=6
     * i=3：
     *     F(3)=max(F(2)+7,7)=max(1+7,7)=8
     *     res=max(F(2),res)=max(8,6)=8
     * i=4：
     *     F(4)=max(F(3)-15,-15)=max(8-15,-15)=-7
     *     res=max(F(4),res)=max(-7,8)=8
     * 以此类推
     * 最终res的值为8
     * @param array
     * @return
     */
    public  int FindGreatestSumOfSubArrayTwo(int[] array) {
        int res = array[0]; //记录当前所有子数组的和的最大值
        int max=array[0];   //包含array[i]的连续数组最大值
        for (int i = 1; i < array.length; i++) {
            max=Math.max(max+array[i], array[i]);
            res=Math.max(max, res);
        }
        return res;
    }
}

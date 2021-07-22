package cn.tuhao.tree.path;

import cn.tuhao.common.TreeNode;
import cn.tuhao.tree.SerializeTreeTest;
import org.junit.Before;
import org.junit.Test;

/**
 * 二叉树的最大路径和(必须在同一棵子树上)
 */
public class MaxPathSumTwo {
    // 如何计算最小值
    int MIN_VALUE = -10000;
    // 这里的最小值
    int res = MIN_VALUE;
    public int getMax(TreeNode root) {
        if (null == root) {
            return MIN_VALUE;
        }
        int max = Math.max(getMax(root.right), getMax(root.left));
        res = getMaxFour(res, root.val + max, max, root.val);
        return getMaxThree(root.val,max + root.val, max);
    }



    private int getMaxThree(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    private int getMaxFour(int a, int b, int c, int d) {
        return Math.max(Math.max(Math.max(a, b), c), d);
    }

    /*************** 测试用例 *******************/
    private TreeNode root1;
    private TreeNode root2;
    private TreeNode root3;
    private TreeNode root4;
    private TreeNode root5;
    @Before
    public void preTest() {
        SerializeTreeTest serializeTreeTest = new SerializeTreeTest();
        /**
         * 满二叉树
         *     1
         *   2   3
         * 4  5 6  7
         *
         * 输出：11
         */
        root1 = serializeTreeTest.Deserialize("1|2|3|4|5|6|7|-10086|-10086|-10086|-10086|-10086|-10086|-10086|-10086|");
        /**
         *     1
         *   4   3
         * 7  5 6  2
         *
         * 输出：12
         */
        root2 = serializeTreeTest.Deserialize("1|4|3|7|5|6|2|-10086|-10086|-10086|-10086|-10086|-10086|-10086|-10086|");
        /**
         *      -1
         *   4     6
         * -7  5
         *
         * 输出：9
         */
        root3 = serializeTreeTest.Deserialize("-1|4|6|-7|5|-10086|-10086|-10086|-10086|-10086|-10086|");

        /**
         *       -1
         *    -2     -3
         * -4  -5  -6  -7
         *
         * 输出：-1
         */
        root4 = serializeTreeTest.Deserialize("-1|-2|-3|-4|-5|-6|-7|-10086|-10086|-10086|-10086|-10086|-10086|-10086|-10086|");

        /**
         *     -1
         *   2   -3
         * -4  -5  -6  -7
         *
         * 输出：2
         */
        root5 = serializeTreeTest.Deserialize("-1|2|-3|-4|-5|-6|-7|-10086|-10086|-10086|-10086|-10086|-10086|-10086|-10086|");

    }

    @Test
    public void test() {
        MaxPathSumTwo maxPathSumTwo = new MaxPathSumTwo();
        // 11
        int r1 = maxPathSumTwo.getMax(root1);
        maxPathSumTwo.res = Integer.MIN_VALUE;
        // 12
        int r2 = maxPathSumTwo.getMax(root2);
        maxPathSumTwo.res = Integer.MIN_VALUE;
        // 9
        int r3 = maxPathSumTwo.getMax(root3);
        maxPathSumTwo.res = Integer.MIN_VALUE;
        // -1
        int r4 = maxPathSumTwo.getMax(root4);
//        maxPathSumTwo.res = Integer.MIN_VALUE;
        // 2
        int r5 = maxPathSumTwo.getMax(root5);
    }

}

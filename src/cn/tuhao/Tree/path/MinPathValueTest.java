package cn.tuhao.tree.path;

import cn.tuhao.common.TreeNode;
import cn.tuhao.tree.SerializeTreeTest;
import org.junit.Before;
import org.junit.Test;

/**
 * 求路径上上和的最小值
 */
public class MinPathValueTest {

    /**

     * @param root
     * @return
     */
    public int getShortPathValue(TreeNode root) {
        // 1 | 结束条件
        if (null == root) {
            return 0;
        }

        // 2 | 返回节点的值
        if (null == root.right && null == root.left) {
            return root.val;
        }
        // 3 | 比较左右子树路径值和的大小
        int rightValue = getShortPathValue(root.right);
        int leftValue = getShortPathValue(root.left);
        // 4 | 返回结果
        return Math.min(rightValue, leftValue) + root.val;
    }


    /*************** 测试用例 *******************/
    private TreeNode root1;
    private TreeNode root2;
    private TreeNode root3;
    @Before
    public void preTest() {
        SerializeTreeTest serializeTreeTest = new SerializeTreeTest();
        /**
         * 满二叉树
         *     1
         *   2   3
         * 4  5 6  7
         */
        root1 = serializeTreeTest.Deserialize("1|2|3|4|5|6|7|-10086|-10086|-10086|-10086|-10086|-10086|-10086|-10086|");
        /**
         *     1
         *   4   3
         * 7  5 6  2
         */
        root2 = serializeTreeTest.Deserialize("1|4|3|7|5|6|2|-10086|-10086|-10086|-10086|-10086|-10086|-10086|-10086|");
        /**
         *     1
         *   4   6
         * 7  5
         */
        root3 = serializeTreeTest.Deserialize("1|4|6|7|5|-10086|-10086|-10086|-10086|-10086|-10086|");
    }

    @Test
    public void test() {
        MinPathValueTest minPathValueTest = new MinPathValueTest();
        int r1 = minPathValueTest.getShortPathValue(root1);
        int r2 = minPathValueTest.getShortPathValue(root2);
        int r3 = minPathValueTest.getShortPathValue(root3);
    }
}

package cn.tuhao.tree.path;

import cn.tuhao.common.TreeNode;
import cn.tuhao.tree.SerializeTreeTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 神策数据面试题：二叉树根节点到叶子节点的所有路径和
 *
 *
 */
public class AllPathSumValue {
    //记录所有的路径
    private ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();
    // 单个路径
    private ArrayList<Integer> path = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> getAllPathSumValue(TreeNode root) {
        /*结束时候的情况*/
        if(root == null) return pathList;

        /*向路径中增加一个结点*/
        path.add(root.val);

        /**
         *到达叶子结点 就是目标路径值已经满足的情况，此时就结束本次路径搜索
         */
        if(root.left == null && root.right == null) {
            pathList.add(new ArrayList<>(path));
        }
        // 没有利用到返回值
        getAllPathSumValue(root.left);
        getAllPathSumValue(root.right);
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }
        return pathList;
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
        AllPathSumValue allPathSumValue1 = new AllPathSumValue();
        AllPathSumValue allPathSumValue2 = new AllPathSumValue();
        AllPathSumValue allPathSumValue3 = new AllPathSumValue();
        ArrayList<ArrayList<Integer>> list1 = allPathSumValue1.getAllPathSumValue(root1);
        ArrayList<ArrayList<Integer>> list2 = allPathSumValue2.getAllPathSumValue(root2);
        ArrayList<ArrayList<Integer>> list3 = allPathSumValue3.getAllPathSumValue(root3);
    }
}

package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

/**
 * JZ4 重建二叉树
 * 题目：根据前、中序遍历重建二叉树
 * 考点：中序遍历和前序遍历确定二叉树，数组指针
 *
 */
public class ReConstructBinaryTreeTest {

    /**
     *         1
     *       2   3
     *     4   5
     *
     *     1,2,4,5,3
     *     4,2,5,1,3
     *
     *     2 4 5   3
     *     4 2 5   3
     *
     *     4 5
     *
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        // 边界检查
        if (pre.length == 0) {
            return null;
        }
        return reConstructBinaryTree(pre, 0, in, 0 , pre.length - 1);
    }

    public TreeNode reConstructBinaryTree(int [] pre, int pStart, int [] in, int start, int end) {
        // 结束条件
        if (start == end) {
            return new TreeNode(pre[pStart]);
        } else if (start > end) {
            return null;
        }
        int rootIndex = getRootIndex(pre[pStart], in, start, end);
        TreeNode root = new TreeNode(pre[pStart]);
        TreeNode left = reConstructBinaryTree(pre, pStart + 1, in, start, rootIndex - 1);
        TreeNode right = reConstructBinaryTree(pre,pStart + 1 + rootIndex - start, in, rootIndex + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 寻找父节点
     * @param rootValue 父节点的值
     * @param in 中序遍历数组
     * @param start 起点
     * @param end 终点
     * @return 父节点在中序遍历数组中位置下标
     */
    private int getRootIndex(int rootValue, int[] in, int start, int end) {
        int rootIndex = -1;
        for (int i = start; i <= end; i++) {
            if (rootValue == in[i]) {
                rootIndex = i;
                break;
            }
        }
        if (rootIndex == -1) {
            throw new RuntimeException("不合法的遍历数组");
        }
        return rootIndex;
    }

    public static void main(String[] args) {
        ReConstructBinaryTreeTest test = new ReConstructBinaryTreeTest();
//        int [] pre = {1,2,4,5,3};
//        int [] in = {4,2,5,1,3};
//        int [] pre = {1,2,3};
//        int [] in = {2,1,3};
        /**
         *         1
         *      2    3
         *    4     5  6
         */
        int [] pre = {1,2,4,3,5,6};
        int [] in = {4,2,1,5,3,6};
        TreeNode treeNode = test.reConstructBinaryTree(pre, in);
    }
}

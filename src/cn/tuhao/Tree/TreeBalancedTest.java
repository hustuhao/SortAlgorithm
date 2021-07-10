package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

/**
 * JZ39 平衡二叉树
 * 平衡二叉树
 */
public class TreeBalancedTest {
    /**
     * 求平衡树的深度
     * @param root 根节点
     * @return 返回深度，如果不满足平衡树的要求，则返回-1
     */
    public int depth(TreeNode root){
        if(root == null)
            return 0;
        int left = depth(root.left);
        //如果发现子树不平衡之后就没有必要进行下面的高度的求解了
        if(left == -1)
            return -1;
        int right = depth(root.right);
        //如果发现子树不平衡之后就没有必要进行下面的高度的求解了
        if(right == -1)
            return -1;
        if(Math.abs(left-right) > 1) {
            return -1;
        } else {
            return 1 + Math.max(left, right);
        }
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root) != -1;
    }
}

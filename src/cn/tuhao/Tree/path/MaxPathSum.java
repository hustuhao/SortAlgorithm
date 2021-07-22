package cn.tuhao.tree.path;

import cn.tuhao.common.TreeNode;

/**
 * NC6 二叉树的最大路径和(路径连通就行)
 * 描述：描述
 * 给定一个二叉树，请计算节点值之和最大的路径的节点值之和是多少。
 * 这个路径的开始节点和结束节点可以是二叉树中的任意节点
 *
 * 输入：
 *    1
 * 2     3
 *
 * 输出：1+2+3=6
 */
public class MaxPathSum {
    public int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return res;
    }

    public int getMax(TreeNode root){
        if(root == null) return 0;
        int leftMax = Math.max(0,getMax(root.left));
        int rightMax = Math.max(0,getMax(root.right));
        //*1.--下面一行划重点--//
        res = Math.max(res, root.val+leftMax+rightMax);
        //*2--这一行也很重要--//
        return Math.max(leftMax,rightMax) + root.val;
    }
}

package cn.tuhao.tree.path;

import cn.tuhao.common.TreeNode;

import java.util.ArrayList;

/**
 * NC9 二叉树中是否存在节点和为指定值的路径
 * 给定一个二叉树和一个值sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径，
 */
public class FindTargetPathSumValue {
    private ArrayList<ArrayList<Integer>> pathList = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> path = new ArrayList<Integer>();

    /*
    target  : 路径值
    pathList : 所有的路径
    path    : 单个路径
    */
    public boolean hasPathSum(TreeNode root, int target) {
        /*结束时候的情况*/
        if(root == null) return !pathList.isEmpty();

        /*向路径中增加一个结点*/
        path.add(root.val);
        target -= root.val;

        /*
        target == 0且到达叶子结点 就是目标路径值已经满足的情况，此时就结束本次路径搜索
        */
        if(target == 0 && root.left == null && root.right == null)
            pathList.add(new ArrayList<Integer>(path));
        // 没有利用到返回值
        hasPathSum(root.left, target);
        hasPathSum(root.right, target);
        // list.remove(list.size()-1);
        return !pathList.isEmpty();
    }
}

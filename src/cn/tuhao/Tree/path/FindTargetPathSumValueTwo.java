package cn.tuhao.tree.path;

import cn.tuhao.common.TreeNode;

import java.util.ArrayList;

/**
 * NC8 二叉树根节点到叶子节点和为指定值的路径
 * 描述：给定一个二叉树和一个值 sum，
 * 请找出所有的根节点到叶子节点的节点值之和等于 sum 的路径，
 */
public class FindTargetPathSumValueTwo {
    private ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();;
    private ArrayList<Integer> path = new ArrayList<>();;

    public ArrayList<ArrayList<Integer>> pathSum (TreeNode root, int sum) {
        if (null == root) {
            return pathList;
        }
        // 将节点的值增加到路径
        path.add(root.val);
        sum -= root.val;

        // 当到叶子节点且路径和等于 sum 时，才是目标路径
        // 这里可以扩展：寻找某一范围的路径值，或者找特定子路径值的路径
        if (sum == 0 && null == root.left && null == root.right) {
            pathList.add(new ArrayList<>(path));
        }
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        // 注意这里需要移除刚加入的节点，去遍历另外的路径
        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }
        return pathList;
    }
}

package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

/**
 * JZ17 树的子结构
 * 判断root2是否为root1的子树
 */
public class HasSubtreeTest {

    public boolean hasSubTree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            if (judge(root1, root2)) {
                return true;
            }
        }
        return hasSubTree(root1.left, root2) && hasSubTree(root1.right, root2);
    }

    /**
     * 判断子结构是否相等
     * @param root1 父
     * @param root2 子
     * @return
     */
    public boolean judge(TreeNode root1, TreeNode root2) {
        if (null == root1) {
            return true;
        }

        if (null == root2) {
            return false;
        }

        if (root1.val == root2.val) {
            return judge(root1.right, root2.left) && judge(root1.right, root2.right);
        }
        return false;
    }
}

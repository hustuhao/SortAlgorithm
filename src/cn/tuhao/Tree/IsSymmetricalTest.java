package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

import java.util.Collections;

/**
 * JZ58 对称的二叉树
 * 递归解法：
 */
public class IsSymmetricalTest {
    boolean isSymmetrical(TreeNode pRoot) {
        if (null == pRoot) {
            return false;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        if (null == root1 && null ==  root2) {
            return true;
        }

        if (null == root1 || null == root2) {
            return false;
        }
        // 继续递归的条件
        if (root1.val == root2.val) {
            return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
        }

        return false;
    }
}

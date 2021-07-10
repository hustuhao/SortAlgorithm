package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的遍历：递归实现
 * 代码逻辑基本上完全一样
 */
public class PrintTreeRecursiveTest {
    /**
     * 前序遍历
     * @param root 根节点
     * @return
     */
    public List<Integer> preOrderTraversalRecursive(TreeNode root){
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        preOrderTraversalRecursive(root, resultList);
        return resultList;
    }
    public void preOrderTraversalRecursive(TreeNode node, List<Integer> resultList){
        // 结束递归条件：节点为空
        if (null == node) {
            return;
        }
        resultList.add(node.val);
        preOrderTraversalRecursive(node.left, resultList);
        preOrderTraversalRecursive(node.right, resultList);
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inOrderVisitRecursive(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        inOrderVisitRecursive(root, resultList);
        return resultList;
    }

    public void inOrderVisitRecursive(TreeNode node, List<Integer> resultList){
        if (node == null) {
            return;
        }
        // 先访问左孩子
        inOrderVisitRecursive(node.left, resultList);
        resultList.add(node.val);
        // 最后访问右孩子
        inOrderVisitRecursive(node.right, resultList);
    }

    /**
     * 后序遍历
     * @param root
     * @param
     */
    public List<Integer> poOrderVisitRecursive(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (null == root) {
            return resultList;
        }
        poOrderVisitRecursive(root, resultList);
        return resultList;
    }

    public void poOrderVisitRecursive(TreeNode node, List<Integer> resultList) {
        if (null == node) {
            return;
        }
        poOrderVisitRecursive(node.left, resultList);
        poOrderVisitRecursive(node.right, resultList);
        resultList.add(node.val);
    }

    /*
     * 测试用例：
     *     2
     *   3   1
     * 4        6
     * 前序遍历： 2 3 4 1 6
     * 中序遍历： 4 3 2 1 6
     * 后序遍历： 4 3 6 1 2
     * */
    @Test
    public void testTraversal(){
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(6);
        root.left  = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;

        PrintTreeRecursiveTest pt = new PrintTreeRecursiveTest();
//        List<Integer> list = pt.preOrderTraversalRecursive(root);
//        List<Integer> list = pt.inOrderVisitRecursive(root);
        List<Integer> list = pt.poOrderVisitRecursive(root);
        System.out.println(list);
        //
    }
}

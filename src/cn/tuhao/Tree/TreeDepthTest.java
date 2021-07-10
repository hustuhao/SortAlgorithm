package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

public class TreeDepthTest {
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return  left > right ? left + 1 : right + 1;
    }

    public int treeDepthWithoutRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        int level = 0;
        // BFS 广度优先遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode curNode = queue.poll();
                if (null != curNode.left) {
                    queue.offer(curNode.left);
                }

                if (null != curNode.right) {
                    queue.offer(curNode.right);
                }
            }
            level ++;
        }
        return level;
    }



    @Test
    public void test1() {
        /**
         *      8
         *    / \
         *   6   10
         *  / \  /\
         * 5  7 9 11
         */
        TreeNode one = new TreeNode(8);
        TreeNode two = new TreeNode(6);
        TreeNode three = new TreeNode(10);
        TreeNode four = new TreeNode(5);
        TreeNode five = new TreeNode(7);
        TreeNode six = new TreeNode(9);
        TreeNode seven = new TreeNode(11);

        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        TreeDepthTest treeDepthTest = new TreeDepthTest();
        int level = treeDepthTest.treeDepthWithoutRecursive(one);
        System.out.println("----------------------");
    }
}

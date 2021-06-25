package cn.tuhao.Tree;

import cn.tuhao.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 按层次打印二叉树
 */
public class PrintTreeByLevel {
    /**
     *      8
     *    / \
     *   6   10
     *  / \  /\
     * 5  7 9 11
     * 逐层打印：8 _ 6 10 _ 5 7 9 11
     * 考点：队列的特性，先进先出
     * @param pRoot
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode pRoot) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        TreeNode curNode;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (queue.size() > 0) {
            curNode = queue.poll();
            if (null == curNode) {
                return arrayList;
            }
            arrayList.add(curNode.val);
            if (null != curNode.left) {
                queue.offer(curNode.left);
            } else if (null != curNode.right) {
                queue.offer(curNode.right);
            }
        }
        return arrayList;
    }
}

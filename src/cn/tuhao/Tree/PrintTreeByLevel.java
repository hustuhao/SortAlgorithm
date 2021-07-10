package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @desc JZ22 从上往下打印二叉树
 * @url https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701
 * 按层次打印二叉树,BFS
 *
 * 非递归算法：
 * 1、初始化：一个队列queue<TreeNode*> q， 将root节点入队列q
 * 2、如果队列不空，做如下操作：
 * 3、弹出队列头，保存为node，将node的左右非空孩子加入队列
 * 4、做2,3步骤，知道队列为空
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
        if (null == pRoot) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        TreeNode curNode;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!queue.isEmpty()) {
            curNode = queue.poll();
            if (null == curNode) {
                return arrayList;
            }
            arrayList.add(curNode.val);
            if (null != curNode.left) {
                queue.offer(curNode.left);
            }
            if (null != curNode.right) {
                queue.offer(curNode.right);
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        /**
         *     8
         *    / \
         *   6   10
         *  / \  /\
         * 5  7 9 11
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
        PrintTreeByLevel printTreeByLevel = new PrintTreeByLevel();
        ArrayList<Integer> arrayList = printTreeByLevel.PrintFromTopToBottom(one);
        System.out.println("----------------------");
    }
}

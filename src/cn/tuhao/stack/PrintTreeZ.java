package cn.tuhao.stack;

import cn.tuhao.common.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * 对于LinkedList
 * 栈：push和 pop  操作，把第一元素当做栈顶
 * 队列：offer和 poll
 */
public class PrintTreeZ {
    public static void main(String[] args) {
//        {8,6,10,5,7,9,11}
//        [[8],[10,6],[5,7,9,11]]
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
        PrintTreeZ printTreeZ = new PrintTreeZ();
        ArrayList<ArrayList<Integer>> print = printTreeZ.PrintOne(one);
        System.out.println("----------------------");
    }

    /**
     * 利用两个栈分开保存每一层的数据，如果只使用一个栈，无法确认每一层在哪个节点结束
     * @param pRoot 根节点
     * @return
     */
    public ArrayList<ArrayList<Integer>> PrintOne(TreeNode pRoot) {
        if (null == pRoot) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        stack1.push(pRoot);
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        // stack1 和 stack2 分别保存相邻层的数据
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                saveLevelData(stack1, stack2, resultList, true);
            } else {
                saveLevelData(stack2, stack1, resultList, false);
            }
        }
        return resultList;
    }

    /**
     * 保存每一层的数据
     * @param stack1 栈1
     * @param stack2 栈2
     * @param resultList 最终的结果
     * @param leftFirst 控制顺序:true:从左到右,false:从右到左
     */
    private void saveLevelData(LinkedList<TreeNode> stack1,
                               LinkedList<TreeNode> stack2,
                               ArrayList<ArrayList<Integer>> resultList,
                               Boolean leftFirst) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack1.isEmpty()) {
            TreeNode curNode = stack1.pop();
            // 1、当当前遍历的节点为空时，继续出栈
            if (null == curNode) {
                continue;
            }
            // 2、控制顺序
            if (leftFirst) {
                arrayList.add(curNode.val);
                // 2.1 | 注意这里没有对左右孩子节点作非空判断,在1处做判断加continue
                stack2.push(curNode.left);
                stack2.push(curNode.right);
            } else {
                arrayList.add(curNode.val);
                stack2.push(curNode.right);
                stack2.push(curNode.left);
            }
        }
        // 层数据为空，则不加入最终的结果中
        if (!arrayList.isEmpty()) {
            resultList.add(arrayList);
        }
    }

}

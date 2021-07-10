package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树前序、中序、后序遍历非递归写法的透彻解析：
 * https://blog.csdn.net/zhangxiangdavaid/article/details/37115355
 */
public class PrintTreeWithoutRecursiveTest {
    /**
     * 前序遍历
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(TreeNode root){
        List<Integer> resultList = new ArrayList<>();
        TreeNode curNode = root;
        Stack<TreeNode> stack = new Stack();
        while(null != curNode || !stack.isEmpty()){
            //一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while(curNode!=null){
                resultList.add(curNode.val);
                stack.push(curNode);
                curNode = curNode.left;
            }
            // 左子树遍历完，遍历右子树
            if(!stack.isEmpty()) {
                curNode = stack.pop();
                curNode = curNode.right;
            }
        }
        return resultList;
    }

    /**
     * 中序遍历
     * 考点：用栈来控制当前遍历的元素
     * 注意：stack.pop 当 Stack 为空的时候会抛异常
     * @param root
     * @return
     */
    public List<Integer> inOrderTraversal(TreeNode root){
        TreeNode curNode = root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> resultList = new ArrayList<>();
        while (null != curNode || !stack.isEmpty()) {
            // 一直遍历到左子树最下边，边遍历边保存根节点到栈中
            while (null != curNode) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            // 左子树遍历完，遍历右子树
            if (!stack.isEmpty()) {
                curNode = stack.pop();
                resultList.add(curNode.val);
                curNode = curNode.right;
            }
        }
        return resultList;
    }

    /**
     * 后序遍历：先左子树，后右子树，再根节点。
     * 难点：需要判断上次访问的节点是位于左子树，还是右子树。
     * 1、若是位于左子树，则需跳过根节点，先进入右子树，再回头访问根节点；
     * 2、若是位于右子树，则直接访问根节点。
     *
     * 考点：指针，栈
     *
     * 算法：最近访问的节点 Last
     * 1、定位到树的最左下角的节点LA(保证出栈的元素的左子树一定访问完了)，入栈
     * 2、出栈，判断出栈的节点T是否可以访问
     *  2.1 if (null == T.right 或 Last==T.right) // 可以访问根节点的前提：在左子树访问完后,右子树为空或者或右子树访问完
     *          visit(T);
     *          Last = T;
     *  2.2 else if (curNode.left == lastVisitNode) //这里可以直接省略else if 使用else
     *          curNode = 出栈;
     *          // 遍历右子树，定位到右子树的最左下角（保证栈中的元素的左子树在出栈的时候一定访问完）
     *          curNode = curNode.right
     *          while (null != curNode)
     *              curNode 进栈
     *              curNode = curNode.left;
     *
     *
     *
     * @param root
     * @return
     */
    public List<Integer> poOrderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> resultList = new ArrayList<>();
        // 记录上一个访问节点的指针
        TreeNode lastVisitNode = null;
        // 当前节点， 定位到指针到左子树最下边（左下角）
        TreeNode curNode = root;
        while (null != curNode) {
            stack.push(curNode);
            curNode = curNode.left;
        }
        while (!stack.isEmpty()) {
            //走到这里，curNode都是空，并已经遍历到左子树底端(看成扩充二叉树，则空，亦是某棵树的左孩子)
            curNode = stack.pop();
            // 上一步访问的是右子树 :一个根节点被访问的前提是：无右子树或右子树已被访问过
            if (null == curNode.right || lastVisitNode == curNode.right) {
                resultList.add(curNode.val);
                lastVisitNode = curNode;
            } else {
                // 上一步访问的是左子树：else if (curNode.left == lastVisitNode),上面的条件没有满足，一定就满足这个条件
                // 暂时不访问的节点进栈：父节点再次入栈
                stack.push(curNode);
                // 遍历右子树:左子树访问完，再访问右子树
                curNode = curNode.right;
                while (null != curNode) {
                    stack.push(curNode);
                    curNode = curNode.left;
                }
            }
        }
        return resultList;
    }

    @Test
    public void testTraversal(){
        /*
        *     2
        *   3   1
        * 4        6
        * 前序遍历： 2 3 4 1 6
        * 中序遍历： 4 3 2 1 6
        * 后序遍历： 4 3 6 1 2
        * */
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(6);
        root.left  = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;

        PrintTreeWithoutRecursiveTest pt = new PrintTreeWithoutRecursiveTest();
        List<Integer> list = pt.inOrderTraversal(root);
//        List<Integer> list = pt.poOrderTraversal(root);
//        List<Integer> list = pt.preOrderTraversal(root);
        System.out.println(list);
    }
}

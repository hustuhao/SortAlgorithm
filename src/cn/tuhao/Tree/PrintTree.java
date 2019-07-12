package cn.tuhao.Tree;

import cn.tuhao.common.TreeNode;
import cn.tuhao.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * 还未验证
 */
public class PrintTree {
    /**
     * 前序遍历
     * @param root
     * @return
     */
    public List<TreeNode> preOrderTraversal(TreeNode root){
        List<TreeNode> list = new ArrayList<TreeNode>();
        TreeNode temp = root;
        Stack<TreeNode> stack = new Stack();
        while(temp!=null || !stack.isEmpty()){
            while(temp!=null){
                list.add(temp);
                stack.push(temp);
                temp = temp.left;
            }
            if(!stack.isEmpty()) {
                temp = stack.pop().right;
            }
//            temp = stack.pop().right; 这种也可以
        }
        return list;
    }


    @Test
    public void testTraversal(){
        /*
        *     1
        *   2   3
        * 4        5
        * 前序遍历： 1 2 4 3 5
        * */
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left  = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;

        PrintTree pt = new PrintTree();
        List<TreeNode> list = pt.preOrderTraversal(root);
        System.out.println(list);
    //
    }
}

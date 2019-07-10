package cn.tuhao.Tree;

import cn.tuhao.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 还未验证
 */
public class PrintTree {
    public List<TreeNode> preOrderTraversal(TreeNode root){
        List<TreeNode> list = new ArrayList<TreeNode>();
        TreeNode temp = root;
        Stack<TreeNode> stack = new Stack();
        while(root!=null && !stack.isEmpty()){
            while(root!=null){
                list.add(temp);
                stack.push(temp);
                temp = temp.left;
            }
                temp = stack.pop().right;
        }
        return list;
    }
}

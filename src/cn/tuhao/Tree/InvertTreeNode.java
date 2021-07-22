package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 阿里巴巴面试题目：二叉树的反转
 */
public class InvertTreeNode {
    /**
     * @Description： 目标：实现二叉树的反转
     * @Params:
     * @return:
     * @author: Mr.Wang
     * @create: 20:19
     */
    public static void main(String[] args) {
        InvertTreeNode Solution = new InvertTreeNode();
        TreeNode root = new TreeNode(5);
        TreeNode ret = Solution.invertNode1(root);

    }

    /**
     * @Description：  递归式算法   （ 容易造成栈溢出 )
     * @Params:     TreeNode root  根节点
     * @return:     TreeNode root  反转后的根节点
     * @author: Mr.Wang
     */
    public TreeNode invertNode1(TreeNode root){
        if(root == null)
            return root;
        TreeNode temp = root;
        root.left = invertNode1(root.right);
        root.right = invertNode1(temp);
        return root;
    }
    /**
     * @Description：  非递归方法实现  逐层进行替换
     * @Params:     TreeNode root  根节点
     * @return:     TreeNode root  反转后的根节点
     * @author: Mr.Wang
     */
    public TreeNode invertNode2(TreeNode root){
        if(root==null)
            return null;
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode current=queue.poll();
            TreeNode temp=current.left;
            current.left=current.right;
            current.right=temp;
            if(current.left!=null)
                queue.add(current.left);
            if(current.right!=null)
                queue.add(current.right);
        }
        return root;
    }
}


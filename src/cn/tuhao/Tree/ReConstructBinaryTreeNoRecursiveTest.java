package cn.tuhao.tree;

import cn.tuhao.common.TreeNode;

import java.util.Stack;

/**
 * 非递归实现：根据前中序遍历结果构建二叉树
 * 考点：栈，数组指针
 */
public class ReConstructBinaryTreeNoRecursiveTest {
    /**
     *
     * @param pre 前序遍历数组
     * @param in 中序遍历数组
     * @return 重建好的树
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre.length == 0 ) {
            return null;
        }
        // 存储数组指针的栈
        Stack<Integer> pStack = new Stack<Integer>();
        // 存储树的栈
        Stack<TreeNode> tStack = new Stack<TreeNode>();
        TreeNode cur;
        // 中序遍历数组和前序遍历数组的初始值
        pStack.push(0);
        pStack.push(pre.length - 1);
        pStack.push(0);
        pStack.push(in.length - 1);
        // 定义指针
        int pStart;
        int pEnd;
        int iStart;
        int iEnd;
        TreeNode root = new TreeNode(-1);
        tStack.push(root);
        while(!tStack.isEmpty()) {
            iEnd = pStack.pop();
            iStart = pStack.pop();
            pEnd = pStack.pop();
            pStart = pStack.pop();
            cur = tStack.pop();
            int index = find(pre[pStart], in, iStart, iEnd);
            cur.val = pre[pStart];

            // 左边是否还有子树：左边的长度
            int lenL = index - iStart;
            if (lenL != 0) {
                pStack.push(pStart + 1);
                pStack.push(pStart + lenL);
                pStack.push(iStart);
                pStack.push(index - 1);
                // 占坑，等会儿赋值
                cur.left = new TreeNode(-1);
                tStack.push(cur.left);
            }
            // 右边是否还有子树：右边的长度
            int lenR = iEnd - index;
            if (lenR != 0) {
                pStack.push(pStart + lenL + 1);
                pStack.push(pEnd);
                pStack.push(index + 1);
                pStack.push(iEnd);
                cur.right = new TreeNode(-1);
                tStack.push(cur.right);
            }
        }
        return root;
    }

    /**
     * 寻找父节点
     * @param target
     * @param all
     * @param start
     * @param end
     * @return
     */
    int find(int target, int[] all, int start , int end){
        for(int i = start; i <= end; ++i){
            if(all[i] == target) return i;
        }
        return -1;
    }
}

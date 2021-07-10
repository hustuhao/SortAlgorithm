package cn.tuhao.tree;

import org.junit.Test;

import java.util.Stack;

/**
 * JZ23 二叉搜索树的后序遍历序列
 *
 * 题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
 * （ps：我们约定空树不是二叉搜索树）
 *
 * 考点：二叉搜索树，树的后序遍历特点，最后访问节点
 *
 * 递归实现算法：倒序递归
 * 1、数组sequence[n]最后一个元素为父节点
 * 2、遍历父节点的右子树：元素均大于父节点
 * 3、遍历父节点的左子树：芫荽均小于父节点
 * 4、对左右子树进行上述检查
 *
 * 时间复杂度计算：O(nlogn)
 * 递归方法在每一层的遍历开销是O(n)，
 * 而对于二叉树而言，递归的层数平均是O(logn)，因此，递归方法的最终复杂度是O(nlogn)
 */
public class VerifySequenceOfBSTest {
    public boolean VerifySequenceOfBST(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        // 找到左子树和右子树的分界点
        boolean[] result = new boolean[1];
        // 结果数组默认为true
        result[0] = true;
        VerifySequenceOfBST(sequence, 0, sequence.length - 1, result);
        return result[0];
    }

    private void VerifySequenceOfBST(int [] sequence, int start, int end, boolean[] result) {
        // 结束条件：没有子树 或者 结果数组为false时
        if (start >= end || !result[0]) {
            return;
        }
        // 遍历右子树，父节点的值为 sequence[end]
        int nodeIndex = -1;
        for (int i = end - 1; i >= start; i--) {
            if(sequence[end] > sequence[i]) {
                nodeIndex = i;
                break;
            }
        }
        // 遍历左子树
        for (int i = nodeIndex - 1; i >= start; i--) {
            if(sequence[end] < sequence[i]) {
                result[0] = false;
                return;
            }
        }
        // 判断左子树，与下面的注释部分等价
        VerifySequenceOfBST(sequence, start, nodeIndex, result);
        // 判断右子树
        VerifySequenceOfBST(sequence, nodeIndex + 1, end - 1, result);

// nodeIndex = -1说明没有左子树
//        if (nodeIndex <= -1) {
//            // 判断左子树
//            VerifySequenceOfBST(sequence, start, end - 1, result);
//        } else {
//            // 判断左子树
//            VerifySequenceOfBST(sequence, start, nodeIndex, result);
//            // 判断右子树
//            VerifySequenceOfBST(sequence, nodeIndex + 1, end - 1, result);
//        }
    }

    /**
     * 非递归实现：https://blog.nowcoder.net/n/8fe97e67996249ccbe71328d3a49c4af
     * 核心思想：
     * 对于任意一棵子树，均有“左子树<根节点<右子树”，
     * 因此，它的根节点约束了它左右子树的取值范围，二叉搜索树的根root是它左子树值的上限（max），同时是它右子树值的下限（min）。
     * 如果我们从根节点出发往下走，那么高层祖辈节点序列就会不停地对低层未遍历节点形成一个上下限约束，
     * 只要低层节点没有违背这个约束，那么它就是合法的，否则，序列就是不合法的。
     * ()[https://uploadfiles.nowcoder.com/images/20190904/709610362_1567529856108_CDD12A4C3648F4C15C64C15DF3EE1FA2]
     * @param sequence
     * @return
     */
    public boolean VerifySequenceOfBSTNoRecursive(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MIN_VALUE);
        int max = Integer.MAX_VALUE;
        for (int i = sequence.length - 1; i >= 0; i--) {
            int num = sequence[i];
            if (num > max)
                return false;
            while (num < stack.peek()) {
                max = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }

    @Test
    public void test1() {
        int[] input1 = {2,9,3,4,7,6,5};
        int[] input2 = {4,5};
        int[] input3 = {5};
        int[] input4 = {};
        int[] input5 = {4,8,6,12,16,14,10};
        int[] input6 = {10,4,9,12,16,14,11};
        int[] input7 = {4,8,6,12,16,14,10};
        VerifySequenceOfBSTest test = new VerifySequenceOfBSTest();
//        System.out.println(test.VerifySequenceOfBST(input1));
//        System.out.println(test.VerifySequenceOfBST(input2));
//        System.out.println(test.VerifySequenceOfBST(input3));
//        System.out.println(test.VerifySequenceOfBST(input4));
//        System.out.println(test.VerifySequenceOfBST(input5));
//        System.out.println(test.VerifySequenceOfBST(input6));
        System.out.println(test.VerifySequenceOfBSTNoRecursive(input7));
    }
}

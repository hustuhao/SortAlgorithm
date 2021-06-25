package cn.tuhao.stack;

/**
 * @desc JZ23 二叉搜索树的后序遍历序列
 * @url https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd
 */
public class PostOrderVisitTest {
    public static void main(String[] args) {
//        boolean b = VerifySquenceOfBST(new int[]{7,4,9,3,8,11,12,10}, 0, 7);
//        boolean b = VerifySquenceOfBST(new int[]{4,8,6,12,16,14,10}, 0, 6);
        boolean b = VerifySquenceOfBSTTwo(new int[]{4,6,7,5}, 0, 3);
        System.out.println("---------");
    }
    /**
     * 方法一：非递归解法
     * 考点：二叉搜索树的定义
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBSTOne(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        int size = sequence.length;
        while (--size > 0) {
            // 指针指向当前元素
            int cur = 0;
            // 左子树的节点 < 根节点
            while (cur <= size && sequence[cur++] < sequence[size]);
            // 右子树的节点 > 根节点
            while (cur <= size && sequence[cur++] > sequence[size]);
            if (cur <= size)
                return false;
        }
        return true;
    }

    /**
     * 递归解法
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBSTTwo(int[] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        return VerifySquenceOfBSTTwo(sequence, 0, sequence.length - 1);
    }

    public static boolean VerifySquenceOfBSTTwo(int[] sequence,int left , int right) {
        if (left >= right) {
            return true;
        }
        // 遍历左子树：判断是否满足左子树的值 < 根节点的值
        int i = left;
        for (; i < right; i++) {
            if (sequence[i] > sequence[right]) {
                break;
            }
        }
        // 遍历右子树：判断是否满足右子树的值 > 根节点的值
        int j = i;
        for (; j < right; j++) {
            if (sequence[j] < sequence[right]) {
                break;
            }
        }

        // j == right 的时候，就说明左右子树通过二叉搜索树检验
        if (j < right) {
            return false;
        }

        return VerifySquenceOfBSTTwo(sequence, left, i - 1) && VerifySquenceOfBSTTwo(sequence, i, right - 1);
    }
}

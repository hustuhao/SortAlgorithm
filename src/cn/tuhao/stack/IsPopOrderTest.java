package cn.tuhao.stack;

import java.util.LinkedList;

public class IsPopOrderTest {

    public static void main(String[] args) {
        IsPopOrderTest isPopOrderTest = new IsPopOrderTest();
        boolean b = isPopOrderTest.IsPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
        boolean c = isPopOrderTest.IsPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 2, 1});
        System.out.println("------------------");

    }
    /**
     * 描述
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        // 每个数组一个指针 [1,2,3,4,5],[4,3,5,1,2]
        // [1,2,3,5],[3,5,1,2]
        // [1,2,5],[5,1,2]
        // [1,2],[1,2]
        LinkedList<Integer> stack = new LinkedList<>();
        if (pushA.length != popA.length || pushA.length == 0) {
            return false;
        }

        int i = 0, j = 0;
        // 遍历插入数组
        while (i < pushA.length) {
            // 不等则说明在栈中，执行入栈操作
            if (pushA[i] != popA[j]) {
                stack.add(pushA[i++]);
            } else {
                // 相等则执行出栈操作：两数组指针移动
                i++;j++;
                // 检查是执行出栈操作还是入栈操作
                while (!stack.isEmpty() && stack.getLast().equals(popA[j])) {
                    stack.removeLast();
                    ++j;
                }
            }
        } // end outer while
        return stack.isEmpty();
    }

    /**
     * 更容易理解，将 pop 和 push 操作分开，每个元素必定执行pop，
     * 而上面的算法，如果进栈之后马上出栈，则不执行pop，直接跳过
     *
     * 核心思路：模拟入栈和出栈的过程
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrderTwo(int [] pushA,int [] popA) {
        // 每个数组一个指针 [1,2,3,4,5],[4,3,5,1,2]
        LinkedList<Integer> stack = new LinkedList<>();
        if (pushA.length != popA.length || pushA.length == 0) {
            return false;
        }
        int j = 0;
        for (int k : pushA) {
            stack.add(k);
            // popA[j] 注意不能越界, pushA.length = popA.length ，同时stack先插,隐含了 j < popA.length,所以可以去掉这个条件
            while (stack.size() > 0 && stack.getLast().equals(popA[j])) {
                stack.removeLast();
                j++;
            }
        }
        return stack.isEmpty();
    }
}

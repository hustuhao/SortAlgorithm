package cn.tuhao.stack;

import java.util.Stack;

public class StackToQueueTest {
    /**
     * 进栈用Stack1
     * 出栈用stack2
     * 当元素从stack1到stack2时，正好顺序颠倒。
     */
    static Stack<Integer> stack1 = new Stack<Integer>();
    static Stack<Integer> stack2 = new Stack<Integer>();

    public static void main(String[] args) {
        push(1);
        push(2);
        push(3);
        push(4);
        System.out.println(pop());
        push(5);
        push(6);
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }

    public static void push(int node) {
        stack1.push(node);
    }

    public static int pop() {
        if (0 == stack2.size()) {
            while (stack1.size() != 0)
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}

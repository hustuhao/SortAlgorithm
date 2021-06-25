package cn.tuhao.stack;

import java.util.LinkedList;

public class MinStackTest {

    public static void main(String[] args) {
        //-1,2,1,-1
        MinStackTest minStack = new MinStackTest();
        minStack.push(-1);
        minStack.push(2);
        System.out.println(minStack.min());
        System.out.println(minStack.top());
        minStack.pop();
        minStack.push(1);
        System.out.println(minStack.top());
        System.out.println(minStack.min());
    }

    LinkedList<Integer> stack1 = new LinkedList<>();
    LinkedList<Integer> stack2 = new LinkedList<>();

    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数，并且调用 min函数、push函数 及 pop函数 的时间复杂度都是 O(1)
     * push(value):将value压入栈中
     * pop():弹出栈顶元素
     * top():获取栈顶元素
     * min():获取栈中最小元素
     */
    public void push(int node) {
        this.stack1.add(node);
        if (stack2.size() == 0) {
            this.stack2.add(node);
        } else if (stack2.getLast() >= node){
            stack2.add(node);
        }
    }

    public void pop() {
        Integer popValue = this.stack1.removeLast();
        if (popValue.equals(this.stack2.getLast())) {
            stack2.removeLast();
        }
    }

    public int top() {
        return stack1.getLast();
    }

    public int min() {
        return stack2.getLast();
    }
}

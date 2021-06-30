package cn.tuhao.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 基于数组的 Stack 实现
 * 待改进之处：
 * 1、异常和错误应该细分
 * 2、应该自定义栈满之后的增长速度
 * 3、应该能在不出栈的情况下获取栈顶元素
 * @param <E>
 */
public class MyStack<E> {
    /** 底层使用一个泛型数组做存储 */
    private E[] elementData; //数组
    private int elementCount; //当前栈中的元素
    private int capacity;//最大容量

    public MyStack() {
        //会调用下面一个构造方法,初始化一个长度为10的数组
        this(10);
    }

    public MyStack(int initialCapacity){
        this.capacity = initialCapacity;
        this.elementData =(E[])new Object [this.capacity];
        this.elementCount = 0;
    }

    /**
     * 入栈操作
     */
    public void push(E e) throws Exception{
        if(elementCount != capacity){
            elementData[elementCount++] = e;
            //判断数据是不是已经满了,看需不需要扩容
            grow();
        }else{
            throw new Exception();
        }
    }

    /**
     * 出栈操作
     */
    public E pop() throws Exception{
        if (elementCount == 0){
            throw new Exception();
        } else {
            E ret = elementData[--elementCount];
            // 注意：如果不把引用赋值为null（引用过期），则弹出的元素不会被GC回收，会造成内存泄露。
            elementData[elementCount] = null;
            return ret;
        }
    }

    /**
     * 查看栈顶元素
     * @return 栈顶元素
     * @throws Exception 当栈为空的时候，抛出 NoSuchElementException 异常
     */
    public E top() throws Exception {
        if (elementCount == 0) {
            throw new NoSuchElementException();
        } else {
            return elementData[elementCount - 1];
        }
    }

    /**
     * grow操作,把数组扩大两倍
     没有考虑增长因子，默认2倍
     */
    private void grow(){
        // 如果top和capacity相等的话,说明满了
        if(elementCount==capacity){
            // 把范围括大2倍
            this.capacity = this.capacity*2;
            elementData = Arrays.copyOf(elementData, capacity);
        }

    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>(2);
        try {
            stack.push("first");
            stack.push("second");
            stack.push("third");
            stack.push("four");
            String pop = stack.pop();
            String top = stack.top();
            System.out.println(pop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

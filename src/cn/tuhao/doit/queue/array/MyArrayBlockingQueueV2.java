package cn.tuhao.doit.queue.array;

import cn.tuhao.doit.queue.MyBlockingQueue;

public class MyArrayBlockingQueueV2<E> implements MyBlockingQueue<E> {

    /**
     * 队列默认的容量大小
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * 承载队列元素的底层数组
     */
    private final Object[] elements;

    /**
     * 当前头部元素的下标
     */
    private int head;

    /**
     * 下一个元素插入时的下标
     */
    private int tail;

    /**
     * 队列中元素个数
     */
    private int count;

    //=================================================构造方法======================================================
    public MyArrayBlockingQueueV2() {
        // 设置数组大小为默认
        this.elements = new Object[DEFAULT_CAPACITY];
        // 初始化队列 头部,尾部下标
        this.head = 0;
        this.tail = 0;
    }

    public MyArrayBlockingQueueV2(int initCapacity) {
        assert initCapacity > 0;
        this.elements = new Object[initCapacity];

        // 初始化队列 头部,尾部下标
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 入队
     */
    private void enqueue(E e) {
        // 存放新插入的元素
        this.elements[this.tail] = e;
        // 尾部插入新元素后 tail下标后移一位
        this.tail = this.tail + 1;
        if (this.tail == this.elements.length) {
            this.tail = 0;
        }
        this.count++;
    }

    /**
     * 出队
     */
    private E dequeue() {
        // 暂存需要被删除的数据
        E dataNeedRemove = (E) this.elements[this.head];
        // 将当前头部元素引用释放
        this.elements[this.head] = null;
        // 头部下标 后移一位
        this.head = this.head + 1;
        if (this.head == this.elements.length) {
            this.head = 0;
        }
        this.count--;
        return dataNeedRemove;
    }

    @Override
    public void put(E e) throws InterruptedException {
        while (true) {
            synchronized (this) {
                // 队列未满时执行入队操作
                if (count != elements.length) {
                    // 入队，并返回
                    enqueue(e);
                    return;
                }
            }

            // 队列已满，休眠一段时间后重试
            Thread.sleep(100L);
        }
    }

    @Override
    public boolean offer(E e) {
        if (this.count == this.elements.length)
            return false;
        enqueue(e);
        return true;
    }

    @Override
    public E take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // 队列非空时执行出队操作
                if (count != 0) {
                    // 出队并立即返回
                    return dequeue();
                }
            }
            // 队列为空的情况下,休眠一段时间后重试
            Thread.sleep(100L);
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}

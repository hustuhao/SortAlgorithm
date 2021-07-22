package cn.tuhao.doit.queue.array;

import cn.tuhao.doit.queue.MyBlockingQueue;

/**
 * 数组作为底层结构的阻塞队列 v1版本
 */
public class MyArrayBlockingQueueV1<E> implements MyBlockingQueue<E> {

    /**
     * 队列默认的容量大小
     * */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * 承载队列元素的底层数组
     * */
    private final Object[] elements;

    /**
     * 当前头部元素的下标
     * */
    private int head;

    /**
     * 下一个元素插入时的下标
     * */
    private int tail;

    /**
     * 队列中元素个数
     * */
    private int count;

    //=================================================构造方法======================================================
    public MyArrayBlockingQueueV1() {
        // 设置数组大小为默认
        this.elements = new Object[DEFAULT_CAPACITY];

        // 初始化队列 头部,尾部下标
        this.head = 0;
        this.tail = 0;
    }

    public MyArrayBlockingQueueV1(int initCapacity) {
        assert initCapacity > 0;
        this.elements = new Object[initCapacity];

        // 初始化队列 头部,尾部下标
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 下标取模
     * */
    private int getMod(int logicIndex){
        int innerArrayLength = this.elements.length;

        // 由于队列下标逻辑上是循环的
        if(logicIndex < 0){
            // 当逻辑下标小于零时

            // 真实下标 = 逻辑下标 + 加上当前数组长度
            return logicIndex + innerArrayLength;
        } else if(logicIndex >= innerArrayLength){
            // 当逻辑下标大于数组长度时

            // 真实下标 = 逻辑下标 - 减去当前数组长度
            return logicIndex - innerArrayLength;
        } else {
            // 真实下标 = 逻辑下标
            return logicIndex;
        }
    }

    /**
     * 入队
     * */
    private void enqueue(E e){
        if (this.count == this.elements.length) {
            throw new RuntimeException("队列已经满了");
        }

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
     * */
    private E dequeue(){
        // 暂存需要被删除的数据
        E dataNeedRemove = (E)this.elements[this.head];
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
    public void put(E e){
        enqueue(e);
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
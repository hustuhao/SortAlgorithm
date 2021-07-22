package cn.tuhao.doit.queue.array;

import cn.tuhao.doit.queue.MyBlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue<E> implements MyBlockingQueue<E> {

    private Object[] elements;
    // 入队指针，指向队首
    private int head;
    // 出队指针，指向队尾的下一个位置
    private int tail;
    // 元素个数
    private int count;
    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;
    private final static int DEFAULT_CAPACITY = 32;
    private final static boolean fair = true;

    public MyArrayBlockingQueue() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayBlockingQueue(int size) {
        this.elements = new Object[size];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.lock = new ReentrantLock(fair);
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }


    @Override
    public void put(E e) throws InterruptedException {
        // 尝试获取锁
        lock.lockInterruptibly();

        try {
            // 队列已经满了
            while (this.count == elements.length) {
                // 等待队列入队
                notFull.await();
            }
            enqueue(e);
            // 对列中有了元素，唤醒等待的队列
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (this.count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * 入队
     * @param e
     */
    private void enqueue(E e) {
        this.elements[tail] = e;
        this.tail++;
        if (this.tail == elements.length) {
            this.tail = 0;
        }
        this.count++;
    }

    /**
     * 出列
     */
    private E dequeue() {
        E dataNeedRemove = (E)this.elements[this.head];
        this.elements[head] = null;
        this.head++;
        if (this.head == elements.length) {
            head = 0;
        }
        this.count--;
        notFull.signalAll();
        return dataNeedRemove;
    }

    // 判断是否为空
    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }
}

package cn.tuhao.doit.queue;

import cn.tuhao.doit.queue.array.MyArrayBlockingQueue;
import cn.tuhao.doit.queue.array.MyArrayBlockingQueueV1;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class MyBlockingQueueTest {

    @Test
    public void test1() {
        try {
            MyArrayBlockingQueueV1<Integer> v1 = new MyArrayBlockingQueueV1<>(5);
            v1.put(1);
            v1.put(2);
            v1.put(3);
            v1.put(4);
            v1.put(5);
            boolean offer = v1.offer(6);
            v1.take();
            v1.take();
            v1.take();
            v1.take();
            v1.take();
            v1.put(6);
            v1.put(7);
            v1.put(8);
            v1.put(9);
            v1.put(10);
            Integer take = v1.take();
        } catch (Exception e) {

        }

    }

    @Test
    public void testMyArrayBlockingQueue() {
        ExecutorService pool = new ThreadPoolExecutor(5, 5, 200L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        MyBlockingQueue<Integer> queue = new MyArrayBlockingQueue<>(5);
        // 生产者线程
        pool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName()+"-put:"+ i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        // 消费者线程
        pool.submit(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    Integer take = queue.take();
                    System.out.println(Thread.currentThread().getName()+"-task:"+ take.intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 队列为空
        Assert.assertTrue(queue.isEmpty());
    }
}
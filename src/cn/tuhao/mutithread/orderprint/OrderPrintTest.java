package cn.tuhao.mutithread.orderprint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程按顺序打印: first second third
 *
 * 多线程编程重要的就是标志位Flag，锁，唤醒
 * 多线程编程注意的两个地方：标志位Flag和while
 * 1.判断
 * 2.干活
 * 3.通知+唤醒
 *
 *
 * 方法：
 * 1、flag：AtomicInteger,boolean 作为flag
 * 2、lock锁，lock.newCondition()
 * 3、CountDownLatch
 * 4、阻塞队列
 */
public class OrderPrintTest {

    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        Foo foo = new Foo();
        newFixedThreadPool.submit(foo::third);
        newFixedThreadPool.submit(foo::first);
        newFixedThreadPool.submit(foo::second);
        newFixedThreadPool.shutdown();
    }

    static class Foo {

        private AtomicInteger firstJobDone = new AtomicInteger(0);
        private AtomicInteger secondJobDone = new AtomicInteger(0);

        public Foo() {}

        public void first() {
            System.out.println("first");
            // mark the first job as done, by increasing its count.
            firstJobDone.incrementAndGet();
        }

        public void second() {
            while (firstJobDone.get() != 1) {
                // waiting for the first job to be done.
            }
            System.out.println("second");
            // mark the second as done, by increasing its count.
            secondJobDone.incrementAndGet();
        }

        public void third() {
            while (secondJobDone.get() != 1) {
                // waiting for the second job to be done.
            }
            System.out.println("third");
        }
    }
}

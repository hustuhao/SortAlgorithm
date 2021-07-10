package cn.tuhao.mutithread.cas;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 三个售票处卖票
 */
public class SellTest {
    static AtomicInteger stock = new AtomicInteger(0);
    static ExecutorService pool = new ThreadPoolExecutor(
            3,
            3,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10000)
    );


    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            pool.submit(() -> {
                while (stock.get() < 1000) {
                    int result = stock.incrementAndGet();
                    System.out.println(Thread.currentThread().getName()+":"+result);
                }
            });
        }
    }
}

package cn.tuhao.mutithread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 测试带有返回值的任务
 */
public class FutureTaskTest {
    static ExecutorService pool = new ThreadPoolExecutor(
            3,
            3,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10000)
    );

    @Test
    public void test() throws ExecutionException, InterruptedException {
        Future<String> taskOne = pool.submit(() -> {
            Thread.sleep(1000);
            return "helloWorld";
        });
        String s = taskOne.get();
        pool.submit(() -> System.out.println(s));
    }
}

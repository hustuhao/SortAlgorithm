package cn.tuhao.mutithread;

import org.junit.Test;

/**
 * 如何终止线程：https://blog.csdn.net/zhangliangzi/article/details/52484302
 *
 * 三种方法：两阶段终止模式：1、设置线程终止标志位 2、响应终止指令
 * 1、stop()
 *      被弃用，立即终止正在运行线程，可能导致任务没有完成，所以弃用
 * 2、volatile 标志位
 *      使用volatile目的是保证可见性
 * 3、interrupt() 方法
 *      使用interrupt()中断的方式，注意使用interrupt()方法中断正在运行中的线程只会修改中断状态位，
 *      可以通过isInterrupted()判断。
 *      如果使用interrupt()方法中断阻塞中的线程，
 *      那么就会抛出InterruptedException异常，可以通过catch捕获异常，然后进行处理后终止线程。
 */
public class StopThreadTest {

    boolean started = false;
    //采集线程
    Thread rptThread;
    //启动采集功能
    synchronized void start(){
        //不允许同时启动多个采集线程
        if (started) {
            return;
        }
        System.out.println("------启动程序------");
        started = true;
        rptThread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                //省略采集、回传实现
                System.out.println("----------采集 & 回传----------");
                //每隔两秒钟采集、回传一次数据
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    //重新设置线程中断状态
                    Thread.currentThread().interrupt();
                }
            }
            //执行到此处说明线程马上终止
            started = false;
        });
        rptThread.start();
    }
    //终止采集功能
    synchronized void stop(){
        System.out.println("-----终止线程------");
        rptThread.interrupt();
    }


    @Test
    public void test() throws InterruptedException {
        StopThreadTest stopThreadTest = new StopThreadTest();
        stopThreadTest.start();
        Thread.sleep(10000);
        stopThreadTest.stop();
    }
}

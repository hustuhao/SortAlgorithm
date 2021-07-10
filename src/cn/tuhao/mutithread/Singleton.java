package cn.tuhao.mutithread;

/**
 * volatile用于禁止指令重排序：可以解决单例双重检查对象初始化代码执行乱序问题。
 * 而synchronized是一种排他(互斥)的机制。
 *
 * 禁止指令重排序的好处:
 * 对象实际上创建对象要进过如下几个步骤：
 * 1|分配内存空间。
 * 2|调用构造器，初始化实例。
 * 3|返回地址给引用
 * 指令重排后：顺序可能编程 2 1 3
 */
public class Singleton {
    private volatile static Singleton instance;
    public Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

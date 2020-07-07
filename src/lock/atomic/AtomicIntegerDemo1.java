package lock.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：演示AtomicInteger的基本用法，对比非原子类的线程安全问题。
 *使用了原子类之后也可以保证线程安全。
 * @Author wzy
 * @Date 2020/7/7 0:15
 * @Version V1.0
 **/
public class AtomicIntegerDemo1 implements Runnable {
    private final static AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndIncrement();
    }

    private static volatile int basicCount = 0;

    /**
     * 普通变量也可以做到线程安全，用synchronized
     */
    public void incrementBasic() {
        basicCount ++;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("原子类结果：" + atomicInteger.get());
        System.out.println("普通变量结果：" + basicCount);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }
}

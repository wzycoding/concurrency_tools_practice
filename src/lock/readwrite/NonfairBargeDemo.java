package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述： 演示非公平锁和公平锁读写锁的策略,
 * 可以观察到，队列头结点是请求写锁的线程，后面的读锁不能插队。
 */
public class NonfairBargeDemo {
    /** 读写锁 **/
    private static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock();

    /** 读锁 **/
    private static ReentrantReadWriteLock.ReadLock readLock =
            reentrantReadWriteLock.readLock();

    /** 写锁 **/
    private static ReentrantReadWriteLock.WriteLock writeLock =
            reentrantReadWriteLock.writeLock();

    /**
     * 读方法获取读锁
     */
    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁， 正在读取中...");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();

        }
    }

    /**
     * 写方法获取写锁
     */
    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁， 正在写入中...");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        //读不可以插队，当队列头结点是请求写锁的情况下
//        new Thread(() -> write(), "Tread1").start();
//        new Thread(() -> read(), "Tread2").start();
//        new Thread(() -> read(), "Tread3").start();
//        new Thread(() -> write(), "Tread4").start();
//        new Thread(() -> read(), "Tread5").start();

    }


}

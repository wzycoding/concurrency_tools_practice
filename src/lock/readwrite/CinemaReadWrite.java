package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述： 通过电影院买票例子演示读写锁
 */
public class CinemaReadWrite {
    private static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock =
            reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock =
            reentrantReadWriteLock.writeLock();

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
        new Thread(() -> read(), "Tread1").start();
        new Thread(() -> read(), "Tread2").start();
        new Thread(() -> write(), "Tread3").start();
        new Thread(() -> write(), "Tread4").start();
    }

}

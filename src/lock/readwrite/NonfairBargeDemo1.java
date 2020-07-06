package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述： 演示非公平锁和公平锁读写锁的策略,
 * 头结点也是读，就可以插队了
 */
public class NonfairBargeDemo1 {
    /** 读写锁 **/
    private static ReentrantReadWriteLock reentrantReadWriteLock
            = new ReentrantReadWriteLock(true);

    /** 读锁 **/
    private static ReentrantReadWriteLock.ReadLock readLock
            = reentrantReadWriteLock.readLock();
    /** 写锁 **/
    private static ReentrantReadWriteLock.WriteLock writeLock
            = reentrantReadWriteLock.writeLock();

    public static void read() {
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 得到读锁，正在读取");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    public static void write() {
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 得到写锁，正在读取");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        // 写不是头结点
        new Thread(() -> write(), "Tread1").start();
        new Thread(() -> read(), "Tread2").start();
        new Thread(() -> read(), "Tread3").start();
        new Thread(() -> write(), "Tread4").start();
        new Thread(() -> read(), "Tread5").start();

        // 这里主要测试的是这样一种情况，获取读锁的线程2已经执行完，线程3，也是
        // 获取读锁，还在队列的头部，有很多的子线程来获取读锁，这种情况下可以插队到线程3的前面。
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread[] = new Thread[1000];
                for (int i = 0; i < 1000; i++) {
                    thread[i] = new Thread(() -> read(), "子线程创建的Thread" + i);
;                }
                for (int i = 0; i < 1000; i++) {
                    thread[i].start();
                }
            }
        }).start();

    }

}

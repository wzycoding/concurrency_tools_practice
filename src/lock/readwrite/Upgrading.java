package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁升级
 */
public class Upgrading {
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
    private static void readUpgrading() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁， 正在读取中...");
            Thread.sleep(1000);
            System.out.println("升级不会成功会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "升级成功，获得到了写锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();

        }
    }

    /**
     * 写锁降级
     */
    private static void writeDowngrading() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁， 正在写入中...");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println("在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示降级是可以的");
        Thread thread1 = new Thread(() -> {
            writeDowngrading();
        }, "Thread1");
        thread1.start();
        // 挂起当前线程知道thread1执行完毕
        thread1.join();

        System.out.println("===================================");

        System.out.println("演示升级是不行的");
        Thread thread2 = new Thread(() -> readUpgrading(), "Thread2");
        thread2.start();
    }


}

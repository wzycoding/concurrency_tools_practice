package lock.reentranlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class GetHoldCount {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 三次加锁三次解锁 getHoldCount线程拿到锁的次数
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();

        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();

    }
}

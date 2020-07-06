package lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述：自旋锁
 *
 * @Author wzy
 * @Date 2020/7/6 19:28
 * @Version V1.0
 **/
public class SpinLock {
    // 原子引用
    private AtomicReference sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        // 能够把当前的线程的引用替换到sign中就相当于获得了锁
        while (!sign.compareAndSet(null, current)) {
            System.out.println(Thread.currentThread().getName() + "自旋获取失败再次尝试");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放自旋锁
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}

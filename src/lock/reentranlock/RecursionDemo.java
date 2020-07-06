package lock.reentranlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：递归获取资源
 */
public class RecursionDemo {
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        lock.lock();
        try {
            // 处理五次才能处理完毕
            System.out.println("已经对资源进行了处理");
            if (lock.getHoldCount() < 5) {
                System.out.println(Thread.currentThread().getName() + lock.getHoldCount());
                accessResource();
                System.out.println(Thread.currentThread().getName() + lock.getHoldCount());
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}

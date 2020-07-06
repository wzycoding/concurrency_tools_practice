package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示乐观锁和悲观锁
 */
public class PessimismOptimismLock {
    int a;
    // 悲观锁
    public synchronized void testMethod() {
        a++;
    }

    // 乐观锁
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}

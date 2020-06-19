package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述： 演示读写锁的非公平锁
 */
public class NonfairBargeDemo {
    private final ReentrantReadWriteLock reentrantReadWriteLock =
            new ReentrantReadWriteLock(true);


}

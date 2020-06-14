package threadlocal;

/**
 * 描述 TODO 空指针异常
 */
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    // 这里要用包装类型
    public Long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
//                threadLocalNPE.set();
                // 直接get拿到的是null
                System.out.println(threadLocalNPE.get());
            }
        }).start();
    }
}

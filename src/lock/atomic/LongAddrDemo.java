package lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 描述：演示高并发场景下，LongAdder比AtomicLong性能好
 * LongAdder耗时：350
 *
 * @Author wzy
 * @Date 2020/7/7 15:39
 * @Version V1.0
 **/
public class LongAddrDemo {
    public static void main(String[] args) throws InterruptedException {
        LongAdder adder = new LongAdder();

        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10000; i++) {
            service.submit(new Task(adder));
        }

        service.shutdown();
        while (!service.isTerminated()) {

        }
        long end = System.currentTimeMillis();

        System.out.println((adder.longValue()));

        System.out.println("LongAdder耗时：" + (end - start));

    }

    public static class Task implements Runnable {
        private LongAdder adder;

        public Task(LongAdder adder) {
            this.adder = adder;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                adder.increment();
            }
        }
    }
}

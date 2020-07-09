package flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 描述：演示Semaphore用法
 *
 * @Author wzy
 * @Date 2020/7/9 18:12
 * @Version V1.0
 **/
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            service.submit(new Task());
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                //可以拿多个许可证
//                semaphore.acquire();
                semaphore.acquire(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release(3);
        }
    }
}

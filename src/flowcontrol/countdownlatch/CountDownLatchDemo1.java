package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时门栓
 * 描述：工厂中，质检，5个工人检查，所有人都认为通过之后，
 * 才通过
 * CountLatch可以做一些启动前的上下游服务是否可用、数据是否准备好这种检查工作
 * 这种检查工作是可以并行执行的。
 *
 * 一等多
 *
 * @Author wzy
 * @Date 2020/7/9 13:43
 * @Version V1.0
 **/
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + no + " 完成了检查。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("等待五个人检查完.......");
        // 让谁等待谁就调用await
        latch.await();

        System.out.println("所有人都完成了工作进入下一个环节");

        // 关闭线程池
        executorService.shutdown();
    }

}

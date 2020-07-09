package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：模拟100米跑步，当所有人都到终点后比赛结束。
 *
 * 实际应用场景：压测的时候我们可能希望多个线程同时去请求，这样来创造并发的效果
 * @Author wzy
 * @Date 2020/7/9 14:02
 * @Version V1.0
 **/
public class CountDownLatchDemo1And2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable(){

                @Override
                public void run() {
                    System.out.println("NO." + no + "准备完毕，等待发令枪");
                    try {
                        begin.await();
                        System.out.println("NO." + no + "开始跑步");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("NO." + no + "跑到终点");
                        end.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.submit(runnable);
        }
        // 裁判员检查发令枪....
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始！");
        begin.countDown();

        end.await();
        System.out.println("所有运动员到达，比赛结束");

        service.shutdown();
    }
}

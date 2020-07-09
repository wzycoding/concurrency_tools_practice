package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同
 * 时开始跑步
 *
 * 实际应用场景：压测的时候我们可能希望多个线程同时去请求，这样来创造并发的效果
 * @Author wzy
 * @Date 2020/7/9 14:02
 * @Version V1.0
 **/
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);

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

        service.shutdown();
    }
}

package collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 描述：
 *
 * @Author wzy
 * @Date 2020/7/9 12:03
 * @Version V1.0
 **/
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        final ArrayBlockingQueue<String> queue =
                new ArrayBlockingQueue<String>(3);

        Interviewer r1 = new Interviewer(queue);
        Consumer r2 = new Consumer(queue);

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);

        thread1.start();
        thread2.start();


    }

}

/**
 * 生产者（面试者）
 */
class Interviewer implements Runnable {
    BlockingQueue queue;
    public Interviewer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("十个候选人都来了");
        for (int i = 0; i < 10; i++) {
            String candidate = "Candidate" + i;
            try {
                queue.put(candidate);
                System.out.println("安排好了" + candidate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        String msg;
        try {
            while (!(msg = queue.take()).equals("stop")) {
                System.out.println(msg + "到了");
                Thread.sleep(1000);
            }
            System.out.println("所有候选人都结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

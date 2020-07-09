package collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述：组合操作并不保证线程安全
 * 不是说一个工具类是线程安全的，就怎么用都是线程安全的
 * @Author wzy
 * @Date 2020/7/8 14:13
 * @Version V1.0
 **/
public class OptionsNotSafe implements Runnable{

    private static ConcurrentHashMap<String, Integer> scores =
            new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);

        Thread thread1 = new Thread(new OptionsNotSafe());
        Thread thread2 = new Thread(new OptionsNotSafe());

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(scores);
    }
//    @Override
//    public void run() {
//        for (int i = 0; i < 1000; i++) {
//            //这种强同步不好，好不容易开发了ConcurrentHashMap来保证线程安全，
//
////            synchronized (OptionsNotSafe.class) {
//                Integer score = scores.get("小明");
//                Integer newScore = score + 1;
//                // 仅仅保证单步操作线程安全
//                scores.put("小明", newScore);
////            }
//
//        }
//    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            // 也是CAS乐观锁机制
            while (true) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                boolean b = scores.replace("小明", score, newScore);
                if (b) {
                    break;
                }
            }

        }
    }
}

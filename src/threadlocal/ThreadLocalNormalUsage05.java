package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description: 利用ThreadLocal给每个线程分配自己dateFormat对象 <br>
 * date: 2020-06-10 21:42 <br>
 * author: wzy <br>
 * version: 1.0 <br>
 */
public class ThreadLocalNormalUsage05 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public String date(int seconds) {
        // 参数的单位是毫秒，是从1970.1.1 00:00:00 GMT计时，往上加,我们在东八区，会加八个小时
        Date date = new Date(1000 * seconds);
        // 在每个线程中会有一份，总共有十份
        SimpleDateFormat sdf  = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return sdf.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage05().date(finalI);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    // 也可以使用Lambada表达式方式进行实现
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

}

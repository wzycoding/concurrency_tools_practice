package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: 十个线程打印日期 <br>
 * date: 2020-06-10 21:42 <br>
 * author: wzy <br>
 * version: 1.0 <br>
 */
public class ThreadLocalNormalUsage01 {

    public String date(int seconds) {
        // 参数的单位是毫秒，是从1970.1.1 00:00:00 GMT计时，往上加,我们在东八区，会加八个小时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }
    }
}

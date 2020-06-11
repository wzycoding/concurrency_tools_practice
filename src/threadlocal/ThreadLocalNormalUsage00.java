package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description: 两个线程打印日期<br>
 * date: 2020-06-10 21:42 <br>
 * author: wzy <br>
 * version: 1.0 <br>
 */
public class ThreadLocalNormalUsage00 {

    public String date(int seconds) {
        // 参数的单位是毫秒，是从1970.1.1 00:00:00 GMT计时，往上加,我们在东八区，会加八个小时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(104707);
                System.out.println(date);
            }
        }).start();


    }
}

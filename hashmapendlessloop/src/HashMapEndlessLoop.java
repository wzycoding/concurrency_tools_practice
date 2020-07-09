import java.util.HashMap;

/**
 * 描述：演示HashMap在多线程情况下造成死循环的情况CPU100%，在1.7才会出现
 *
 * @Author wzy
 * @Date 2020/7/8 11:34
 * @Version V1.0
 **/
public class HashMapEndlessLoop {
    // 扩容造成的
    private static HashMap<Integer, String> map =
            new HashMap<>(2, 1.5f);

    public static void main(String[] args) {
        // 都会落到第一个槽中
        map.put(5, "C");
        map.put(7, "B");
        map.put(3, "A");

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(15, "D");
                System.out.println(map);
            }
        }, "Thread 1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put(1, "E");
                System.out.println(map);
            }
        }, "Thread 2").start();
    }
}

package lock.cas;

/**
 * 描述：模拟CAS操作，等价代码
 *
 * @Author wzy
 * @Date 2020/7/7 18:43
 * @Version V1.0
 **/
public class TowThreadCompetition implements Runnable{

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TowThreadCompetition r = new TowThreadCompetition();
        r.value = 0;
        Thread r1 = new Thread(r, "Thread-1");
        Thread r2 = new Thread(r, "Thread-2");

        r1.start();
        r2.start();

        r1.join();
        r2.join();

        System.out.println(r.value);
    }
}

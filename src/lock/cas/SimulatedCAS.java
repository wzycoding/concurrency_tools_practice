package lock.cas;

/**
 * 描述：模拟CAS操作，等价代码
 *
 * @Author wzy
 * @Date 2020/7/7 18:43
 * @Version V1.0
 **/
public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}

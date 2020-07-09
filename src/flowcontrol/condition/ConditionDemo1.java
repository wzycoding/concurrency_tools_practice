package flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：Condition的基本用法
 *
 * @Author wzy
 * @Date 2020/7/9 19:22
 * @Version V1.0
 **/
public class ConditionDemo1 {
    private  ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() {
        lock.lock();
        try {
            System.out.println("条件不满足开始await");
            condition.await();
            System.out.println("条件满足，开始执行后续的任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void method2() {
        lock.lock();
        try {
            System.out.println("准备工作完成开始唤醒其他线程");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionDemo1.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    conditionDemo1.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

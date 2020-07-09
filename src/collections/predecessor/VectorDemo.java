package collections.predecessor;

import java.util.Vector;

/**
 * 描述：可以当初ArrayList来使用
 * 演示Vector，主要看Vector源码
 * 很多方法都用到synchronized，虽然是线程安全，多线程性能也不好
 * @Author wzy
 * @Date 2020/7/8 9:15
 * @Version V1.0
 **/
public class VectorDemo {


    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
    }

}

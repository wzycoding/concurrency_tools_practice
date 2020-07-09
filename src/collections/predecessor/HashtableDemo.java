package collections.predecessor;

import java.util.Hashtable;

/**
 * 描述：演示Hashtable
 *
 * @Author wzy
 * @Date 2020/7/8 9:22
 * @Version V1.0
 **/
public class HashtableDemo {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("学完以后跳槽涨薪幅度", "50%");

        System.out.println(hashtable.get("学完以后跳槽涨薪幅度"));
    }
}

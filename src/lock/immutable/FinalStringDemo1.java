package lock.immutable;

/**
 * 描述：面试题看输出结果
 *
 * true
 * false
 *
 * @Author wzy
 * @Date 2020/7/8 0:47
 * @Version V1.0
 **/
public class FinalStringDemo1 {
    public static void main(String[] args) {
        String a = "wukong2";
        // 这里会将b作为一个常量
        final String b = "wukong";
        String d = "wukong";

        // 直接计算出是：wukong2，指向已经存在的常量
        String c = b + 2;
        // 会在堆上创建对象
        String e = d + 2;

        System.out.println((a == c));
        System.out.println((a == e));

    }
}

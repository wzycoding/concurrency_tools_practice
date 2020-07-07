package lock.immutable;

/**
 * 描述：
 *
 * @Author wzy
 * @Date 2020/7/8 0:55
 * @Version V1.0
 **/
public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        // 通过方法所获得的，编译器无法确定值，也不会去优化。
        final String b = getDashixiong();
        String c = b + 2;
        System.out.println(a == c);
    }

    private static String getDashixiong() {
        return "wukong";
    }
}

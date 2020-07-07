package lock.immutable;

/**
 * 描述：演示final变量
 *
 * @Author wzy
 * @Date 2020/7/7 23:27
 * @Version V1.0
 **/
public class FinalVariableDemo {
    private final int a;

    {
        a = 7;
    }

//    public FinalVariableDemo(int a) {
//        this.a = a;
//    }

    private static final int b;
    static  {
        b = 2;
    }

    public void test() {
        // 不要求赋值时机，但是使用前必须赋值
        // 和普通变量相同，使用前必须赋值
        final int c;

        c = 3;
//        int i = c;

    }

    public void drink() {

    }

    /**
     * final表标识方法不能重写
     */
    public final void eat() {

    }


}

class SubClass extends FinalVariableDemo {
    @Override
    public void drink() {
        super.drink();
    }

//    public final void eat() {
//
//    }
}

package lock.immutable;

/**
 * 描述：测试final能否被修改
 *
 * @Author wzy
 * @Date 2020/7/7 22:22
 * @Version V1.0
 **/
public class TestFinal {
    public static void main(String[] args) {
        final Person person = new Person();
        // 都不能修改final修饰的变量
//        person.age = 30;
//        person.name = "";
        // person引用地址不可变，对象的属性可以改变
//        person = new Person();
    }
}

package lock.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述：一个属性是对象，但是整体不可变，其他无法修改set里面的数据。
 *
 * @Author wzy
 * @Date 2020/7/8 0:28
 * @Version V1.0
 **/
public class ImmutableDemo {

    // 没有设置的方法，private权限，对象无法被更改了。
    private final Set<String> students = new HashSet<>();
    public ImmutableDemo() {
        students.add("张三");
        students.add("李四");
        students.add("王五");
    }

    public boolean isStudent(String name) {
        return students.contains(name);
    }


}

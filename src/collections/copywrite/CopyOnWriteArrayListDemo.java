package collections.copywrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述：演示CopyOnWriteArrayList可以在迭代的过程中修改数组内容，
 * 但是ArrayList不行
 *
 * @Author wzy
 * @Date 2020/7/8 22:49
 * @Version V1.0
 **/
public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) {
        // 不可以在迭代中修改
//        ArrayList<String> list = new ArrayList<>();

        // 可以在迭代中修改
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println("List is" + list);
            String next = iterator.next();
            System.out.println(next);

            if (next.equals("2")) {
                list.remove("5");
            }

            if (next.equals("3")) {
                list.add("3 found");
            }
        }
    }

}

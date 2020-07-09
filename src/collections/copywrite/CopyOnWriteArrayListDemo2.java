package collections.copywrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述：对比两个迭代器
 *
 * @Author wzy
 * @Date 2020/7/9 1:17
 * @Version V1.0
 **/
public class CopyOnWriteArrayListDemo2 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list =
                new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});

//        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        // 它能拿到的数据取决于诞生的时间，不取决于拿到的时间
        Iterator<Integer> itr1 = list.iterator();
        list.add(4);
        System.out.println(list);

        Iterator<Integer> itr2 = list.iterator();


        itr1.forEachRemaining(System.out::println);
        itr2.forEachRemaining(System.out::println);

    }
}

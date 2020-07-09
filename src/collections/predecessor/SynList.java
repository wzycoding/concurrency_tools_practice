package collections.predecessor;

import java.util.*;

/**
 * 描述：演示Collections.synchronizedList(new ArrayList<>())
 * 如果这个list会经常被修改，那么Collections.synchronizedList(new ArrayList<>())
 * 性能会优于CopyOnWriteArrayList
 * CopyOnWriteArrayList 适合读多写少
 *
 * ConcurrentHashMap几乎在任何场合都好于Hashtable和Collections工具提供的同步map
 * @Author wzy
 * @Date 2020/7/8 9:25
 * @Version V1.0
 **/
public class SynList {

    public static void main(String[] args) {
        // 用synchronized代码块包裹
        List<Integer> objects =
                Collections.synchronizedList(new ArrayList<Integer>());

        objects.add(5);

        System.out.println(objects.get(0));

        Map<String, String> synchronizedMap =
                Collections.synchronizedMap(new HashMap<>());
    }
}

package collections.predecessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：演示Map的基础用法
 *
 * @Author wzy
 * @Date 2020/7/8 9:55
 * @Version V1.0
 **/
public class MapDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.put("wzy", 20);
        map.put("zhangsan", 32);

        System.out.println(map.keySet());

        System.out.println(map.get("wzy"));

        System.out.println(map.size());

        System.out.println(map.containsKey("wzy"));

        map.remove("wzy");
        System.out.println(map.containsKey("wzy"));

        // 三种遍历map的方式
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }

        map.forEach((x, y) -> {
            System.out.println(x + "," + y);
        });

        for (String key : map.keySet()) {
            System.out.println(key + "," + map.get(key));
        }
    }
}

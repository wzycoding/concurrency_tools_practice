package lock.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * 描述：演示LongAccumulator的用法
 *
 * @Author wzy
 * @Date 2020/7/7 16:27
 * @Version V1.0
 **/
public class LongAccumulatorDemo {

    public static void main(String[] args) {
        // 使用LongAccumulator更灵活，效率高
        // x为传进来的值，y为上次计算结果值
        LongAccumulator accumulator =
                new LongAccumulator((x, y) -> x + y, 100);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        // 从1加到9
        IntStream.range(1, 10).
                forEach(i -> executorService.submit(() -> accumulator.accumulate(i)));

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println(accumulator.getThenReset());
    }
}

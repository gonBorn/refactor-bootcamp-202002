package cc.xpbootcamp.warmup.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Fibonacci {
    public long calculate(int position) {
        List<Long> container = new ArrayList<>();
        container.add(0, 1L);
        container.add(1, 1L);

        return container.get(position - 1);
    }
}



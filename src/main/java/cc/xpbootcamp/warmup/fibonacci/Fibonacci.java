package cc.xpbootcamp.warmup.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Fibonacci {
    public long calculate(int position) {
        if (position > 50 || position < 1) {
            throw new IllegalArgumentException("'n' must be in (0, 50)");
        }

        List<Long> container = new ArrayList<>();
        container.add(0, 1L);
        container.add(1, 1L);

        IntStream.rangeClosed(2, position).forEach(i -> container.add(i, container.get(i - 1) + container.get(i - 2)));

        return container.get(position - 1);
    }
}



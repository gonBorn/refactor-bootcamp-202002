package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FibonacciTest {
    private Fibonacci fibonacci;

    @BeforeEach
    void setUp() {
        fibonacci = new Fibonacci();
    }

    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        assertEquals(fibonacci.calculate(1), 1L);
    }

    @Test
    void should_return_1_when_calculate_given_position_is_2() {
        assertEquals(fibonacci.calculate(2), 1L);
    }

    @Test
    void should_return_correct_fib_number_when_calculate_given_position_is_n() {
        Map<Integer, Long> fibValues = new HashMap<>();
        fibValues.put(1, 1L);
        fibValues.put(2, 1L);
        fibValues.put(3, 2L);
        fibValues.put(4, 3L);
        fibValues.put(5, 5L);
        fibValues.put(6, 8L);
        fibValues.put(7, 13L);

        fibValues.forEach((key, value) -> assertEquals(Optional.of(fibonacci.calculate(key)).get(), value));
    }

    @Test
    void should_throw_when_calculate_given_position_is_greater_than_50() {
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacci.calculate(51);
        });
    }

    @Test
    void should_throw_when_calculate_given_position_is_less_than_0() {
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacci.calculate(-1);
        });
    }
}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Closure {

    @Test
    public void testClosure() {
        Integer a = 2;

        Function<Integer, Integer> f = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t * a;
            }
        };
        //a = 3; //this produce error - local variables referenced from a lambda expression must be final or effectively final
        Function<Integer, Integer> f2 = t -> t * a;

        Assertions.assertEquals(f.apply(3), f2.apply(3));
    }

    @Test
    public void testAvoidClosure() {
        BiFunction<Integer, Integer, Integer> f = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t, Integer a) {
                return t * a;
            }
        };

        BiFunction<Integer, Integer, Integer> f2 = (t, a) -> t * a;

        Assertions.assertEquals(f.apply(3, 2), f2.apply(3, 2));
    }
}


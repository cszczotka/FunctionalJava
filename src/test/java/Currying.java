import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class Currying {

    @Test
    public void testCurrying() {
        /**
         * f(a)(b) = g(b)
         * f(a,b) = a -> b -> a + b
         */
        Function<Integer, Function<Integer, Integer>>  f =
                new Function<Integer, Function<Integer, Integer>>() {
                    @Override
                    public Function<Integer, Integer> apply(Integer a) {
                        return new Function<Integer, Integer>() {
                            @Override
                            public Integer apply(Integer b) {
                                return a + b;
                            }
                        };
                    }
                };
        Function<Integer, Function<Integer, Integer>> f2 = a -> b -> a + b;

        Assertions.assertEquals(f.apply(2).apply(3), f2.apply(2).apply(3));
    }

    @Test
    public void testPartialApplication() {
        Function<Double, Function<Double, Double>> calculateTax = rate -> amount -> amount * rate;

        Function<Double, Double> calculateNationalTax = calculateTax.apply(0.2);
        Function<Double, Double> calculateInternationalTax = calculateTax.apply(0.3);

        System.out.println( calculateNationalTax.apply(100.0) );
        System.out.println( calculateInternationalTax.apply(100.0) );
    }

    @Test
    public void curryingFunctionTest() {
        Function<Short, Function<Integer, Function<Long, Long>>> f = s -> i -> l -> s + i + l;

        CurriedFunction3 f2 =
                s -> i -> l -> s + i + l;
    }

    interface CurriedFunction3 extends
            Function<Short, Function<Integer, Function<Long, Long>>> {}
}



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.function.Function;

public class FunctionalInterface {

    @Test
    public void testFunction_U_R() {
        Function<Integer, Integer> addOne = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer a) {
                return a+1;
            }
        };
        Function<Integer, Integer> addOne2 = a -> a + 1;
        Assertions.assertTrue(3 == addOne.apply(2));
        Assertions.assertEquals(addOne.apply(2), addOne2.apply(2));
    }

    @Test
    public void testMethodReference() {
        Function<Integer, Integer> addOne = Math::incrementExact;
        Function<Integer, Integer> addOne2 = a -> Math.incrementExact(a);

        Assertions.assertTrue(3 == addOne.apply(2));
        Assertions.assertEquals(addOne.apply(2), addOne2.apply(2));
    }


}

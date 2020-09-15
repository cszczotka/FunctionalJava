import com.cszczotka.conditional.Condition;
import com.cszczotka.model.Product;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Conditional {

    @Test
    public void testCondition() {
        Function<Product, Supplier<Double>> calculateDiscount = p ->
                Condition.<Supplier<Double>>match(
                        Condition._if(() -> p.price < 10.0, () -> p.price * 0.1),
                        Condition._if(() -> p.price >= 10.0 && p.price < 100.0, () -> p.price * 0.2),
                        Condition._if(() -> p.price >= 100.0, () -> p.price * 0.3)
                ).orElse(() -> 0.0);


        Supplier<Double> result = calculateDiscount.apply(new Product(10.0));
        System.out.println(result.get());

        Function<Integer, Consumer<Integer>> rangeChecker = n ->
                Condition.<Consumer<Integer>>match(
                        Condition._if(() -> n < 10, (Integer i) -> {
                            System.out.println("Executed " + i); }),
                        Condition._if(() -> n > 10 && n < 100, (Integer i) -> {
                            System.out.println("Executed " + i); })
                ).orElse((Integer i) -> {
                    System.out.println("Executed " + i);});

        rangeChecker.apply(6).accept(7);
    }
}

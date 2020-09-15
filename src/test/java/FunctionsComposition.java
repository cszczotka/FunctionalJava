import com.cszczotka.model.Order;
import com.cszczotka.model.RewardPoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FunctionsComposition {
    @Test
    public void testFunctionComposition() {
        Order order = new Order();

        Function<Order, RewardPoints> totalBasedRewardPoints = FunctionsComposition::calculateRewardPoints;
        Function<RewardPoints, RewardPoints> roundRewardPoints = FunctionsComposition::roundRewardPoints;
        //Function<Order, RewardPoints> f = compose(roundRewardPoints, totalBasedRewardPoints);
        Function<Order, RewardPoints> f = roundRewardPoints.compose(totalBasedRewardPoints);
        /**
         * f.compose(g) is the same like g.andThen(f)
         */
        //Function<Order, RewardPoints> f = totalBasedRewardPoints.andThen(roundRewardPoints);

        System.out.println(f.apply(order));
        Assertions.assertEquals(20, f.apply(order).points.longValue());
    }

    private static RewardPoints calculateRewardPoints(Order order) {
        return new RewardPoints(19);
    }

    private static RewardPoints roundRewardPoints(RewardPoints rewardPoints) {
        return new RewardPoints((int) (Math.round(rewardPoints.points/10.0) * 10));
    }

    Function<Order, RewardPoints> compose(Function<RewardPoints, RewardPoints> f1,
                                          Function<Order, RewardPoints> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    @Test
    public void testFluentInterface() {
        BigDecimal result = BigDecimal.TEN.add(BigDecimal.ONE)
                .multiply(BigDecimal.TEN).subtract(BigDecimal.ONE);
        Assertions.assertEquals(109, result.intValue());
    }

    @Test
    public void testComposeIdentity() {
        int n = 10;

        Function<Integer, Integer> g = x -> x; // Or Function.identity()
        Function<Integer, Integer> f = x -> x + 1;
        /*
        for (int i = 0; i < n; i++) {
            g = g.compose(f);
            //g = f.andThen(g);
        };
        */
        g = Collections.nCopies(n, f)
                .stream()
                .reduce(g, Function::compose);

        System.out.println(g.apply(0));

    }

    @Test
    public void testComposePredicates() {
        List<RewardPoints> list = Arrays.asList(
                new RewardPoints(10),
                new RewardPoints(20),
                new RewardPoints(45),
                new RewardPoints(90),
                new RewardPoints(120)
        );

        Predicate<RewardPoints> morethan40points = rp -> rp.points > 40;
        Predicate<RewardPoints> lessthan100points = rp -> rp.points < 100;

        List<RewardPoints> result = list.stream()
                .filter(morethan40points.and(lessthan100points))
                .collect(Collectors.toList());

        List<RewardPoints> result2 = list.stream()
                .filter(morethan40points)
                .filter(lessthan100points)
                .collect(Collectors.toList());

        Assertions.assertEquals(result, result2);

        List<RewardPoints> result3 = list.stream()
                .filter(morethan40points.or(lessthan100points))
                .collect(Collectors.toList());

        Assertions.assertTrue(result.size() == 2);
    }
}

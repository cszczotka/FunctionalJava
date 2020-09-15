import com.cszczotka.utils.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class TupleFunction {

    @Test
    public void testTuple() {
        Function<Tuple<Integer, Integer>, Integer> f = t -> t._1 + t._2;
        Integer result = f.apply(new Tuple<>(2, 3));
        Assertions.assertEquals(5, result.intValue());
    }
}

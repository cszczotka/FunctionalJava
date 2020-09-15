import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class OptionalAsMonad {

    @Test
    public void testOptionalAsParametrizedType() {
        //of represent Unit method
        Integer test = Integer.valueOf(1);
        Optional<Integer> integerOptional = Optional.of(10);

        //flatMap represent Bind method
        integerOptional.flatMap( ( Integer a ) -> Optional.of(a/2));

    }
}

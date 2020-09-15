import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions {

    @Test
    public void testHOF() {
        Random r = new Random();
        List<Integer> list = Arrays.asList(r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt());
        List<Integer> result = new ArrayList<>();
        for(int n : list) {
            if(n % 3 == 0) {
                result.add(n);
            }
         }
        Predicate<Integer> predicate = a -> a % 3 == 0;

        List<Integer> result2 = list.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        Assertions.assertEquals(result, result2);

    }
}

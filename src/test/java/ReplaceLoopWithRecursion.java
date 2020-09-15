import org.junit.jupiter.api.Test;

import java.util.List;

import static com.cszczotka.utils.ListUtils.head;
import static com.cszczotka.utils.ListUtils.tail;

public class ReplaceLoopWithRecursion {

    @Test
    public void testRecursion(){
        printWithLoop(10);
        printWithRecursion(10);
    }

    private static void printWithLoop(int n) {
        for(int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }

    private static void printWithRecursion(int i) {
        if(i > 0) {
            printWithRecursion(i-1);
            System.out.println(i);
        }
    }

    public static int sumImperative(List<Integer> list) {
        int total = 0;

        for (int i : list) {
            total += i;
        }

        return total;
    }

    public static int sum(List<Integer> list) {
        return list.isEmpty()
                ? 0
                : head(list) + sum(tail(list));
    }
}

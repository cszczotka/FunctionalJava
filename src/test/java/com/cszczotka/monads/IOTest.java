package com.cszczotka.monads;

import org.junit.jupiter.api.Test;

import static com.cszczotka.monads.ProcessFileService.*;
import static org.junit.jupiter.api.Assertions.*;

public class IOTest {

    @Test
    public void testClosure() {
        IO<Unit> program = readFile("src/test/resources/inputData.txt")
                .map(sales -> log(sales))
                .flatMap(sales -> writeFile(sales, "src/test/resources/outputData.txt"));

        program.exec();
    }
}
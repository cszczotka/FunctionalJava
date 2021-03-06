package com.cszczotka.monads;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessFileService {

    public static IO<List<String>> readFile(String file) {
        return () -> {
            try (Stream<String> stream = Files.lines(Paths.get(file))) {

                return stream
                        .filter(line -> line.startsWith("SD"))
                        .collect(Collectors.toList());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static List<String> log(List<String> sales) {
        System.out.println("Sales with discount: " + sales.size());

        return sales;
    }

    public static IO<Unit> writeFile(List<String> discountedSales, String file) {
        return () -> {
            try {
                Files.write(
                        Paths.get(file),
                        discountedSales, Charset.defaultCharset()
                );
                System.out.println("Written to the file : " + file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return Unit.instance;
        };
    }

    private static IO<Unit> processSale(String inputFile, String outputFile) {
        IO<Unit> program = readFile(inputFile)
                .map(sales -> log(sales))
                .flatMap(sales -> writeFile(sales, outputFile));

        return program;
    }

}
